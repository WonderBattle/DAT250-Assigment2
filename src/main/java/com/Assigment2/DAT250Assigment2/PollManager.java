package com.Assigment2.DAT250Assigment2;

import com.Assigment2.DAT250Assigment2.model.User;
import com.Assigment2.DAT250Assigment2.model.Poll;
import com.Assigment2.DAT250Assigment2.model.Vote;
import com.Assigment2.DAT250Assigment2.model.VoteOption;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class PollManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Poll> polls = new HashMap<>(); // We'll fix this later
    private final Map<String, Vote> votes = new HashMap<>(); // We'll fix this later
    private final Map<String, VoteOption> voteOptions = new HashMap<>();

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

    // Poll methods
    public Poll createPoll(Poll poll) {
        String id = UUID.randomUUID().toString();
        poll.setId(id);

        // FIX: Look up the full user object if only ID is provided
        if (poll.getCreator() != null && poll.getCreator().getId() != null) {
            User fullUser = users.get(poll.getCreator().getId());
            if (fullUser != null) {
                poll.setCreator(fullUser); // Replace with complete user object
            }
        }

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
        deleteVotesByPollId(id); // Delete associated votes
    }

    // VoteOption methods (for poll options)
    public VoteOption createVoteOption(VoteOption voteOption) {
        String id = UUID.randomUUID().toString();
        voteOption.setId(id);
        voteOptions.put(id, voteOption);
        return voteOption;
    }

    // Helper method to find users by ID
    public User findUserById(String userId) {
        return users.get(userId);
    }

    // Vote methods
    public Vote createVote(Vote vote) {
        String id = UUID.randomUUID().toString();
        vote.setId(id);
        vote.setPublishedAt(String.valueOf(System.currentTimeMillis()));
        votes.put(id, vote);

        // Add vote to user's vote history
        if (vote.getUser() != null) {
            vote.getUser().getVotes().add(vote);
        }

        // Add vote to poll's votes
        if (vote.getVoteOption() != null && vote.getVoteOption().getPoll() != null) {
            vote.getVoteOption().getPoll().getVotes().add(vote);
        }

        return vote;
    }

    public List<Vote> getAllVotes() {
        return new ArrayList<>(votes.values());
    }

    public void deleteVotesByPollId(String pollId) {
        // Remove votes associated with a poll when it's deleted
        votes.values().removeIf(vote ->
                vote.getVoteOption() != null &&
                        vote.getVoteOption().getPoll() != null &&
                        vote.getVoteOption().getPoll().getId().equals(pollId)
        );
    }


}
