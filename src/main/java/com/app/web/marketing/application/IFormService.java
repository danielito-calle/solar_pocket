package com.app.web.marketing.application;

import com.app.web.marketing.domain.Form;
import com.app.web.marketing.domain.Submission;
import org.springframework.beans.factory.annotation.Autowired;

public interface IFormService {

    public Submission submitForm(Submission submission);

    public Form getFormById(Long id);
}
