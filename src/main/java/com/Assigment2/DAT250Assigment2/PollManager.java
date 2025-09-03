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
        if (poll != null) {
            // Remove poll from creator's created polls
            if (poll.getCreator() != null) {
                poll.getCreator().getCreatedPolls().remove(poll);
            }

            // Delete associated votes
            deleteVotesByPollId(id);

            // Delete associated vote options
            deleteVoteOptionsByPollId(id);

            // Finally remove the poll itself
            polls.remove(id);
        }
    }

    public void deleteVoteOptionsByPollId(String pollId) {
        // Remove vote options associated with a poll when it's deleted
        voteOptions.values().removeIf(voteOption ->
                voteOption.getPoll() != null &&
                        voteOption.getPoll().getId().equals(pollId)
        );
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

        // PROPERLY SET USER RELATIONSHIP
        if (vote.getUser() != null && vote.getUser().getId() != null) {
            User user = users.get(vote.getUser().getId());
            if (user != null) {
                vote.setUser(user); // Replace with full user object
                user.getVotes().add(vote); // Add to user's votes
            }
        }

        // PROPERLY SET VOTEOPTION RELATIONSHIP
        if (vote.getVoteOption() != null && vote.getVoteOption().getId() != null) {
            VoteOption voteOption = voteOptions.get(vote.getVoteOption().getId());
            if (voteOption != null) {
                vote.setVoteOption(voteOption); // Replace with full voteOption object
                // The vote is now properly connected to the voteOption and its poll
            }
        }

        votes.put(id, vote);
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

    public List<VoteOption> getAllVoteOptions() {
        return new ArrayList<>(voteOptions.values());
    }

}
