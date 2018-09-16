package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderRating;
import com.alekseysamoylov.repair.center.model.exceptions.UserRatingException;
import com.alekseysamoylov.repair.center.model.exceptions.WrongUserAccessException;
import com.alekseysamoylov.repair.center.site.model.form.RatingForm;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by alekseysamoylov on 7/31/16.
 */
public class RatingStorageImplTest extends AbstractTest {

    RatingForm ratingForm;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Autowired
    @Qualifier("orderRatingDaoImpl")
    EntityDao<OrderRating> orderRatingEntityDao;

    @Autowired
    RatingStorage ratingStorage;

    @Before
    public void setUp() throws Exception {
        ratingForm = new RatingForm();
        ratingForm.setId(2L);
        ratingForm.setStars(4);
        ratingForm.setComment("All perfect");
    }

    @Test
    public void saveRating() {
        ratingStorage.saveRating(ratingForm);
        System.out.println(orderRatingEntityDao.get(2L));
        System.out.println(orderRatingEntityDao.get(1L));
        Assert.isTrue(orderRatingEntityDao.get(2L).getRating() == 4);
        ratingForm.setStars(1);
        thrown.expect(UserRatingException.class);
        thrown.expectMessage(equalTo("Wrong access to setting rating from user"));
        ratingStorage.saveRating(ratingForm);
    }

    @Test
    public void getRatingData() throws Exception {

    }

}