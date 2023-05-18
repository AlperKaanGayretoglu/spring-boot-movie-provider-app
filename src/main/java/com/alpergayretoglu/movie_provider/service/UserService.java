package com.alpergayretoglu.movie_provider.service;

import com.alpergayretoglu.movie_provider.exception.BadRequestException;
import com.alpergayretoglu.movie_provider.exception.EntityNotFoundException;
import com.alpergayretoglu.movie_provider.model.entity.*;
import com.alpergayretoglu.movie_provider.model.enums.UserRole;
import com.alpergayretoglu.movie_provider.model.request.user.UserCreateRequest;
import com.alpergayretoglu.movie_provider.model.request.user.UserUpdateRequest;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MovieService movieService;
    private final CategoryService categoryService;
    private final SubscriptionService subscriptionService;
    private final ContractRecordService contractRecordService;
    private final PasswordEncoder passwordEncoder;

    /* without Pagination
    public List<User> getUsers() {
        return userRepository.findAll();
    }
     */

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User addUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists"); // TODO make specific exception
        }

        return userRepository.save(User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.GUEST) // default
                .build()
        );
    }


    public User getUser(String id) {
        return getUserWithException(id);
    }

    public User updateUser(String id, UserUpdateRequest request) {
        User oldUser = getUserWithException(id);

        oldUser.setName(request.getName());
        oldUser.setSurname(request.getSurname());
        oldUser.setEmail(request.getEmail());
        oldUser.setPassword(request.getPassword());

        return userRepository.save(oldUser);
    }

    public void deleteUser(String userId) {
        User user = getUserWithException(userId);
        userRepository.delete(user);
    }

    private User getUserWithException(String id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
    }


    public ContractRecord subscribe(String userId, String subscriptionId) {
        User user = getUserWithException(userId);
        Subscription subscription = subscriptionService.findById(subscriptionId);

        // check if user is verified
        if (!user.isVerified()) throw new BadRequestException("only verified users can subscribe");

        // check if user has already a subscription (allow only upgrading)
        ContractRecord contractRecord = user.getContractRecord();

        if (contractRecord != null) {
            if (contractRecord.getDuration() >= subscription.getDuration()) {
                throw new BadRequestException("you can only upgrade your subscription");
            }
        }

        // when a guest user buys a subscription, assign a member role
        if (user.getRole() == UserRole.GUEST) user.setRole(UserRole.MEMBER);

        if (contractRecord == null) return contractRecordService.addContract(user, subscription);
        else return contractRecordService.updateContract(contractRecord, subscription);
    }

    // Interests : Follow Categories and Favorite Movies
    public User favoriteMovie(String userId, String movieId) {
        return addOrRemoveMovieFromUserFavoriteMovies(userId, movieId, true);
    }

    public User unfavoriteMovie(String userId, String movieId) {
        return addOrRemoveMovieFromUserFavoriteMovies(userId, movieId, false);
    }

    public User followCategory(String userId, String categoryId) {
        return followHelper(userId, categoryId, true);
    }

    public User unfollowCategory(String userId, String categoryId) {
        return followHelper(userId, categoryId, false);
    }

    /**
     * Helper method to avoid duplicate codes.
     * boolean isAddition field checks if it is a favorite or unfavorite method
     */
    private User addOrRemoveMovieFromUserFavoriteMovies(String userId, String movieId, boolean isAddition) {
        User user = getUserWithException(userId);
        Movie movie = movieService.findMovieById(movieId);
        Set<Movie> movies = user.getFavoriteMovies();

        if (isAddition) {
            movies.add(movie);
        } else {
            movies.remove(movie);
        }

        userRepository.save(user);
        return user;
    }

    /**
     * Helper method to avoid duplicate codes. <br>
     * (TODO still needs a refactor of duplicate codes with addOrRemoveMovieFromUserFavoriteMovies method) <br>
     * To refactor, maybe use ICrudService interface? and give the method of class and its service. <br>
     * boolean isFollow field checks if it is a follow or unfollow request
     */
    private User followHelper(String userId, String categoryId, boolean isFollow) {
        User user = getUserWithException(userId);
        Category category = categoryService.findCategoryById(categoryId);

        List<Category> categories = user.getFollowedCategories();

        if (isFollow && !categories.contains(category)) categories.add(category);
        else categories.remove(category);
        userRepository.save(user);
        return user;
    }
}