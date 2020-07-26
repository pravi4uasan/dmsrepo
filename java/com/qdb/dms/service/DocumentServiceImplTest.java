package com.qdb.dms.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.qdb.dms.entity.Document;
import com.qdb.dms.repository.DocumentRepository;
import com.qdb.dms.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@Slf4j
public class DocumentServiceImplTest {
	
	@InjectMocks
	DocumentServiceImpl service;
	
	@Mock
	DocumentRepository documentRepository;
	
	@Mock
	private PostRepository postRepository;
	
	@Test
	public void listDocumentsTest() {
		
		try {
			List<Document> docList = new ArrayList<>();
			Document d1 = new Document();
			d1.setDocId(1);
			docList.add(d1);
			when(documentRepository.findAll()).thenReturn(docList);
			List<Integer> docs = service.listDocuments();
			Assert.notEmpty(docs, "shud not be empty");
			
		} catch (Exception e) {
			log.error("Exception in listDocumentsTest", e);
		}
	}
	
	

}
