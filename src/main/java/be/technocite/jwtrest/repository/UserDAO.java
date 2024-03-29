package be.technocite.jwtrest.repository;

import be.technocite.jwtrest.model.User;

public interface UserDAO {

    User findByEmail(String email);
    User save(User user);
}
