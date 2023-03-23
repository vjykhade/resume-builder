package com.humancloud.resume.web.service;

import com.humancloud.resume.web.entity.ResumeMasterEntity;
import com.humancloud.resume.web.entity.WorkExperience;
import com.humancloud.resume.web.repository.ResumeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Optional<ResumeMasterEntity> getResumeData(String uuid)
    {
        return resumeRepository.findById(UUID.fromString(uuid));
    }

    @Transactional
    public ResumeMasterEntity updateResumeData(ResumeMasterEntity resumeMasterEntity, String uuid)
    {
        Optional<ResumeMasterEntity> masterEntity = resumeRepository.findById(UUID.fromString(uuid));

                if(masterEntity != null)
                {
                    masterEntity.get().setEducationDetails(resumeMasterEntity.getEducationDetails());
                    masterEntity.get().setPersonalDetails(resumeMasterEntity.getPersonalDetails());
                    masterEntity.get().setProfessionalSummary(resumeMasterEntity.getProfessionalSummary());
                    masterEntity.get().setSkillSet(resumeMasterEntity.getSkillSet());
                    masterEntity.get().setWorkExperience(resumeMasterEntity.getWorkExperience());
                    }
                    resumeRepository.save(masterEntity.get());

        return masterEntity.get();
    }

}
