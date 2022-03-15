package com.Demointelli.demo2.controller;


import com.Demointelli.demo2.Service.UserService;
import com.Demointelli.demo2.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MyController {
@Autowired
private UserService userService;

@Autowired
private AuthenticationManager authenticationManager;
    @PostMapping("/userlogin")
    public ResponseEntity<HttpStatus> login(@RequestBody Users user)throws  Exception
    {
        Authentication autho;
        try {
            autho=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            SecurityContextHolder .getContext().setAuthentication(autho);
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("invalid credentials");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getuser")
    public List<Users> getuser()
    {
    return this.userService.getuser();
    }
    @PostMapping("/setuser")
    public Users setuser(@RequestBody Users user)
    {
        return this.userService.setuser(user);
    }
    @GetMapping("/getuser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id)
    {
        Users user=userService.getUserById(id);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
}
