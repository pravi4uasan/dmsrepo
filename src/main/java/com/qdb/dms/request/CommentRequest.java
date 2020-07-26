package com.qdb.dms.request;

import java.util.List;

import lombok.Data;

@Data
public class CommentRequest {

	private int postId;
	private List<CommentRequestVO> comments;
	
}
