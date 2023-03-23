package com.humancloud.resume.web.controller;

import com.humancloud.resume.web.entity.ResumeMasterEntity;
import com.humancloud.resume.web.helper.PDFGeneratorHelper;
import com.humancloud.resume.web.repository.ResumeRepository;
import com.humancloud.resume.web.service.ResumeService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.hibernate.dialect.SimpleDatabaseVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ResumeMasterController {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/savedata")
    public ResumeMasterEntity saveResumeData (@Valid @RequestBody ResumeMasterEntity resumeMasterEntity)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resumeMasterEntity.setCreatedBy("admin");
        resumeMasterEntity.setCreatedDate(simpleDateFormat.format(Date.from(Instant.now())));
        resumeMasterEntity.setWorkExperience(resumeMasterEntity.getWorkExperience().stream().map(rm->{
            rm.setCreatedBy("admin");
            rm.setCreatedDate(simpleDateFormat.format(Date.from(Instant.now())));
            rm.getProjects().stream().map(p -> {
                p.setCreatedBy("admin");
                p.setCreatedDate(simpleDateFormat.format(Date.from(Instant.now())));
                return p;
            }).collect(Collectors.toList());
            return rm;
        }).collect(Collectors.toList())
        );
        return resumeRepository.save(resumeMasterEntity);
    }

    @DeleteMapping("/deletedata/{id}")
    public ResponseEntity<Void> deleteResumeData(@PathVariable("id") String uuid)
    {
         try {
             resumeRepository.deleteById(UUID.fromString(uuid));
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
         }
         catch (Exception e)
         {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
    }

    @GetMapping("/downloadpdf/{id}")
    public void generatePDFFromDB(@PathVariable("id") String uuid, HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        String headerkey = "Content-Disposition";
        String headerValue = "attachment; filename=Resume_"+uuid+".pdf";
        response.setHeader(headerkey, headerValue);
        Optional<ResumeMasterEntity> resumeMasterEntity = resumeService.getResumeData(uuid);
        PDFGeneratorHelper generator = new PDFGeneratorHelper();
        generator.GeneratePDF(resumeMasterEntity,response);

    }

    @GetMapping("/alldata")
    public List<ResumeMasterEntity> retriveAllData()
    {
        return resumeRepository.findAll();
    }
    @PutMapping("/updatedata/{id}")
    public ResumeMasterEntity updateResume(@RequestBody ResumeMasterEntity resumeMasterEntity, @PathVariable("id") String uuid)
    {
        return resumeService.updateResumeData(resumeMasterEntity,uuid);
    }
}
