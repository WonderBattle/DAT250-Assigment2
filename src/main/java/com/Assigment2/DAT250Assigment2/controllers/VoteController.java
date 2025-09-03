package com.Assigment2.DAT250Assigment2.controllers;

import com.Assigment2.DAT250Assigment2.PollManager;
import com.Assigment2.DAT250Assigment2.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        return pollManager.createVote(vote);
    }

    @GetMapping
    public List<Vote> getAllVotes() {
        return pollManager.getAllVotes();
    }
}
