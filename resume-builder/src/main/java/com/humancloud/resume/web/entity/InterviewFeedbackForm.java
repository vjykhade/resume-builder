package com.humancloud.resume.web.entity;

import com.humancloud.resume.web.entity.constants.InterviewResult;
import com.humancloud.resume.web.entity.constants.InterviewRound;
import com.humancloud.resume.web.entity.constants.InterviewType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewFeedbackForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer formId;
    @NotBlank(message = "Candidate Name should not be blank")
    private String candidateName;
    @NotNull(message = "Interview type should be The_Converge or Humancloud_Internal")
    @Enumerated(EnumType.STRING)
    private InterviewType interviewType;
    @NotNull(message = "Interview Round should be L1/L2/L3")
    @Enumerated(EnumType.STRING)
    private InterviewRound interviewRound;
    @DecimalMin(value = "0.0", inclusive = true, message = "Value must be greater than or equal to 0.0")
    private Float experience;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "formId")
    private Set<TechnologyRating> technologyRating = new LinkedHashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "formId")
    private Set<SoftSkillsRating> softSkillRatings = new LinkedHashSet<>();
    @NotNull(message = "Interview result should be SELECTED/REJECTED")
    @Enumerated(EnumType.STRING)
    private InterviewResult result;
    private String comments;

}
