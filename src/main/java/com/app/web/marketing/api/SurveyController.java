package com.app.web.marketing.api;

import com.app.web.marketing.application.FormService;
import com.app.web.marketing.application.IFormService;
import com.app.web.marketing.domain.Answer;
import com.app.web.marketing.domain.Form;
import com.app.web.marketing.domain.Question;
import com.app.web.marketing.domain.Submission;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class SurveyController {

    @Autowired
    private IFormService formService;

    @PostMapping("/survey")
    public String saveSurvey(
            @RequestParam("formId") Long formId,
            @RequestParam Map<String, String> allParams,
            RedirectAttributes redirectAttributes) {
        try {
            Form form = formService.getFormById(formId);

            Submission submission = new Submission();
            submission.setForm(form);
            submission.setCreatedAt(LocalDateTime.now());

            List<Answer> answers = new ArrayList<>();

            for (Question question : form.getQuestions()) {
                String sentValue = allParams.get(question.getText());

                if (sentValue != null) {
                    Answer answer = new Answer();
                    answer.setValue(sentValue);
                    answer.setQuestion(question);
                    answer.setSubmission(submission);
                    answers.add(answer);
                }
            }

            submission.setAnswers(answers);

            formService.submitForm(submission);

            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "¡Te has unido con éxito a la lista de espera!");

            return "redirect:/";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "Hubo un problema al guardar tus datos. Por favor, intenta más tarde.");

            return "redirect:/";
        }
    }
}