package com.shj.y18shj;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Finder;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.sqlite.FinderLazyLoader;

/**
 * Created by jash
 * Date: 15-7-17
 * Time: 下午4:20
 */
@Table(name = "EntryType")
public class EntryType {
    @Id(column = "id")
    @NoAutoIncrement
    private int id;
    @Column(column = "name")
    private String name;
    @Finder(valueColumn = "id", targetColumn = "type_id")
    private FinderLazyLoader<Entry> finder;
    public EntryType() {
    }

    public EntryType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FinderLazyLoader<Entry> getFinder() {
        return finder;
    }

    public void setFinder(FinderLazyLoader<Entry> finder) {
        this.finder = finder;
    }
}
