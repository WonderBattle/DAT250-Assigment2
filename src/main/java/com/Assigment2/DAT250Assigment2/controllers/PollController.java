package com.Assigment2.DAT250Assigment2.controllers;

import com.Assigment2.DAT250Assigment2.PollManager;
import com.Assigment2.DAT250Assigment2.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollManager pollManager;

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollManager.getAllPolls();
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollManager.createPoll(poll);
    }

    @DeleteMapping("/{id}")
    public void deletePoll(@PathVariable String id) {
        pollManager.deletePoll(id);
    }
}
