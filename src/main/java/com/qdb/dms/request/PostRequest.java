package com.qdb.dms.request;

import lombok.Data;

@Data
public class PostRequest {

	private int docId;
	private String title;
	private String body;
	
}
