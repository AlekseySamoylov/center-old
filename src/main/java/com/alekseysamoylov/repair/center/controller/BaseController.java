package com.alekseysamoylov.repair.center.controller;

import com.alekseysamoylov.repair.center.model.exceptions.UserRatingException;
import com.alekseysamoylov.repair.center.model.exceptions.WrongUserAccessException;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alekseysamoylov on 7/29/16.
 */
@ControllerAdvice
public class BaseController {

    private final Logger logger = Logger.getLogger(BaseController.class);

    @ModelAttribute("isManager")
    public boolean isManager(Authentication auth) {
        return auth != null &&
                auth.getAuthorities().contains(AuthorityUtils.createAuthorityList("ROLE_MANAGER").get(0));
    }

    @ModelAttribute("isClient")
    public boolean isClient(Authentication auth) {
        return auth != null &&
                auth.getAuthorities().contains(AuthorityUtils.createAuthorityList("ROLE_CLIENT").get(0));
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin(Authentication auth) {
        return auth != null &&
                ((WebUserInformation) auth.getPrincipal()).getUsername().equals("SAMOYLOV");
//                auth.getAuthorities().contains(AuthorityUtils.createAuthorityList("ROLE_ADMIN").get(0));
    }

    @ExceptionHandler(UserRatingException.class)
    public String goUserError( UserRatingException ex) throws Exception {
        logger.info("Request: raised " + ex);
        return "client-wrong-rating";

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Model model, Exception ex) throws Exception {
            logger.error("Request: " + req.getRequestURL() + " raised " + ex);
            logger.error(ex.getStackTrace());

            model.addAttribute("exception", ex);
            model.addAttribute("url", req.getRequestURL());

            return "error";
    }


}
