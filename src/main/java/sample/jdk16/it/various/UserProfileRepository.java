package sample.jdk16.it.various;

import java.util.Optional;

public interface UserProfileRepository {
    Optional<UserProfile> findById(User.Id userId);
}