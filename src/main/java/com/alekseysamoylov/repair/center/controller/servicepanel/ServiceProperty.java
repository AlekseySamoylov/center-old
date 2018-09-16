package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import com.alekseysamoylov.repair.center.site.service.util.FillWebObject;
import com.alekseysamoylov.repair.center.site.service.util.WebCompanyStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by alekseysamoylov on 7/21/16.
 */
@Controller
public class ServiceProperty {

    private final FillWebObject<WebCompany, Long> companyInformationFill;
    private final WebCompanyStorage webCompanyStorage;

    @Autowired
    public ServiceProperty(
            WebCompanyStorage webCompanyStorage,
            @Qualifier("webCompanyInformationFill")
                    FillWebObject<WebCompany, Long> companyInformationFill) {
        this.webCompanyStorage = webCompanyStorage;
        this.companyInformationFill = companyInformationFill;
    }

    @RequestMapping(value = "/service-property")
    public String goServiceProperty(Authentication auth, Model model) {
        Long companyId = ((WebUserInformation) auth.getPrincipal()).getCompanyId();
        WebCompany webCompany = companyInformationFill.getFilledWebObject(companyId);
        model.addAttribute("webCompany", webCompany);
        return "service-property";
    }

    @RequestMapping(value = "/service-property/save", method = RequestMethod.POST)
    public String saveServiceProperty(@ModelAttribute WebCompany webCompany) {
        WebUserInformation principal = ((WebUserInformation)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal());
        principal.getCompanies().set(principal.getCompanies().indexOf(webCompany), webCompany);
        webCompanyStorage.saveWebCompany(webCompany);
        return "redirect:/";
    }
}
