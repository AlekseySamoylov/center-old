package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.element.WebMaster;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderPartTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderWorkTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebTextPlusAdvice;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import com.alekseysamoylov.repair.center.site.service.util.WebOrderWorkStorage;
import com.alekseysamoylov.repair.center.site.service.util.WebPartStorage;
import com.alekseysamoylov.repair.center.site.service.util.WebPurchaseOrderStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
@Controller
@RequestMapping(value = "/service-panel")
@Secured("ROLE_MANAGER")
public class PurchaseOrderController {

    private final WebPurchaseOrderStorage purchaseOrderStorage;

    private final WebOrderWorkStorage webOrderWorkStorage;

    private final WebPartStorage webPartStorage;

    private final
    FillWebList<WebMaster> fillMasterList;

    @Autowired
    public PurchaseOrderController(WebPurchaseOrderStorage purchaseOrderStorage, WebOrderWorkStorage webOrderWorkStorage, @Qualifier("fillMasterList") FillWebList<WebMaster> fillMasterList, WebPartStorage webPartStorage) {
        this.purchaseOrderStorage = purchaseOrderStorage;
        this.webOrderWorkStorage = webOrderWorkStorage;
        this.fillMasterList = fillMasterList;
        this.webPartStorage = webPartStorage;
    }

    //COMPANY SECURITY ACCESS
    @RequestMapping(value = "/open/{id}")
    @PostAuthorize("principal.companyId == #model[purchaseOrder].companyId")
    public String openPurchaseOrder(Model model, @PathVariable Long id) {
        model.addAttribute("purchaseOrder", purchaseOrderStorage.loadTemplate(id));
        model.addAttribute("textPlusAdvice", new WebTextPlusAdvice());
        return "purchase-order";
    }

    @RequestMapping(value = "/open/save", method = RequestMethod.POST)
    public String operationPurchaseOrder(Model model, @ModelAttribute WebTextPlusAdvice attribute) {
        switch (attribute.getOperation()) {
            case 1:
                purchaseOrderStorage.saveTextPlusAdvice(attribute);
                model.addAttribute("purchaseOrder", purchaseOrderStorage
                        .loadTemplate(attribute.getId()));
                model.addAttribute("textPlusAdvice", new WebTextPlusAdvice());
                return "purchase-order";
            case 2:
                //add work
                purchaseOrderStorage.saveTextPlusAdvice(attribute);
                WebOrderWorkTemplate workTemplate = new WebOrderWorkTemplate();
                workTemplate.setOrderId(attribute.getId());
                model.addAttribute("workTemplate", workTemplate);
                model.addAttribute("masters", fillMasterList.getFilledWebList(workTemplate.getOrderId()).getWebList());
                return "add-master";
            case 3:
                //add part
                purchaseOrderStorage.saveTextPlusAdvice(attribute);
                WebOrderPartTemplate partTemplate = new WebOrderPartTemplate();
                partTemplate.setOrderId(attribute.getId());
                model.addAttribute("partTemplate", partTemplate);
                return "add-part";
            default:
                //Exit without saving
                return "redirect:/service-panel/";

        }
    }

    @RequestMapping(value = "/open/delete-work/{splitId}")
    public String deleteOrder(Model model, @PathVariable String splitId) {
        String delims = "[+]";
        String[] tokens = splitId.split(delims);
        webOrderWorkStorage.deleteOrderWork(Long.valueOf(tokens[0]));
        model.addAttribute("purchaseOrder", purchaseOrderStorage.loadTemplate(Long.valueOf(tokens[1])));
        model.addAttribute("textPlusAdvice", new WebTextPlusAdvice());
        return "purchase-order";
    }

    @RequestMapping(value = "/open/delete-part/{splitId}")
    public String deletePart(Model model, @PathVariable String splitId) {
        String delims = "[+]";
        String[] tokens = splitId.split(delims);
        webPartStorage.deleteOrderPart(Long.valueOf(tokens[0]));
        model.addAttribute("purchaseOrder", purchaseOrderStorage.loadTemplate(Long.valueOf(tokens[1])));
        model.addAttribute("textPlusAdvice", new WebTextPlusAdvice());
        return "purchase-order";
    }

}
