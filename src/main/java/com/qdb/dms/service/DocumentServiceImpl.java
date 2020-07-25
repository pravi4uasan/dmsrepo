package com.qdb.dms.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.qdb.dms.entity.Document;
import com.qdb.dms.repository.DocumentRepository;
import com.qdb.dms.response.DocumentUploadResponse;

@Component
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;

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

}
