package com.Demointelli.demo2.Service;


import com.Demointelli.demo2.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface UserService {
    public List<Users> getuser( ) ;
    public Users setuser( Users user);
    public Users getUserById(long id);
}
