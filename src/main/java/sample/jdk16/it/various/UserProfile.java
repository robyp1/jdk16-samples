package sample.jdk16.it.various;

import java.util.Optional;

public record UserProfile(String profileName ) {

    public static Optional<UserProfile> defaultDetails() {
        return Optional.of(new UserProfile("x"));
    }

    record Id (){

    }
}
