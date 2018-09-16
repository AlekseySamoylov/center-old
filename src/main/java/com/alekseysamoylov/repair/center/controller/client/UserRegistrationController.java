package com.alekseysamoylov.repair.center.controller.client;

import com.alekseysamoylov.repair.center.site.service.util.WebClientFullStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by alekseysamoylov on 8/13/16.
 */
@Controller
public class UserRegistrationController {

    private final WebClientFullStorage webClientFullStorage;

    @Autowired
    public UserRegistrationController(WebClientFullStorage webClientFullStorage) {
        this.webClientFullStorage = webClientFullStorage;
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String goRegistrationUser() {
        return "create-user";
    }

    @CrossOrigin
    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public String getUserJson() {
        System.out.println("Get JSON User");
        return "client-page";
    }

    @CrossOrigin
    @RequestMapping(value = "/login-list")
    @ResponseBody
    public List<String> getLoginList() {
        return webClientFullStorage.getAllLogins();
    }


}
