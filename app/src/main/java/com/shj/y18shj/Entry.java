package com.shj.y18shj;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by jash
 * Date: 15-7-17
 * Time: 下午4:44
 */
 //这个实体类作用是展示详情页面
@Table(name = "Entry")
public class Entry {
    @Id(column = "id")
    @NoAutoIncrement
    private int id;
    @Column(column = "title")
    private String title;
    @Column(column = "img")
    private String img;
    @Column(column = "count")
    private int count;
    @Column(column = "author")
    private String author;
    @Column(column = "time")
    private String time;
    @Foreign(column = "type_id", foreign = "id")//因为设置的有外键，所以他应该是根据type中的id获取数据
    private EntryType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }
}
