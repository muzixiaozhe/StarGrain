package com.start.blog.service.impl;

import com.start.blog.domain.Comment;
import com.start.blog.repository.CommentRepository;
import com.start.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * CommentService 接口实现
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void removeComment(Long id) {
        commentRepository.deleteById(id);
    }
}
