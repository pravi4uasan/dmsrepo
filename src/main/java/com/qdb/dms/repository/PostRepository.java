package com.qdb.dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qdb.dms.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
