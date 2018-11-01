package com.start.blog.repository;


import com.start.blog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Vote Repository 接口.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
 
}
