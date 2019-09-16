package be.technocite.jwtrest.repository.impl;

import be.technocite.jwtrest.model.User;
import be.technocite.jwtrest.repository.UserDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private List<User> users = new ArrayList<>();

    @Override
    public User findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(User user) {
        if(findByEmail(user.getEmail()) == null){
            users.add(user);
        }else{
            users.remove(user);
            users.add(user);
        }

        return findByEmail(user.getEmail());
    }

}
