package com.blogging.app.services.implementations;

import com.blogging.app.entities.Comment;
import com.blogging.app.entities.Post;
import com.blogging.app.exceptions.ResourceNotFoundException;
import com.blogging.app.payloads.CommentDTO;
import com.blogging.app.payloads.PostDTO;
import com.blogging.app.repositories.CommentRepository;
import com.blogging.app.repositories.PostRepository;
import com.blogging.app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post with id "+ postId + " not found")
        );
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        return modelMapper.map(
                commentRepository.save(comment),
                CommentDTO.class
        );
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found with ID = "+ commentId)
        );
        commentRepository.delete(comment);
    }
}
