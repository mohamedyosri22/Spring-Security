package com.spring.DB;

import com.spring.Dao.AuthDao;
import com.spring.Dao.RoleDao;
import com.spring.Dao.UserDao;
import com.spring.model.Authorities;
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

        userDao.deleteAll();

        User admin = new User("anakin","anakin201","20","alex",1);
        admin.setRoles(roleDao.findAll());
        admin.setAuthorities(authDao.findAll());
        userDao.save(admin);
        ///////////////////////////////

        User manger = new User("manger","manger201","22","benha",1);
        Role mangerRole1 = roleDao.findById(2L).get();
        Role mangerRole2 = roleDao.findById(3L).get();

        Authorities managerAuth1 = authDao.findById(2L).get();
        Authorities managerAuth2 = authDao.findById(3L).get();

        manger.getRoles().add(mangerRole1);
        manger.getRoles().add(mangerRole2);

        manger.getAuthorities().add(managerAuth1);
        manger.getAuthorities().add(managerAuth2);

        userDao.save(manger);

        ////////////////////////////////////////////////

        User user = new User("user","user201","30","cairo",1);
        Role userRole = roleDao.findById(3L).get();
        Authorities userAuth = authDao.findById(3L).get();

        user.getAuthorities().add(userAuth);
        user.getRoles().add(userRole);

        userDao.save(user);

    }
}
