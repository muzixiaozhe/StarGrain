package com.start.blog.service;

import com.start.blog.domain.Authority;

/**
 * Authority 服务接口
 */
public interface AuthorityService {

    /**
     * 根据id查询Authority
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
