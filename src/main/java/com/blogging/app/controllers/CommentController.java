package com.blogging.app.controllers;

import com.blogging.app.payloads.CommentDTO;
import com.blogging.app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<?> createComment(
            @RequestBody CommentDTO comment,
            @PathVariable Integer postId
    ) {
        return new ResponseEntity<>(
                commentService.createComment(comment, postId),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Integer commentId
    ) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
