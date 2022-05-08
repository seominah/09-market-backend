package com.gonggu.market.api.controller;

import com.gonggu.market.api.dto.feed.FeedUploadDto;
import com.gonggu.market.api.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    public FeedController(@Autowired FeedService feedService) {
        this.feedService = feedService;
    }

    @PostMapping
    public ResponseEntity<FeedUploadDto> feedUpload(@RequestBody FeedUploadDto dto, @RequestHeader("Authorization") String token) {
        if (dto.getUrl().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        FeedUploadDto result = feedService.feedUpload(dto, token);
        return ResponseEntity.ok(result);
    }
}