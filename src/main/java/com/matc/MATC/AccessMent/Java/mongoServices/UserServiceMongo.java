package com.matc.MATC.AccessMent.Java.mongoServices;

import com.matc.MATC.AccessMent.Java.MongoModels.UserMo;
import com.matc.MATC.AccessMent.Java.exception.UserNotFoundException;
import com.matc.MATC.AccessMent.Java.mongoRepository.UserRepositoryMo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceMongo implements UserDetailsService {

    @Autowired
    UserRepositoryMo userRepositoryMo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Value("${login.admin.username}")
    private String userName;

    @Value("${login.admin.password}")
    private String passWord;

    @Value("${login.admin.role}")
    private String role;

    public UserMo getUserById(Long id) {
        Optional<UserMo> user = userRepositoryMo.findById(id);
        if(user.isPresent()){
            return userRepositoryMo.findById(id).get();
        }else {
            throw new UserNotFoundException();
        }

    }

    public List<UserMo> getAllUser() {
        return userRepositoryMo.findAll();
    }

    public UserMo save(UserMo userMo) {
        return userRepositoryMo.save(userMo);
    }

    public void delete(Long id) {
        Optional<UserMo> user = userRepositoryMo.findById(id);
        if(user.isPresent()){
            userRepositoryMo.deleteById(id);
        }else {
            throw new UserNotFoundException();
        }
    }

    public UserMo updateUser(Long id, UserMo userMo) throws Exception {
        Optional<UserMo> addResponse=userRepositoryMo.findById(id);
        if(addResponse.isPresent() && addResponse.get().getUserId().equals(id)){
            userMo.setUserId(id);
            return userRepositoryMo.save(userMo);
        }
        else{
            throw new UserNotFoundException();
        }
    }

    public UserMo saveAdmin() throws Exception {
        UserMo userMoResponse = userRepositoryMo.findByUserName(userName);
        if( userMoResponse != null && Objects.equals(userMoResponse.getUserName(), userName)){

            return null;
        }
        else{
            UserMo userMo = new UserMo();
            userMo.setUserName(userName);
            userMo.setPassword(passWord);
            userMo.setRole(role);
            return userRepositoryMo.save(userMo);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserMo userMo = userRepositoryMo.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(userMo.getUserName() , userMo.getPassword() , new ArrayList<>());
    }
}
