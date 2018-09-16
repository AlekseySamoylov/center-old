package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.element.*;
import com.alekseysamoylov.repair.center.site.model.form.NormalUser;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import com.alekseysamoylov.repair.center.site.service.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/6/16.
 */
@Controller
@RequestMapping(value = "/service-panel")
@Secured("ROLE_MANAGER")
public class MainOrderController {

    private final WebPurchaseOrderStorage purchaseOrderStorage;
    private final FillWebList<WebOrder> fillWebList;
    private final WebOrderStorage webOrderStorage;
    private final FillWebObject<WebCompany, Long> webCompanyLongFillWebObject;
    private final WebClientFullStorage webClientFullStorage;


    @Autowired
    public MainOrderController(WebPurchaseOrderStorage purchaseOrderStorage,
                               @Qualifier("fillOrderList")
                               FillWebList<WebOrder> fillWebList,
                               WebOrderStorage webOrderStorage,
                               @Qualifier("webCompanyInformationFill")
                               FillWebObject<WebCompany, Long> webCompanyLongFillWebObject,
                               WebClientFullStorage webClientFullStorage) {
        this.purchaseOrderStorage = purchaseOrderStorage;
        this.fillWebList = fillWebList;
        this.webOrderStorage = webOrderStorage;
        this.webCompanyLongFillWebObject = webCompanyLongFillWebObject;
        this.webClientFullStorage = webClientFullStorage;
    }


    //PreFilter or PostFilter(able to use)
    @RequestMapping(value = "/")
    public String orderList(Model model, Authentication auth) throws IOException {

        List<WebOrder> list = fillWebList
                .getFilledWebList(((WebUserInformation) auth
                        .getPrincipal()).getCompanyId()).getWebList();
        Collections.sort(list);
        model.addAttribute("orders", list);
        return "order-list";
    }

    //MANAGER SECURITY ACCESS
    @RequestMapping(value = "/delete-order/{orderId}")
    public String deleteOrder(Authentication auth, Model model, @PathVariable Long orderId) {
        WebOrder tempOrder = new WebOrder();
        tempOrder.setId(orderId);
        Long companyId = ((WebUserInformation)auth.getPrincipal()).getCompanyId();
        webOrderStorage.deleteWebOrder(orderId, companyId);
        return "redirect:/service-panel/";
    }

    @RequestMapping(value = "/print-order/{id}")
    @PostAuthorize("principal.companyId == #model[purchaseOrder].companyId")
    public String openPurchaseOrder(Authentication auth, Model model, @PathVariable Long id) {
        model.addAttribute("purchaseOrder", purchaseOrderStorage.loadTemplate(id));
        model.addAttribute("webCompany", webCompanyLongFillWebObject
                .getFilledWebObject(((WebUserInformation) auth
                        .getPrincipal()).getCompanyId()));
        return "print-order";
    }

    @RequestMapping(value = "/print-plan/{id}")
    @PostAuthorize("principal.companyId == #model[purchaseOrder].companyId")
    public String openPrintPlanOrder(Authentication auth, Model model, @PathVariable Long id) {
        model.addAttribute("purchaseOrder", purchaseOrderStorage.loadTemplate(id));
        return "print-plan";
    }

    @RequestMapping(value = "/open-clients")
    @Secured("ROLE_MANAGER")
    public String goClientList(Model model) {
        model.addAttribute("clients", webClientFullStorage.getAllUsers());
        return "open-clients";
    }

    @RequestMapping(value = "/open-employees")
    @Secured("ROLE_MANAGER")
    public String goEmployeesList(Model model) {
        model.addAttribute("employees", webClientFullStorage.getAllEmployees());
        return "open-employees";
    }

    @RequestMapping(value = "/set-client/{userId}", method = RequestMethod.GET)
    @Secured("ROLE_MANAGER")
    public String setClient(@PathVariable Long userId) {
        webClientFullStorage.setClient(userId);
        return "redirect:/service-panel/open-employees";
    }
    @RequestMapping(value = "/set-master/{userId}", method = RequestMethod.GET)
    @Secured("ROLE_MANAGER")
    public String setMaster(@PathVariable Long userId) {
        webClientFullStorage.setMaster(userId);
        return "redirect:/service-panel/open-employees";
    }
    @RequestMapping(value = "/set-manager/{userId}", method = RequestMethod.GET)
    @Secured("ROLE_MANAGER")
    public String setManager(@PathVariable Long userId) {
        webClientFullStorage.setManager(userId);
        return "redirect:/service-panel/open-employees";
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.GET)
    @Secured("ROLE_MANAGER")
    public String goNewUser(Model model) {
        model.addAttribute("normalUser", new NormalUser());
        return "new-user";
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    @Secured("ROLE_MANAGER")
    public String saveNewUser(@ModelAttribute NormalUser normalUser) {
        webClientFullStorage.saveNormalUser(normalUser);
        return "redirect:/service-panel/open-clients";
    }


}
