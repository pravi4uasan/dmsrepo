package com.qdb.dms.response;

import lombok.Data;

@Data
public class CommentResponseVO {

	private int postId;
	private int id;
	private String name;
	private String email;
	private String body;
	
}
