package com.qdb.dms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qdb.dms.request.CommentRequest;
import com.qdb.dms.request.PostRequest;
import com.qdb.dms.response.CommentResponse;
import com.qdb.dms.response.DocumentUploadResponse;
import com.qdb.dms.response.PostResponse;

public interface DocumentService {
	
	public DocumentUploadResponse uploadDocument(MultipartFile fileRequest) throws IOException;
	
	public byte[] downloadDocument(int docId) throws IOException;

	public List<Integer> listDocuments();

	public PostResponse createPost(PostRequest request);

	public PostResponse retrievePostOfDocument(int docId);

	public CommentResponse createComments(CommentRequest request);

	public void deleteDocument(int docId);

}
