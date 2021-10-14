package sample.jdk16.it.various;


import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

//    public UserWithProfile getUserWithProfile(User.Id userId) {
//        User user = userRepository.findById(userId);
//
//        if (user == null) {
//            throw new UserNotFoundException("User with ID " + userId + " not found");
//        }
//
//        UserProfile details = userProfileRepository.findById(userId);
//
//        return UserWithProfile.of(user, details == null
//                ? UserProfile.defaultDetails()
//                : details);
//    }

    public UserWithProfile getUserWithProfileRewritten(User.Id userId) {
        //se non ci sono user lancio una eccezione
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with ID " + userId + " not found")
        );

//        if (user.isEmpty()) {
//            throw new UserNotFoundException("User with ID " + userId + " not found");
//        }
        //se optional è vuoto eseguo l'or e torna un default
        UserProfile details = userProfileRepository.findById(userId).or(UserProfile::defaultDetails).get();

        return UserWithProfile.of(user, details);

//        return UserWithProfile.of(user, details == null
//                ? UserProfile.defaultDetails()
//                : details);
    }


    public UserWithProfile getUserWithProfileRewritten2(User.Id userId) {
        //NB: map viene eseguiro solo se user non è vuoto
        return userRepository.findById(userId).map(user -> {
            //se optional è vuoto eseguo l'or e torna un default
            UserProfile details = userProfileRepository.findById(userId).or(UserProfile::defaultDetails).get();
            return UserWithProfile.of(user, details); //questo è un Optional<UserWithProfile> userWithProfile
        }).orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
//        return UserWithProfile.of(user, details == null
//                ? UserProfile.defaultDetails()
//                : details);
    }


    public static void main(String[] args) {
        UserService userService = new UserService(new UserRepoImpl(), new UserProfileRepository() {
            @Override
            public Optional<UserProfile> findById(User.Id userId) {
                return Optional.empty();
            }
        });
        UserWithProfile userWithProfileRewritten2 = userService.getUserWithProfileRewritten2(new User.Id(1));
        userWithProfileRewritten2.toString();
    }
}