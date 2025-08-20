package service;

public class ValidationService {
    public static boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
