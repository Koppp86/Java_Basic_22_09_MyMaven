package services.io;

public interface IOConsoleService {
    void outputString(String message);
    void outputString(String template, Object ...args);
    String inputString();
}
