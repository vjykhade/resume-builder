package com.humancloud.resume.web.repository;

import com.humancloud.resume.web.entity.ResumeMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResumeRepository extends JpaRepository<ResumeMasterEntity, UUID> {

}
