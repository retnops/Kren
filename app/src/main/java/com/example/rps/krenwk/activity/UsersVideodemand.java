package com.example.rps.krenwk.activity;


public class UsersVideodemand {

    String title,desc,thumbnail,url,create ;


    public UsersVideodemand(){

    }

    public UsersVideodemand(String title, String description, String thumbnail, String url, String createtime){
        this.title = title;
        this.desc = description;
        this.thumbnail = thumbnail;
        this.url = url;
        this.create = createtime;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatetime() { return create;    }

    public void setCreatetime(String createtime) { this.create = createtime; }
}
