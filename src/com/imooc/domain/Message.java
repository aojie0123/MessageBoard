package com.imooc.domain;

public class Message {

    private Integer mid;
    private Integer userId;
    private String username;
    private String title;
    private String content;
    private String creatTime;

    public Message() {
    }

    public Message(Integer mid, Integer userId, String username, String title, String content, String creatTime) {
        this.mid = mid;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.content = content;
        this.creatTime = creatTime;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mid=" + mid +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creatTime='" + creatTime + '\'' +
                '}';
    }
}
