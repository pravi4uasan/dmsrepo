package com.qdb.dms.response;

import lombok.Data;

@Data
public class PostResponse {

	private int docId;
	private int postId;
	private String title;
	private String body;
	
}
