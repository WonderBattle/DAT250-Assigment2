package com.Assigment2.DAT250Assigment2.model;

public class User {
    private String id;
    private String username;
    private String email;
    private List<Poll> createdPolls = new ArrayList<>(); // User creates polls
    private List<Vote> votes = new ArrayList<>();        // User makes votes

    public User() {}

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Add relationship getters/setters
    public List<Poll> getCreatedPolls() { return createdPolls; }
    public void setCreatedPolls(List<Poll> createdPolls) { this.createdPolls = createdPolls; }

    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }
}
