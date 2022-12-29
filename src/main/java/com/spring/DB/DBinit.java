package com.spring.DB;

import com.spring.Dao.AuthDao;
import com.spring.Dao.UserDao;
import com.spring.model.Authorities;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBinit implements CommandLineRunner {
    private UserDao userDao;
    private AuthDao authDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DBinit(UserDao userDao,AuthDao authDao,PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.authDao = authDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {


        userDao.deleteAll();

        User admin = new User("anakin",passwordEncoder.encode("anakin201"),"20","alex",1);
        admin.setAuthorities(authDao.findAll());
        userDao.save(admin);

        ///////////////////////////////

        User manger = new User("manger",passwordEncoder.encode("manger201"),"22","benha",1);

        Authorities managerAuth1 = authDao.findById(2L).get();
        Authorities managerAuth2 = authDao.findById(3L).get();
        Authorities managerAuth3 = authDao.findById(5L).get();
        Authorities managerAuth4 = authDao.findById(6L).get();

        manger.getAuthorities().add(managerAuth1);
        manger.getAuthorities().add(managerAuth2);
        manger.getAuthorities().add(managerAuth3);
        manger.getAuthorities().add(managerAuth4);

        userDao.save(manger);

        ////////////////////////////////////////////////

        User user = new User("user",passwordEncoder.encode("user201"),"30","cairo",1);
        Authorities userAuth1 = authDao.findById(3L).get();
        Authorities userAuth2 = authDao.findById(6L).get();

        user.getAuthorities().add(userAuth1);
        user.getAuthorities().add(userAuth2 );

        userDao.save(user);
    }
}
