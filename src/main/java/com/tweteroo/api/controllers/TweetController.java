package com.tweteroo.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.TweetDTO;
import com.tweteroo.api.models.TweetModel;
import com.tweteroo.api.services.TweetService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweets")
public class TweetController {

    final TweetService tweetService;

    TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<TweetModel> createTweet(@RequestBody @Valid TweetDTO body) {
        Optional<TweetModel> tweet = tweetService.save(body);

        if (!tweet.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(tweet.get());

    }

    @GetMapping
    public ResponseEntity<List<TweetModel>> getAllTweets() {
        List<TweetModel> tweets = tweetService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tweets);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetModel>> getMethodName(@PathVariable Long userId) {
        List<TweetModel> tweets = tweetService.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(tweets);
    }

}
