package domain.validator;

import data.entity.User;

public class BirthdateValidator implements Validator<User> {
    @Override
    public boolean validate(User user) {
        return user.getBirthdate() != null && !user.getBirthdate().isEmpty();
    }
}
