package com.org.census.migration.repository;

import com.org.census.migration.model.BatchDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BatchDetailsRepository extends JpaRepository<BatchDetails, UUID> {
}
