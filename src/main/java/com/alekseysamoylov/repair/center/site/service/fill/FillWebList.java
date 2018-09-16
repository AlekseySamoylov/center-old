package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.site.model.WebTable;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public interface FillWebList<T> {
    /**
     * see concrete realisation
     * @return maybe null
     */
    WebTable<T> getFilledWebList();

    /**
     * see concrete realisation
     * @return maybe null
     */
    WebTable<T> getFilledWebList(Long id);
}
