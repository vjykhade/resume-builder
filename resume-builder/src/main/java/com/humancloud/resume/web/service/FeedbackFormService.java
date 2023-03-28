package com.humancloud.resume.web.service;

import com.humancloud.resume.web.entity.InterviewFeedbackForm;
import com.humancloud.resume.web.repository.FeedbackFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackFormService {

    @Autowired
    private FeedbackFormRepository feedbackFormRepo;

    public InterviewFeedbackForm submitForm(InterviewFeedbackForm form){
        return feedbackFormRepo.save(form);
    }

}
