package com.Assigment2.DAT250Assigment2.controllers;

import com.Assigment2.DAT250Assigment2.PollManager;
import com.Assigment2.DAT250Assigment2.model.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/voteoptions")
public class VoteOptionController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public VoteOption createVoteOption(@RequestBody VoteOption voteOption) {
        return pollManager.createVoteOption(voteOption);
    }

    @GetMapping
    public List<VoteOption> getAllVoteOptions() {
        // We'll implement this later if needed
        return List.of();
    }
}
