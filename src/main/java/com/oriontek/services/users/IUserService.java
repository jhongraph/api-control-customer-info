package com.oriontek.services.users;

import com.oriontek.dto.LoginDto;
import com.oriontek.models.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {


     ResponseEntity <String> registerUser(User user);

     ResponseEntity <String> loginUser(LoginDto user);



}
