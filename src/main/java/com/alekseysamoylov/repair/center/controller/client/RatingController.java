package com.alekseysamoylov.repair.center.controller.client;

import com.alekseysamoylov.repair.center.site.model.form.RatingForm;
import com.alekseysamoylov.repair.center.site.service.util.RatingStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alekseysamoylov on 7/30/16.
 */
@Controller
public class RatingController {

    private RatingStorage ratingStorage;

    @Autowired
    public RatingController(RatingStorage ratingStorage) {
        this.ratingStorage = ratingStorage;
    }

    @RequestMapping(value = "/service-panel/rating/{orderId}")
    public String goToGetRating(@PathVariable Long orderId, Model model) {
        model.addAttribute("ratingForm", new RatingForm(orderId));
        return "client-rating";
    }

    @RequestMapping(value = "/rating/save", method = RequestMethod.POST)
    public String saveRating(@ModelAttribute RatingForm ratingForm) {
        ratingStorage.saveRating(ratingForm);
        return "client-thanks";
    }

}
