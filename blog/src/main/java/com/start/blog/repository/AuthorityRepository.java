package com.start.blog.repository;

import com.start.blog.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority Repository接口
 */
public interface AuthorityRepository extends JpaRepository<Authority,Long>{
}
