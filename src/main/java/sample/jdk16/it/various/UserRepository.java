package sample.jdk16.it.various;

import java.util.Optional;

public interface UserRepository {
//    User findById(User.Id userId);
    Optional<User> findById(User.Id userId);
}