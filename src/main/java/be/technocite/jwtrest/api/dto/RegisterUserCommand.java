package be.technocite.jwtrest.api.dto;

import be.technocite.jwtrest.model.Role;

import java.util.Objects;
import java.util.Set;

public class RegisterUserCommand {

    private String email;
    private String password;
    private String fullname;
    private Set<Role> roles;

    public RegisterUserCommand() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterUserCommand user = (RegisterUserCommand) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
