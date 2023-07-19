package com.example.ldrp;

public class putPDF {

    String grade;
    String subject;
    String topic;
    String url;
    String name;

    public putPDF() {
    }

    public putPDF(String grade, String subject, String topic, String url, String name) {
        this.grade = grade;
        this.subject = subject;
        this.topic = topic;
        this.url = url;
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
