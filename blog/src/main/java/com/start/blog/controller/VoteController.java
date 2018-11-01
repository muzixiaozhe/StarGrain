package com.start.blog.controller;


import com.start.blog.VO.ResultVO;
import com.start.blog.domain.User;
import com.start.blog.exception.ConstraintViolationExceptionHandler;
import com.start.blog.service.BlogService;
import com.start.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;


/**
 * 点赞控制器.
 */
@Controller
@RequestMapping("/votes")
public class VoteController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private VoteService voteService;

	/**
	 * 点赞
	 * @param blogId
	 * @return
	 */
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
	public ResponseEntity<ResultVO> createVote(Long blogId) {
 
		try {
			blogService.createVote(blogId);
		} catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new ResultVO(false, ConstraintViolationExceptionHandler.getMessages(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new ResultVO(false, e.getMessage()));
		}
		
		return ResponseEntity.ok().body(new ResultVO(true, "点赞成功", null));
	}

	/**
	 * 取消点赞
	 * @param id
	 * @param blogId
	 * @return
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
	public ResponseEntity<ResultVO> delete(@PathVariable("id") Long id, Long blogId) {
		
		boolean isOwner = false;
		User user = voteService.getVoteById(id).getUser();
		
		// 判断操作用户是否是点赞的所有者
		if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				 &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal !=null && user.getUsername().equals(principal.getUsername())) {
				isOwner = true;
			} 
		} 
		
		if (!isOwner) {
			return ResponseEntity.ok().body(new ResultVO(false, "没有操作权限"));
		}
		
		try {
			blogService.removeVote(blogId, id);
			voteService.removeVote(id);
		} catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new ResultVO(false, ConstraintViolationExceptionHandler.getMessages(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new ResultVO(false, e.getMessage()));
		}
		
		return ResponseEntity.ok().body(new ResultVO(true, "取消点赞成功", null));
	}
}
