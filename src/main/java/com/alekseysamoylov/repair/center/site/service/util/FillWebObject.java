package com.alekseysamoylov.repair.center.site.service.util;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
public interface FillWebObject<T, V> {
    T getFilledWebObject(V param);
}
