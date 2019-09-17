package be.technocite.jwtrest.service;

import be.technocite.jwtrest.api.dto.RegisterUserCommand;
import be.technocite.jwtrest.model.Role;
import be.technocite.jwtrest.model.User;
import be.technocite.jwtrest.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bCryPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthorities(user.getRoles());
            return buildSpringUser(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found!");
        }
    }

    private List<GrantedAuthority> getUserAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return authorities;
    }

    private UserDetails buildSpringUser(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public User findByEmail(String email) {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("user not found!");
        }
    }

    public User createUser(RegisterUserCommand command) {
        User user = new User();
        user.setPassword(bCryPasswordEncoder.encode(command.getPassword()));
        user.setEnabled(true);
        user.setRoles(new HashSet<>(command.getRoles()));
        return userDAO.save(user);
    }
}
