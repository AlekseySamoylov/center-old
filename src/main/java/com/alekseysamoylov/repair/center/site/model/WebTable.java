package com.alekseysamoylov.repair.center.site.model;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public interface WebTable<T> {
    List<T> getWebList();
    void setWebList(List<T> webElementList);
    List<T> search(String string);
}
