package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.element.*;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import com.alekseysamoylov.repair.center.site.service.util.WebOrderStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
@Controller
@RequestMapping(value = "/service-panel")
public class CreateOrderController {


    private final FillWebList<WebClient> fillClientList;

    private final FillWebList<WebClientCar> fillClientCarList;

    private final FillWebList<WebCarMark> fillCarMarkList;

    private final WebOrderStorage webOrderStorage;

    @Autowired
    public CreateOrderController(@Qualifier("fillClientList") FillWebList<WebClient> fillClientList,
                                 @Qualifier("fillCarMarkList") FillWebList<WebCarMark> fillCarMarkList,
                                 WebOrderStorage webOrderStorage,
                                 @Qualifier("fillClientCarList") FillWebList<WebClientCar> fillClientCarList) {
        this.fillClientList = fillClientList;
        this.fillCarMarkList = fillCarMarkList;
        this.webOrderStorage = webOrderStorage;
        this.fillClientCarList = fillClientCarList;
    }


    @RequestMapping(value = "/create")
    @Secured("ROLE_MANAGER")
    public String goSelectClient(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        WebOrderTemplate webOrderTemplate = new WebOrderTemplate();
        webOrderTemplate.setManagerId(((WebUserInformation)auth.getPrincipal()).getId());
        webOrderTemplate.setCompanyId(((WebUserInformation)auth.getPrincipal()).getCompanyId());
        //Company id!!!!
//        webOrderTemplate.setDate(new Date());
        model.addAttribute("orderTemplate", webOrderTemplate);
        model.addAttribute("clients", fillClientList
                .getFilledWebList(((WebUserInformation)auth.getPrincipal())
                        .getCompanyId()).getWebList());
        return "select-client";
    }

    @RequestMapping(value = "/create/client", method = RequestMethod.POST)
    @Secured("ROLE_MANAGER")
    public String goCreateClient(Model model,
                                  @ModelAttribute WebOrderTemplate webOrderTemplate) {
        if(webOrderTemplate.isClientInBase()){
            model.addAttribute("orderTemplate", webOrderTemplate);
            model.addAttribute("clientCars", fillClientCarList
                    .getFilledWebList(webOrderTemplate.getClientId())
                    .getWebList());
            return "select-car";
        } else {
            model.addAttribute("orderTemplate", webOrderTemplate);
            return "new-client";
        }
    }

    @RequestMapping(value = "/create/car", method = RequestMethod.POST)
    @Secured("ROLE_MANAGER")
    public String goCreateCar(Model model,
                            @ModelAttribute WebOrderTemplate webOrderTemplate) {
        if(webOrderTemplate.isClientCarInList()){
            model.addAttribute("orderTemplate", webOrderTemplate);
            return "new-text";
        } else {
            model.addAttribute("orderTemplate", webOrderTemplate);
            model.addAttribute("carMarks", fillCarMarkList
                    .getFilledWebList().getWebList());
            return "new-car";
        }    }

    @RequestMapping(value = "/create/text", method = RequestMethod.POST)
    @Secured("ROLE_MANAGER")
    public String goCreateText(Model model,
                             @ModelAttribute WebOrderTemplate webOrderTemplate) {
        model.addAttribute("orderTemplate", webOrderTemplate);
        return "new-text";
    }

    @RequestMapping(value = "/create/save", method = RequestMethod.POST)
    @Secured("ROLE_MANAGER")
    public String saveNewOrder(@ModelAttribute WebOrderTemplate webOrderTemplate) {
        webOrderStorage.saveNewWebOrder(webOrderTemplate);
        return "redirect:/service-panel/";
    }

}
