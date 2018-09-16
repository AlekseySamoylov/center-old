package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.WebTable;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public abstract class ParentTable<T> implements WebTable<T> {
    protected List<T> webList;

    public List<T> getWebList() {
        return webList;
    }

    public void setWebList(List<T> webList) {
        this.webList = webList;
    }

    public List<T> search(String string) {
        return null;
    }

}
