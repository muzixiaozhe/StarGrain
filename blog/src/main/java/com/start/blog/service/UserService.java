package com.start.blog.service;

import com.start.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserService {

    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 新增修改用户
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 根据用户名分页模糊查询
     * @param username
     * @param pageable
     * @return
     */
    Page<User> findByUserNameLikePage(String username, Pageable pageable);

    /**
     * 根据名称列表查询
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(Collection<String> usernames);
}
