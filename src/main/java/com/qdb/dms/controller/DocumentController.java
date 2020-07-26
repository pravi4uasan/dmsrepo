package com.qdb.dms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qdb.dms.request.CommentRequest;
import com.qdb.dms.request.PostRequest;
import com.qdb.dms.response.CommentResponse;
import com.qdb.dms.response.DocumentUploadResponse;
import com.qdb.dms.response.PostResponse;
import com.qdb.dms.service.DocumentService;

@RestController
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public DocumentUploadResponse uploadDocument(@RequestPart("file") MultipartFile fileRequest) throws IOException {

		return documentService.uploadDocument(fileRequest);
		
	}

	@GetMapping(value = "/documents/{id}", produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadDocument(@PathVariable("id") int docId) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.parseMediaType("application/pdf"));
		  headers.add("Access-Control-Allow-Origin", "*");
		  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		  headers.add("Access-Control-Allow-Headers", "Content-Type");
		  headers.add("Content-Disposition", "filename=document");
		  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		  headers.add("Pragma", "no-cache");
		  headers.add("Expires", "0");
		 
		  byte[] content = documentService.downloadDocument(docId);
		  InputStream targetStream = new ByteArrayInputStream(content);
		  
		  if(content != null) {
			  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
					  new InputStreamResource(targetStream), headers, HttpStatus.OK);
			  return response;
		  }
		  
		return null;
	}
	
	@GetMapping(value = "/documents")
	public List<Integer> listDocuments() {
		return documentService.listDocuments();
	}
	
	@DeleteMapping(value = "/documents/{docId}")
	public void deleteDocument(@PathVariable(value = "docId") int docId) {
		documentService.deleteDocument(docId);
	}
	
	@PostMapping(value = "/documents/posts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PostResponse createPost(@RequestBody PostRequest request) {
		
		return documentService.createPost(request);
		
	}
	
	@GetMapping(value = "/documents/{docId}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostResponse retrievePostOfDocument(@PathVariable(name = "docId") int docId) {
		
		return documentService.retrievePostOfDocument(docId);
		
	}
	
	
	@PostMapping(value = "/documents/posts/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CommentResponse createComments(@RequestBody CommentRequest request) {
		
		return documentService.createComments(request);
		
	}
	
	

}
