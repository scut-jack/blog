package online.blog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 博客实体类, 和数据库交互(包含数据库不存在的字段)
 * @date 2022/04/17
 */
public class Blog {

    private Long id;// 主键，标识不同博客
    private String title;// 博客标题
    private String content;// 博客内容
    private String firstPicture;// 博客封面图片
    private String flag;
    private Integer views;// 博客被查看次数
    private Integer commentCount;// 博客的评论数量
    private boolean appreciation;// 是否开启博客赞赏
    private boolean shareStatement;// 是否开启转载声明
    private boolean commentabled;// 是否开启博客下评论栏
    private boolean published;// 是否发布，后台博客状态：发布，草稿
    private boolean recommend;// 是否推荐（即是否展示在前端推荐栏）
    private Date createTime;// 创建时间
    private Date updateTime;// 更新时间
    private Long typeId;// 分类ID，关联分类表
    private Long userId;// 用户ID，关联用户表
    private String description;// 博客简介，首页展示
    private Type type;// 该博客所属分类
    private User user;// 该博客的创建者
    private List<Comment> comments = new ArrayList<>();// 博客的所有评论

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", commentCount=" + commentCount +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}