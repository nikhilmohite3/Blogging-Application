package com.blogging.app.payloads;

import java.util.Date;

import com.blogging.app.entities.Category;
import com.blogging.app.entities.User;

public class PostDTO {
private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date date; 
	
	private CategoryDTO category;
	
	private UserDTO user;

	public PostDTO(int postId, String title, String content, String imageName, Date date, CategoryDTO category,
			UserDTO user) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.date = date;
		this.category = category;
		this.user = user;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
