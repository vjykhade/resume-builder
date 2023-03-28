package com.humancloud.resume.web.service;

import com.humancloud.resume.web.entity.InterviewFeedbackForm;
import com.humancloud.resume.web.exception.BaseException;
import com.humancloud.resume.web.repository.FeedbackFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class FeedbackFormService {

    @Autowired
    private FeedbackFormRepository feedbackFormRepo;

    public InterviewFeedbackForm submitForm(InterviewFeedbackForm form){

        if(!feedbackFormRepo.findByCandidateName(form.getCandidateName()).isPresent()){
            return feedbackFormRepo.save(form);
        }else{
            throw new BaseException(String.valueOf(HttpStatusCode.valueOf(400)),"Candidate Already Present.!");
        }

    }

}
