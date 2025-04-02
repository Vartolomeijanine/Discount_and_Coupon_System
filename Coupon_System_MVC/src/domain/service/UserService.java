package domain.service;

import data.entity.User;
import data.repository.UserRepository;
import domain.validator.Validator;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final List<Validator<User>> validators;


    public UserService(UserRepository userRepository, List<Validator<User>> validators) {
        this.userRepository = userRepository;
        this.validators = validators;
    }

    public User createUser(String name, String birthdate) {
        User user = new User(0, name, birthdate);

        for (Validator<User> validator : validators) {
            if (!validator.validate(user)) {
                throw new IllegalArgumentException("User invalid: validarea a eșuat.");
            }
        }

        return userRepository.save(name, birthdate);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }
}
