package com.alekseysamoylov.repair.center.service.security;

import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import com.alekseysamoylov.repair.center.site.service.util.FillWebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
@Component("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

    private final FillWebObject<WebUserInformation, String> fillWebUserInf;

    @Autowired
    public CustomUserDetailsService(@Qualifier("webUserInformationFill") FillWebObject<WebUserInformation, String> fillWebUserInf) {
        this.fillWebUserInf = fillWebUserInf;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return fillWebUserInf.getFilledWebObject(s);
    }


}
