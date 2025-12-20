package com.app.web.marketing.application;

import com.app.web.marketing.domain.Submission;
import com.app.web.marketing.infrastructure.ISubmissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService implements  ISubmissionService{

    @Autowired
    private ISubmissionRepository submissionRepository;

    @Override
    @Transactional
    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }
}
