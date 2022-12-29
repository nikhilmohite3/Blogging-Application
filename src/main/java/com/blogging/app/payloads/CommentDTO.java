package com.blogging.app.payloads;

import com.blogging.app.entities.Post;
import com.blogging.app.entities.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {
    private int id;
    private String content;

}
