package com.start.blog.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限实体
 */
@Data
@Entity
public class Authority implements GrantedAuthority,Serializable {

    private static final long serialVersionUID = -1888488541953339677L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)//映射为字段,值不能为空
    private String name;

    protected Authority() {
    }

    @Override
    public String getAuthority() {
       return name;
    }
}
