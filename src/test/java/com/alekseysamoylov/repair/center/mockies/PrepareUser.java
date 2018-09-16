package com.alekseysamoylov.repair.center.mockies;

import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Collection;

/**
 * Created by alekseysamoylov on 7/26/16.
 */
public class PrepareUser {

    /**
     * Prepare Authentication for tests
     * @param role
     */
    public void setRole(String role) {
        WebUserInformation user = new WebUserInformation();
        user.setRole(role);
        user.setId(9L);
        user.setName("SAMOYLOV");
        user.setFirstName("Aleksey");
        user.setLastName("Samoylov");
        user.setCompanyId(1L);
        user.setPassword("345345345");
        user.setFirstName("sdfgdsfgdfg");
        WebCompany webCompany = new WebCompany();
        webCompany.setId(1L);
        webCompany.setName("Veter");
        user.getCompanies().add(webCompany);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getCredentials()).thenReturn(AuthorityUtils.createAuthorityList(role));
        Mockito.when(authentication.getAuthorities()).thenReturn((Collection)AuthorityUtils.createAuthorityList(role));

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
    }

    public Model setModel() {
        Model model = Mockito.mock(Model.class);
//        Mockito.when(model.)
        WebOrderTemplate webOrderTemplate = new WebOrderTemplate();
        webOrderTemplate.setManagerId(1L);
        webOrderTemplate.setCompanyId(1L);
return null;
    }

}
