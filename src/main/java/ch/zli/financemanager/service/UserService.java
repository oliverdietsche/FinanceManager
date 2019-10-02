package ch.zli.financemanager.service;

import ch.zli.financemanager.entity.User;
import ch.zli.financemanager.entity.User;
import ch.zli.financemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public void deleteUserById(long id) throws Exception {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("Couldn't delete user, because it didn't exist!");
        }
    }

    public User updateUser(User user, long id) throws Exception {
        User updatedUser;
        Optional<User> optionalUpdatedUser = findUserById(id);

        if (optionalUpdatedUser.isPresent()) {
            updatedUser = optionalUpdatedUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updatedUser.setRoles(user.getRoles());
        } else {
            throw new Exception("Couldn't update user, because it didn't exist!");
        }

        return userRepository.saveAndFlush(updatedUser);
    }
}
