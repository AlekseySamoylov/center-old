package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.site.model.element.RatingTableData;
import com.alekseysamoylov.repair.center.site.model.form.RatingForm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/31/16.
 */
public interface RatingStorage {
    void saveRating(RatingForm ratingForm);
    List<RatingTableData> getRatingData();

}
