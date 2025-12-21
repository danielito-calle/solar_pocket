package com.app.web.marketing.domain.domain_services;

import com.app.web.marketing.cross_cutting.RepeatedSubmitterException;
import com.app.web.marketing.domain.Submission;
import com.app.web.marketing.infrastructure.ISubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyValidatorService implements ISurveyValidatorService {

    @Autowired
    private ISubmissionRepository submissionRepository;

    @Override
    public void validateSubmission(Submission submission) {
        String email = submission.getSubmitter();
        Long formId = submission.getForm() != null ? submission.getForm().getId() : null;

        if (email == null || formId == null) {
            throw new IllegalArgumentException("Datos de identificación incompletos.");
        }

        boolean alreadyExists = submissionRepository.existsBySubmitterAndFormId(email, formId);

        if (alreadyExists) {
            throw new RepeatedSubmitterException();
        }
    }
}