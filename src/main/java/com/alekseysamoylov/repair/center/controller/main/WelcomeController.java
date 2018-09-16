package com.alekseysamoylov.repair.center.controller.main;

import com.alekseysamoylov.repair.center.service.PurchaseLogService;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import com.alekseysamoylov.repair.center.site.service.util.FillWebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
@Controller
@RequestMapping(value = "/")
public class WelcomeController {


    private final PurchaseLogService purchaseLogService;

    @Autowired
    public WelcomeController(PurchaseLogService purchaseLogService) {
        this.purchaseLogService = purchaseLogService;
    }


    @RequestMapping(value = "/")
    public String goWelcome() {
        return "welcome";
    }

    @RequestMapping(value = "/user-page")
    public String goUserPage(Model model) {
        WebUserInformation userInformation =
                (WebUserInformation) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
        purchaseLogService.log("Enter to the application "
                + userInformation.getFirstName() + " "
                + userInformation.getLastName()
                + " from company "
                + userInformation.getCompanyId());
        model.addAttribute("userInfo", userInformation);
//        if (userInformation.getRole().equals("ROLE_MANAGER")) return "redirect:/service-panel/";
        return "user-page";
    }

    @RequestMapping(value = "/user-page/select-company/{companyId}", method = RequestMethod.GET)
    public String selectCompany(Model model, @PathVariable Long companyId) {
        WebUserInformation userInformation =
                (WebUserInformation) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
        userInformation.getCompanies().stream()
                .filter(company -> companyId
                        .equals(company.getId()))
                            .forEach(company -> {
                                userInformation.setCompanyId(companyId);
                                ((WebUserInformation)SecurityContextHolder
                                        .getContext().getAuthentication()
                                        .getPrincipal()).setCompanyId(companyId);
                            });
        model.addAttribute("userInfo", userInformation);
        return "user-page";
    }

    @RequestMapping(value = "/admin")
//    @PreAuthorize(value = "principal.username == 'SAMOYLOV'")
    public String goAdminPanel(Model model) {
        if (((WebUserInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().equals("SAMOYLOV")){
            model.addAttribute("logs", purchaseLogService.getLogList());
            return "admin";
        } else return "redirect:/403";

    }

    @RequestMapping(value = "/403")
    public String goErrorPage() throws Exception  {
        return "403";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goLogin() {
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

}
