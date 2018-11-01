package com.start.blog.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Catalog 实体
 *
 */
@Data
@Entity // 实体
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "名称不能为空")
	@Size(min=2, max=30)
	@Column(nullable = false)
	private String name;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	protected Catalog() {
	}

	public Catalog(User user, String name) {
		this.name = name;
		this.user = user;
	}
	

 
 
}
