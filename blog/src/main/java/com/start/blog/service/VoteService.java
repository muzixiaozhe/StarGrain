package com.start.blog.service;


import com.start.blog.domain.Vote;


public interface VoteService {

	/**
	 * 根据id获取 Vote
	 * @param id
	 * @return
	 */
	Vote getVoteById(Long id);

	/**
	 * 根据id删除Vote
	 * @param id
	 * @return
	 */
	void removeVote(Long id);
}
