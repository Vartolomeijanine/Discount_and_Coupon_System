package data.repository;

import data.entity.User;
import java.util.*;

public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public User save(String name, String birthdate) {
        User user = new User(nextId++, name, birthdate);
        users.put(user.getId(), user);
        return user;
    }

    public User findById(int id) {
        return users.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
