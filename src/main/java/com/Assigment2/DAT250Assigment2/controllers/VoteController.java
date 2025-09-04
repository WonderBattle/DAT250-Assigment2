package com.Assigment2.DAT250Assigment2.controllers;

import com.Assigment2.DAT250Assigment2.PollManager;
import com.Assigment2.DAT250Assigment2.model.Vote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/votes")
@Tag(name = "Votes", description = "Vote management APIs") // Step 6: API Documentation
public class VoteController {

    @Autowired
    private PollManager pollManager;

    @Operation(summary = "Create a new vote", description = "Creates a new vote and returns it") // Step 6: API Documentation
    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        return pollManager.createVote(vote);
    }

    @Operation(summary = "Get all votes", description = "Returns a list of all votes") // Step 6: API Documentation
    @GetMapping
    public List<Vote> getAllVotes() {
        return pollManager.getAllVotes();
    }
}
