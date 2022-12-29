package com.blogging.app.services;

import com.blogging.app.payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer postId);
    void deleteComment(int  commentId);
}
