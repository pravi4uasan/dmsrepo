package com.qdb.dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qdb.dms.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
