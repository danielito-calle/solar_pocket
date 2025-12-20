package com.app.web.marketing.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    public Question(){}

    public Question(Long id, String text, String type, Form form) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.form = form;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String type;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
