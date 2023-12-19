package com.bookstore.service;

import com.bookstore.model.entity.User;
import com.bookstore.model.enums.Role;
import com.bookstore.model.request.RegisterRequest;
import com.bookstore.model.request.UpdatePasswordRequest;
import com.bookstore.model.request.UpdateUserRequest;
import com.bookstore.model.response.UpdateResponse;
import com.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final VerificationService verificationService;
    private final PasswordEncoder passwordEncoder;

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

    public UpdateResponse updateUserPassword(Long id, UpdatePasswordRequest request) throws UsernameNotFoundException{
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isEmpty())
            throw new UsernameNotFoundException("User not found");

        User updatedUser = oldUser.get();
        updatedUser.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(updatedUser);

        return UpdateResponse.builder().message("Successfully Updated Password").build();
    }

    public void enableUser(Long id) throws Exception{

        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setIsUserEnabled(true);

        userRepository.save(existingUser);
    }

    public User addUser(RegisterRequest request){
     return userRepository.save(User.builder()
             .firstName(request.getFirstName())
             .lastName(request.getLastName())
             .email(request.getEmail())
             .password(passwordEncoder.encode(request.getPassword()))
             .role(Role.ROLE_USER)
             .isUserEnabled(false)
             .build()
     );
    }
}
