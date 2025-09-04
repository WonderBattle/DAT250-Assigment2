package com.Assigment2.DAT250Assigment2.controllers;

import com.Assigment2.DAT250Assigment2.PollManager;
import com.Assigment2.DAT250Assigment2.model.VoteOption;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/voteoptions")
@Tag(name = "Vote Options", description = "Vote option management APIs") // Step 6: API Documentation
public class VoteOptionController {

    @Autowired
    private PollManager pollManager;

    @Operation(summary = "Create a new vote option", description = "Creates a new vote option and returns it") // Step 6: API Documentation
    @PostMapping
    public VoteOption createVoteOption(@RequestBody VoteOption voteOption) {
        return pollManager.createVoteOption(voteOption);
    }

    @Operation(summary = "Get all vote options", description = "Returns a list of all vote options") // Step 6: API Documentation
    @GetMapping
    public List<VoteOption> getAllVoteOptions() {
        return pollManager.getAllVoteOptions();
    }
}
