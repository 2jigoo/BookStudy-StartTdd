package chap07.User;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
