package com.spring.DB;

import com.spring.Dao.AuthDao;
import com.spring.Dao.RoleDao;
import com.spring.Dao.UserDao;
import com.spring.model.Role;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DBinit implements CommandLineRunner {
    private UserDao userDao;
    private RoleDao roleDao;
    private AuthDao authDao;

    @Autowired
    public DBinit(UserDao userDao,RoleDao roleDao,AuthDao authDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.authDao = authDao;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("anakin","anakin201","20","alex",1);
        user.setRoles(roleDao.findAll());
        user.setAuthorities(authDao.findAll());
        userDao.save(user);
    }
}
