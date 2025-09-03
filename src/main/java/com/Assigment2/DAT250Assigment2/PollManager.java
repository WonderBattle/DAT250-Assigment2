package com.Assigment2.DAT250Assigment2;

import com.Assigment2.DAT250Assigment2.model.User;
import com.Assigment2.DAT250Assigment2.model.Poll;
import com.Assigment2.DAT250Assigment2.model.VoteOption;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class PollManager {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Object> polls = new HashMap<>(); // We'll fix this later
    private Map<String, Object> votes = new HashMap<>(); // We'll fix this later

    private Map<String, VoteOption> voteOptions = new HashMap<>();

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

    // Poll methods
    public Poll createPoll(Poll poll) {
        String id = UUID.randomUUID().toString();
        poll.setId(id);
        polls.put(id, poll);

        // Add poll to creator's created polls
        if (poll.getCreator() != null) {
            poll.getCreator().getCreatedPolls().add(poll);
        }

        return poll;
    }

    public List<Poll> getAllPolls() {
        return new ArrayList<>(polls.values());
    }

    public Poll getPoll(String id) {
        return polls.get(id);
    }

    public void deletePoll(String id) {
        Poll poll = polls.get(id);
        if (poll != null && poll.getCreator() != null) {
            poll.getCreator().getCreatedPolls().remove(poll);
        }
        polls.remove(id);
    }

    // VoteOption methods (for poll options)
    public VoteOption createVoteOption(VoteOption voteOption) {
        String id = UUID.randomUUID().toString();
        voteOption.setId(id);
        voteOptions.put(id, voteOption);
        return voteOption;
    }
}
