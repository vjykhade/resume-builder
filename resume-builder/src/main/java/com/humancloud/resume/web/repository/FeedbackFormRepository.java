package com.humancloud.resume.web.repository;

import com.humancloud.resume.web.entity.InterviewFeedbackForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackFormRepository extends JpaRepository<InterviewFeedbackForm, Integer> {
}
