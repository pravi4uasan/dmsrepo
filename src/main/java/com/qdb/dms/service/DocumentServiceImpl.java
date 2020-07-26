package com.qdb.dms.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.qdb.dms.entity.Comment;
import com.qdb.dms.entity.Document;
import com.qdb.dms.entity.Post;
import com.qdb.dms.repository.DocumentRepository;
import com.qdb.dms.repository.PostRepository;
import com.qdb.dms.request.CommentRequest;
import com.qdb.dms.request.CommentRequestVO;
import com.qdb.dms.request.PostRequest;
import com.qdb.dms.response.CommentResponse;
import com.qdb.dms.response.CommentResponseVO;
import com.qdb.dms.response.DocumentUploadResponse;
import com.qdb.dms.response.PostResponse;

@Component
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public DocumentUploadResponse uploadDocument(MultipartFile fileRequest) throws IOException {
		
		byte[] docValue = fileRequest.getBytes();
		Document doc = new Document();
		doc.setDocObj(docValue);
		doc = documentRepository.save(doc);
		DocumentUploadResponse response = new DocumentUploadResponse();
		response.setDocId(doc.getDocId());
		return response;
		
	}

	@Override
	public byte[] downloadDocument(int docId) throws IOException {
		
		Optional<Document> doc = documentRepository.findById(docId);
		
		if(doc.isPresent()) {
			Document document = doc.get();
			return document.getDocObj();
		}
		
		return null;
	}

	@Override
	public List<Integer> listDocuments() {
		List<Document> docList = documentRepository.findAll();
		if(!CollectionUtils.isEmpty(docList)) {
			return docList.stream()
				.map(doc -> doc.getDocId())
				.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public PostResponse createPost(PostRequest request) {
		
		Optional<Document> doc = documentRepository.findById(request.getDocId());
		
		if(doc.isPresent()) {
			Post post = new Post();
			post.setTitle(request.getTitle());
			post.setBody(request.getBody());
			Document document = doc.get();
			document.setPost(post);
			document = documentRepository.save(document);
			
			PostResponse response = new PostResponse();
			response.setDocId(document.getDocId());
			response.setPostId(document.getPost().getId());
			response.setTitle(document.getPost().getTitle());
			response.setBody(document.getPost().getBody());
			
			return response;
		}
		
		return null;
	}

	@Override
	public PostResponse retrievePostOfDocument(int docId) {
		
		Optional<Document> doc = documentRepository.findById(docId);
		if(doc.isPresent()) {
			Document document = doc.get();
			Post post = document.getPost();
			PostResponse response = new PostResponse();
			response.setBody(post.getBody());
			response.setDocId(post.getDocument().getDocId());
			response.setPostId(post.getId());
			response.setTitle(post.getTitle());
			return response;
		}
		return null;
		
	}

	@Override
	public CommentResponse createComments(CommentRequest request) {
		
		Post post = postRepository.findById(request.getPostId()).orElse(null);
		
		if(post != null) {
			
			List<CommentRequestVO> comments = request.getComments();
			List<Comment> commentList = comments.stream()
				.map(c -> {
					Comment comment = new Comment();
					comment.setBody(c.getBody());
					comment.setEmail(c.getEmail());
					comment.setName(c.getName());
					return comment;
				})
				.collect(Collectors.toList());
			post.setComments(commentList);
			post = postRepository.save(post);
			
			CommentResponse response = new CommentResponse();
			
			List<CommentResponseVO> commentsResponseList = post.getComments()
				.stream()
				.map(c -> {
					CommentResponseVO crvo = new CommentResponseVO();
					crvo.setBody(c.getBody());
					crvo.setEmail(c.getEmail());
					crvo.setName(c.getName());
					crvo.setId(c.getId());
					crvo.setPostId(request.getPostId());
					return crvo;
				})
				.collect(Collectors.toList());
			
			response.setComments(commentsResponseList);
			
			return response;
		}
		
		return null;
	}

}
