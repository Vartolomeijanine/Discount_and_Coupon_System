package domain.validator;

public interface Validator<T> {
    boolean validate(T obj);
}
