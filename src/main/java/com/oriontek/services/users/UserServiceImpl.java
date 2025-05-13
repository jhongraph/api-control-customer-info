package com.oriontek.services.users;

import com.oriontek.dto.LoginDto;
import com.oriontek.exception.httpException.UserException;
import com.oriontek.models.User;
import com.oriontek.repository.UserRepository;
import com.oriontek.security.MyUserDetailsService;
import com.oriontek.utils.JwtUtils;
import com.oriontek.utils.ValidatePasswordFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {


    MyUserDetailsService serviceDetails;
    JwtUtils jwt;
    AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(JwtUtils jwt, AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, MyUserDetailsService serviceDetails) {
        this.serviceDetails = serviceDetails;
        this.jwt = jwt;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> registerUser(User user) {
        try {

            if (user.getUsername() == null) {
                throw new UserException("username must not be null.");
            }

            if (user.getPassword() == null) {
                throw new UserException("password must not be null.");
            }

            if (user.getFirstName() == null) {
                throw new UserException("first name must not be null.");
            }

            if (user.getLastName() == null) {
                throw new UserException("last name must not be null.");
            }

            final User usernameExists = userRepository.findByUsername(user.getUsername());
            if (usernameExists != null)
                return new ResponseEntity<>("Username: " + user.getUsername() + ", already exists", HttpStatus.CONFLICT);

            final String password = user.getPassword();
            final String passwordCrypt = passwordEncoder.encode(password);

            boolean isValidPassword = ValidatePasswordFormat.validPassword(password);
            if (!isValidPassword) return new ResponseEntity<>("Password must contain at least 8 characters," +
                    " including 1st Character in uppercase, minimum 1 lowercase," +
                    " a number, and a special character", HttpStatus.BAD_REQUEST);

            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordCrypt);
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            userRepository.save(newUser);

            return new ResponseEntity<>("user register successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<String> loginUser(LoginDto user) {
        try {
            User username = userRepository
                    .findByUsername(user.getUsername());
            if (username == null) return new ResponseEntity<>("User not exist", HttpStatus.NOT_FOUND);
            boolean isValidPassword = passwordEncoder.matches(user.getPassword(), username.getPassword());
            if (!isValidPassword)
                return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username.getUsername(), user.getPassword())
            );

            UserDetails userDetails = serviceDetails.loadUserByUsername(username.getUsername());

            return new ResponseEntity<>(jwt.generateToken(userDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error logging in user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
