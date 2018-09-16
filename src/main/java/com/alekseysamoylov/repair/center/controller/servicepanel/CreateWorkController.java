package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.element.WebOrderWorkTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebRepairSection;
import com.alekseysamoylov.repair.center.site.model.element.WebWorkShort;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import com.alekseysamoylov.repair.center.site.service.util.WebOrderWorkStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
@Controller
@RequestMapping(value = "/service-panel")
@Secured("ROLE_MANAGER")
public class CreateWorkController {
    private final
    FillWebList<WebRepairSection> fillSectionList;

    private final
    FillWebList<WebWorkShort> fillWorkShortList;

    private final
    WebOrderWorkStorage workStorage;

    @Autowired
    public CreateWorkController(WebOrderWorkStorage workStorage,
                                @Qualifier("fillWorkShortList")
                                FillWebList<WebWorkShort> fillWorkShortList,
                                @Qualifier("fillRepairSectionList") FillWebList<WebRepairSection> fillSectionList) {

        this.workStorage = workStorage;
        this.fillWorkShortList = fillWorkShortList;
        this.fillSectionList = fillSectionList;
    }


    @RequestMapping(value = "/open/work/section")
    public String selectSection(Model model, @ModelAttribute WebOrderWorkTemplate workTemplate) {
        model.addAttribute("workTemplate", workTemplate);
        model.addAttribute("sections", fillSectionList.getFilledWebList().getWebList());
        return "add-section";
    }

    @RequestMapping(value = "/open/work/select")
    public String selectWork(Model model, @ModelAttribute WebOrderWorkTemplate workTemplate) {
        model.addAttribute("workTemplate", workTemplate);
        model.addAttribute("works", fillWorkShortList.getFilledWebList(workTemplate.getSectionId()).getWebList());
        return "add-work";
    }

    @RequestMapping(value = "/open/work/complete")
    public String completeWork(Model model, @ModelAttribute WebOrderWorkTemplate workTemplate) {
        if (workTemplate.isWorkInPrice()) {
            model.addAttribute("suggestions",
                    workStorage.getWorkSuggestions(
                            workTemplate.getPriceWorkId(),
                            workTemplate.getCarModelId()));
            model.addAttribute("workTemplate", workTemplate);
            return "work-complete-true";
        } else {
            model.addAttribute("workTemplate", workTemplate);
            return "work-complete-false";
        }

    }
    @RequestMapping(value = "/open/work/finish")
    public String saveWork(@ModelAttribute WebOrderWorkTemplate workTemplate) {
        workStorage.saveWork(workTemplate);
        return "redirect:/service-panel/open/" + workTemplate.getOrderId();
    }
}
