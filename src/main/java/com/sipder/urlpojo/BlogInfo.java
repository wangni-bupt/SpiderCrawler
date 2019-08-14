package com.sipder.urlpojo;
/*
 *  实体类
 *  存储所需的数据
 */
public class BlogInfo {
    private String url;
    private String title;
    private String author;
    private String readNum;
    private String recommendNum;
    private String blogHomeUrl;
    private String commentNum;
    private String publishTime;
    private String content;
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(String recommendNum) {
        this.recommendNum = recommendNum;
    }

    public String getBlogHomeUrl() {
        return blogHomeUrl;
    }

    public void setBlogHomeUrl(String blogHomeUrl) {
        this.blogHomeUrl = blogHomeUrl;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
