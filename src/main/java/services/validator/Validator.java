package services.validator;

public interface Validator <T> {
    boolean check(T value);
    String errMesage(T value);
}
