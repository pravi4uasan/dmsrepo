package com.qdb.dms.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qdb.dms.response.DocumentUploadResponse;

@RestController
public class DocumentController {
	
	@PostMapping(value = "/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public DocumentUploadResponse uploadDocument(@RequestPart("file") MultipartFile fileRequest) {
		
		
		
		return null;
	}
	
	
}
