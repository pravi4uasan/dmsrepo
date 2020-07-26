package com.qdb.dms.response;

import java.util.List;

import lombok.Data;

@Data
public class CommentResponse {

	private List<CommentResponseVO> comments;
	
}
