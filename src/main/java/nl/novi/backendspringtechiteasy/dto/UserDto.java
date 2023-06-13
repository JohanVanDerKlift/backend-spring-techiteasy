package nl.novi.backendspringtechiteasy.dto;

import nl.novi.backendspringtechiteasy.model.Authority;

import java.util.Set;

public class UserDto {
    public String username;
    public String password;
    public boolean enabled;
    public String ApiKey;
    public String email;
    public Set<Authority> authorities;

}
