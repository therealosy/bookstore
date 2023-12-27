package com.bookstore.service;

import com.bookstore.model.entity.Order;
import com.bookstore.model.entity.User;
import com.bookstore.model.enums.Role;
import com.bookstore.model.request.RegisterRequest;
import com.bookstore.model.request.UpdateRoleRequest;
import com.bookstore.model.request.UpdateUserRequest;
import com.bookstore.model.response.UpdateResponse;
import com.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Collection<User> loadAllUsers(){
        return userRepository.findAll();
    }

    public User loadUserById(Long id) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return user.get();
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return user.get();
    }

    public UpdateResponse updateUserRole(Long id, UpdateRoleRequest request) throws UsernameNotFoundException{
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isEmpty())
            throw new UsernameNotFoundException("User not found");

        User updatedUser = oldUser.get();
        updatedUser.setRole(request.getRole());
        userRepository.save(updatedUser);

        return UpdateResponse.builder().message("Successfully Updated User Role").build();
    }

    public UpdateResponse updateUserDetails(Long id, UpdateUserRequest request) throws UsernameNotFoundException{
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isEmpty())
            throw new UsernameNotFoundException("User not found");

        User updatedUser = oldUser.get();
        updatedUser.setFirstName(request.getFirstName());
        updatedUser.setLastName(request.getLastName());

        userRepository.save(updatedUser);

        return UpdateResponse.builder().message("Successfully Updated User details").build();
    }

    public UpdateResponse updateUserPassword(String userName, String newPassword) throws UsernameNotFoundException{
        Optional<User> oldUser = userRepository.findByEmail(userName);
        if(oldUser.isEmpty())
            throw new UsernameNotFoundException("User not found");

        User updatedUser = oldUser.get();
        updatedUser.setPassword(newPassword);

        userRepository.save(updatedUser);

        return UpdateResponse.builder().message("Successfully Updated Password").build();
    }

    public Long getUserIdFromAuthentication(Authentication authentication){
        Object principal = authentication.getPrincipal();
        log.info("Getting Id from principal: {}", principal);
        if (principal instanceof User) {
            return ((User)principal).getUserId();
        }else {
            return loadUserByUsername(authentication.getName()).getUserId();
        }
    }

    public Collection<Order> loadUserOrders(Long id){
        User user = loadUserById(id);
        return user.getOrders();
    }

    public void enableUser(Long id) throws Exception{

        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setIsUserEnabled(true);

        userRepository.save(existingUser);
    }

    public User addUser(@NotNull RegisterRequest request){
     return userRepository.save(User.builder()
             .firstName(request.getFirstName())
             .lastName(request.getLastName())
             .email(request.getEmail())
             .password(request.getPassword())
             .role(Role.ROLE_USER)
             .isUserEnabled(false)
             .build()
     );
    }
}
