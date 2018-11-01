package com.start.blog.domain.es;

import com.start.blog.domain.Blog;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Blog.
 */
@Data
@Document(indexName = "blog", type = "blog")
@XmlRootElement // MediaType 转为 XML
public class EsBlog implements Serializable {
 
	private static final long serialVersionUID = 1L;


	@Id  // 主键
	private String id;  
	@Field(index = false,fielddata = true)
	private Long blogId; // Blog 的 id
 
	private String title;
 
	private String summary;
 
	private String content;

	@Field(index = false,fielddata = true)  // 不做全文检索字段
	private String username;
	@Field(index = false,fielddata = true) // 不做全文检索字段
	private String avatar;
	@Field(index = false,fielddata = true)  // 不做全文检索字段
	private Timestamp createTime;
	@Field(index = false,fielddata = true)  // 不做全文检索字段
	private Integer readSize = 0; // 访问量、阅读量
	@Field(index = false,fielddata = true)  // 不做全文检索字段
	private Integer commentSize = 0;  // 评论量
	@Field(index = false,fielddata = true)  // 不做全文检索字段
	private Integer voteSize = 0;  // 点赞量

	@Field(fielddata = true)
	private String tags;  // 标签

	protected EsBlog() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
	}

	public EsBlog(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public EsBlog(Long blogId, String title, String summary, String content, String username, String avatar, Timestamp createTime,
                  Integer readSize, Integer commentSize, Integer voteSize , String tags) {
		this.blogId = blogId;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.username = username;
		this.avatar = avatar;
		this.createTime = createTime;
		this.readSize = readSize;
		this.commentSize = commentSize;
		this.voteSize = voteSize;
		this.tags = tags;
	}
	
	public EsBlog(Blog blog){
		this.blogId = blog.getId();
		this.title = blog.getTitle();
		this.summary = blog.getSummary();
		this.content = blog.getContent();
		this.username = blog.getUser().getUsername();
		this.avatar = blog.getUser().getAvatar();
		this.createTime = blog.getCreateTime();
		this.readSize = blog.getReadSize();
		this.commentSize = blog.getCommentSize();
		this.voteSize = blog.getVoteSize();
		this.tags = blog.getTags();
	}
 
	public void update(Blog blog){
		this.blogId = blog.getId();
		this.title = blog.getTitle();
		this.summary = blog.getSummary();
		this.content = blog.getContent();
		this.username = blog.getUser().getUsername();
		this.avatar = blog.getUser().getAvatar();
		this.createTime = blog.getCreateTime();
		this.readSize = blog.getReadSize();
		this.commentSize = blog.getCommentSize();
		this.voteSize = blog.getVoteSize();
		this.tags = blog.getTags();
	}
 
	

	@Override
    public String toString() {
        return String.format(
                "User[id=%d, title='%s', content='%s']",
                blogId, title, content);
    }
}
