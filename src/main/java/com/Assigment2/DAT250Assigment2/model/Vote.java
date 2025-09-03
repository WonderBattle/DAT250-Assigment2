package com.Assigment2.DAT250Assigment2.model;

public class Vote {
    private String id;
    private String publishedAt;  // Changed from Instant to String

    public Vote() {}

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
}