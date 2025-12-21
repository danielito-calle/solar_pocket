package com.app.web.marketing.application;

import com.app.web.marketing.domain.Form;
import com.app.web.marketing.domain.Submission;
import com.app.web.marketing.domain.domain_services.ISurveyValidatorService;
import com.app.web.marketing.infrastructure.IFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService implements IFormService {

    @Autowired
    private IFormRepository formRepository;

    @Autowired
    private ISubmissionService submissionService;

    public Submission submitForm(Submission submission) {
        return submissionService.save(submission);
    }

    @Override
    public Form getFormById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: No existe el formulario con ID: " + id));
    }


}
