package sample.jdk16.it.various;

import java.util.Optional;

public class UserRepoImpl implements UserRepository{
    @Override
    public Optional<User> findById(User.Id userId) {
        return Optional.of(new User("x", "y"));
    }
}
