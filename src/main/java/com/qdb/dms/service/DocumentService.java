package com.qdb.dms.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.qdb.dms.response.DocumentUploadResponse;

public interface DocumentService {
	
	public DocumentUploadResponse uploadDocument(MultipartFile fileRequest) throws IOException;
	
	public byte[] downloadDocument(int docId) throws IOException;

}
