package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.element.WebOrderPartTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderTemplate;
import com.alekseysamoylov.repair.center.site.service.util.WebPartStorage;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by alekseysamoylov on 7/10/16.
 */
@Controller
@RequestMapping(value = "/service-panel")
@Secured("ROLE_MANAGER")
public class CreatePartController {

    private final WebPartStorage partStorage;

    @Autowired
    public CreatePartController(WebPartStorage partStorage) {
        this.partStorage = partStorage;
    }

    @RequestMapping(value = "/open/part")
    public String savePart(@ModelAttribute WebOrderPartTemplate partTemplate) {
        System.out.println(partTemplate);
        partStorage.saveOrderPart(partTemplate);
        return "redirect:/service-panel/open/" + partTemplate.getOrderId();
    }

}
