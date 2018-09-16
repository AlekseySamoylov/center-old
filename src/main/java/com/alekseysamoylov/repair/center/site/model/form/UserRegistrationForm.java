package com.alekseysamoylov.repair.center.site.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by alekseysamoylov on 8/13/16.
 */
@ToString
@AllArgsConstructor
@Getter
@Setter
public class UserRegistrationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
}
