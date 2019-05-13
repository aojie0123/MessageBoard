package com.imooc.domain;

import java.util.List;

public class Page {
    private int currentPage;    //  当前页面
    private int totalPage;      //  总页数
    private int limit = 10;     //  每页显示数量
    List<Message> list;         //  消息列表

    public Page() {
    }

    public Page(int currentPage, int totalPage, int limit, List<Message> list) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.limit = limit;
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", limit=" + limit +
                ", list=" + list +
                '}';
    }
}
