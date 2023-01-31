package com.org.census.migration.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String DEFAULT_CLIENT_NAME = "DEFAULT";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OpenAPI {
        public static final String TITLE = "Census Migration - Core Service";

        public static final String VERSION = "1.0";

        public static final String DESCRIPTION = "Census Migration - Core And Mapping Microservice";

        public static final String GROUP = "census-migration-apis";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ApiPath {
        public static final String API = "/api";

        public static final String API_VERSION = "/v1";

        public static final String BASE_API_PATH_V1 = API + API_VERSION;

        public static final String MAPPING = "/mapping";

        public static final String SOURCE_TARGET_EHR_SERVICE_LINE = "/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}";

        public static final String CLIENT_NAME = "/client/{clientName}";

        public static final String BATCH_NAME = "/batchName/{batchName}";

        public static final String PROCESS_NAME = "/processName/{processName}";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ErrorMessage {
        public static final String VALIDATION_FAILED_DETAIL = "Validation failed";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SpringProfileNames {
        public static final String TEST = "test";

        public static final String LOCAL = "local";

        public static final String JENKINS = "jenkins";

        public static final String INT_TEST = "int";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CommonConstants {

        public static final String PATH_SEPARATOR = "/";

        public static final String SPACE = " ";

        public static final String UNDERSCORE = "_";

    }
}
