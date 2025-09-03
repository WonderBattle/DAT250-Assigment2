package com.Assigment2.DAT250Assigment2;

import com.Assigment2.DAT250Assigment2.model.User;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class PollManager {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Object> polls = new HashMap<>(); // We'll fix this later
    private Map<String, Object> votes = new HashMap<>(); // We'll fix this later

    // User methods
    public User createUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(String id) {
        return users.get(id);
    }

    // Simple methods for testing - we'll add proper Poll/Vote methods later
    public String createTestPoll() {
        return "Poll created (to be implemented)";
    }

    public String createTestVote() {
        return "Vote created (to be implemented)";
    }
}
