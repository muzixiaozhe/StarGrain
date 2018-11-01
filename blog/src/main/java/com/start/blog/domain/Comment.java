package com.start.blog.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 评论实体
 */
@Data
@Entity
public class Comment implements Serializable{

    private static final long serialVersionUID = 2221823573448678852L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
    private Long id;

    @NotEmpty(message = "评论内容不能为空")
    @Size(min=2, max=500)
    @Column(nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createTime;

    protected Comment() {
    }
    public Comment(User user, String content) {
        this.content = content;
        this.user = user;
    }
}
