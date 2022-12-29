package com.blogging.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogging.app.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date date; 
	
	private CategoryDTO category;
	
	private UserDTO user;

	private Set<CommentDTO> comments = new HashSet<>();
}
