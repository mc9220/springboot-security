package com.Demointelli.demo2.ServiceImpl;


import com.Demointelli.demo2.Repo.UserRepo;
import com.Demointelli.demo2.Service.UserService;
import com.Demointelli.demo2.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder bEncoder;
    @Override
    public List<Users> getuser()  {
       List<Users> usersList= userRepo.findAll();

    return  usersList;
    }

    @Override
    public Users setuser(Users user) {
        Users u=new Users();
        u.setEmail(user.getEmail());
        u.setUname(user.getUname());
        u.setPassword(bEncoder.encode(user.getPassword()));
        u.setAuthority(user.getAuthority());
        return userRepo.save(u);
    }

    @Override
    public Users getUserById(long id) {
        Users user= userRepo.findById(id).orElseThrow(()->new NullPointerException("user not found"));
        return user;
    }
}
