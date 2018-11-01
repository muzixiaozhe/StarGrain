package com.start.blog.repository;

import com.start.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Comment Repository接口
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
