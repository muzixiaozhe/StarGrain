package com.start.blog.service.impl;

import com.start.blog.domain.Vote;
import com.start.blog.repository.VoteRepository;
import com.start.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void removeVote(Long id) {

        voteRepository.deleteById(id);
    }
}
