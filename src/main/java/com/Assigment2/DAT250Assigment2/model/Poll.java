package com.Assigment2.DAT250Assigment2.model;

public class Poll {
    private String id;
    private String question;
    private String publishedAt;  // Changed from Instant to String
    private String validUntil;   // Changed from Instant to String

    public Poll() {}

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }

    public String getValidUntil() { return validUntil; }
    public void setValidUntil(String validUntil) { this.validUntil = validUntil; }
}