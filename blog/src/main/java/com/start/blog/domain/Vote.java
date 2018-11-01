package com.start.blog.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * vote 实体
 */
@Data
@Entity // 实体
public class Vote implements Serializable {

	private static final long serialVersionUID = 8236311575318887168L;
	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createTime;

	protected Vote() {
	}
	
	public Vote(User user) {
		this.user = user;
	}
	

 
}
