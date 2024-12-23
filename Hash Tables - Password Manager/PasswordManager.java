public interface PasswordManager {
    void insertPassword(Password p);
    Password deletePassword(int key);
    Password searchPassword(int key);
    void setPasswordManagerSize(int size);
    void displayPasswordManager();
}
