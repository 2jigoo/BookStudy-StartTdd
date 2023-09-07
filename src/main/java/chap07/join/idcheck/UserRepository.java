package chap07.join.idcheck;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
