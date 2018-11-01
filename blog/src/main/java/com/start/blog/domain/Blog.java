package com.start.blog.domain;

import com.github.rjeschke.txtmark.Processor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 *  博客实体
 */
@Data
@Entity
public class Blog implements Serializable{

    private static final long serialVersionUID = 670636716037555239L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增策略
    private Long id;

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2,max = 50)
    @Column(nullable = false,length = 50)
    private String title;   //标题

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2,max = 300)
    @Column(nullable = false)
    private String summary; //摘要

    @Lob //Long Text
    @NotEmpty(message = "内容不能为空")
    @Basic(fetch = FetchType.LAZY)//懒加载
    @Size(min = 2)
    @Column(nullable = false)
    private String content;

    @Lob
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min=2)
    @Column(nullable = false)
    private String htmlContent;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    @CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    @Column(name="readSize")
    private Integer readSize=0;   // 访问量

    @Column(name="roteSize")
    private Integer voteSize=0;   // 点赞量

    @Column(name="commentSize")
    private Integer commentSize=0;    //评论量

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="catalog_id")
    private Catalog catalog;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "blog_comment", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "blog_vote", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private List<Vote> votes;



    @Column(name = "tags", length = 100)
    @Size(max = 100, message = "关键词太长了")
    private String tags;  // 标签


    protected Blog() {
    }

    public Blog(String title, String summary,String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
        this.htmlContent = Processor.process(content);//将MakeDown转换为HTML格式
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        this.commentSize = this.comments.size();//修改评论量
    }

    /**
     * 添加评论
     * @param comment
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
        this.commentSize = this.comments.size();
    }
    /**
     * 删除评论
     * @param commentId
     */
    public void removeComment(Long commentId) {
        for (int index=0; index < this.comments.size(); index ++ ) {
            if (comments.get(index).getId() == commentId) {
                this.comments.remove(index);
                break;
            }
        }

        this.commentSize = this.comments.size();
    }

    /**
     * 点赞
     * @param vote
     * @return
     */
    public boolean addVote(Vote vote) {
        boolean isExist = false;
        // 判断重复
        for (int index=0; index < this.votes.size(); index ++ ) {
            if (this.votes.get(index).getUser().getId() == vote.getUser().getId()) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            this.votes.add(vote);
            this.voteSize = this.votes.size();
        }

        return isExist;
    }
    /**
     * 取消点赞
     * @param voteId
     */
    public void removeVote(Long voteId) {
        for (int index=0; index < this.votes.size(); index ++ ) {
            if (this.votes.get(index).getId() == voteId) {
                this.votes.remove(index);
                break;
            }
        }

        this.voteSize = this.votes.size();
    }
}
