package com.org.census.migration.exception;

import com.org.census.migration.constant.Constants;
import com.org.census.migration.constant.ErrorCode;
import com.org.census.migration.model.ErrorDTO;
import com.org.census.migration.model.ErrorMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static com.org.census.migration.constant.Constants.ErrorMessage.VALIDATION_FAILED_DETAIL;

@RestControllerAdvice
@Slf4j
public class MappingExceptionHandler {

    @ExceptionHandler({ javax.validation.ValidationException.class,
                              MethodArgumentNotValidException.class,
                              MethodArgumentTypeMismatchException.class,
                              MissingRequestHeaderException.class,
                              BindException.class})
    public final ResponseEntity<ErrorDTO> handleValidateExceptionHandler(Exception ex) {
        logException(ex);

        List<String> errorList = new ArrayList<>();

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException maneEx = (MethodArgumentNotValidException) ex;
            List<ObjectError> oe = maneEx.getBindingResult().getAllErrors();
            if (!CollectionUtils.isEmpty(oe)) {
                oe.forEach(error -> errorList.add(error.getDefaultMessage()));
            }
        }

        if (ex instanceof MethodArgumentTypeMismatchException) {
            String errMsg = ((MethodArgumentTypeMismatchException) ex).getName() + " in the URL is invalid ";
            errorList.add(errMsg);
        }
        if (ex instanceof javax.validation.ValidationException) {
            errorList.add(ex.getMessage());
        }
        return responseBuilder(ErrorCode.E1030000, VALIDATION_FAILED_DETAIL, errorList);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDTO> resourceNotFoundExceptionHandler(Exception ex) {
        logException(ex);
        return errorResponseEntity(ErrorCode.E1030001, ex.getMessage());
    }

    @ExceptionHandler({ValidationException.class })
    public final ResponseEntity<ErrorDTO> handleValidationExceptionHandler(ValidationException ex) {
        logException(ex);

        ErrorCode ec = ErrorCode.E1030000;
        ErrorDTO e = errorDto(ec, VALIDATION_FAILED_DETAIL);

        if (!CollectionUtils.isEmpty(ex.getErrors())) {
            ex.getErrors().forEach(error -> e.getErrors().add(new ErrorMessageDTO(error)));
        }

        if (!CollectionUtils.isEmpty(ex.getWarnings())) {
            ex.getWarnings().forEach(error -> e.getWarnings().add(new ErrorMessageDTO(error)));
        }

        return ResponseEntity.status(ec.getHttpStatus()).body(e);
    }

    private void logException(Exception ex) {
        log.error("Mapping Service Exception - ", ex);
    }

    private ResponseEntity<ErrorDTO> errorResponseEntity(ErrorCode errorCode) {
        return errorResponseEntity(errorCode, "");
    }

    private ResponseEntity<ErrorDTO> errorResponseEntity(ErrorCode errorCode, String detail) {
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorDto(errorCode, detail));
    }

    private ErrorDTO errorDto(ErrorCode errorCode, String detail) {
        return new ErrorDTO(errorCode, detail, null);
    }

    private ResponseEntity<ErrorDTO> responseBuilder(ErrorCode code, String detail, List<String> errorList) {
        return ResponseEntity.status(code.getHttpStatus())
                             .body(new ErrorDTO(code, detail, errorList, null));
    }
}
