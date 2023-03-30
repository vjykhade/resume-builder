package com.humancloud.resume.web.controller;

import com.humancloud.resume.web.entity.InterviewFeedbackForm;
import com.humancloud.resume.web.service.FeedbackFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FeedbackFormController {
    @Autowired
    private FeedbackFormService feedbackFormService;

    @PostMapping
    public InterviewFeedbackForm submitForm(@Valid @RequestBody InterviewFeedbackForm form) {
        return feedbackFormService.submitForm(form);
    }

}
