//package com.matc.MATC.AccessMent.Java.service;
//
//import com.matc.MATC.AccessMent.Java.dto.UserDto;
//import com.matc.MATC.AccessMent.Java.exception.UserNotFoundException;
//import com.matc.MATC.AccessMent.Java.model.User;
//import com.matc.MATC.AccessMent.Java.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userrepository;
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassWord() {
//        return passWord;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    @Value("${login.admin.username}")
//    private String userName;
//
//    @Value("${login.admin.password}")
//    private String passWord;
//
//    @Value("${login.admin.role}")
//    private String role;
//
////    public UserDto getUserById(Long id) {
//////        var user =new User();
////
////        Optional<User> user = userrepository.findById(id);
////        if(user.isPresent()){
////            return userrepository.findById(id).get();
////        }else {
////            throw new UserNotFoundException();
////        }
////
////    }
//
//    public List<User> getAllUser() {
//        return userrepository.findAll();
//    }
//
//    public User save(User user) {
//        return userrepository.save(user);
//    }
//
//    public void delete(Long id) {
//        Optional<User> user = userrepository.findById(id);
//        if(user.isPresent()){
//            userrepository.deleteById(id);
//        }else {
//            throw new UserNotFoundException();
//        }
//    }
//
//    public User updateUser(Long id, User user) throws Exception {
//        Optional<User> addResponse=userrepository.findById(id);
//        if(addResponse.isPresent() && addResponse.get().getUserId().equals(id)){
//            user.setUserId(id);
//            return userrepository.save(user);
//        }
//        else{
//            throw new UserNotFoundException();
//        }
//    }
//
//    public User saveAdmin() throws Exception {
//        User userResponse = userrepository.findByUserName(userName);
//        if( userResponse != null && Objects.equals(userResponse.getUserName(), userName)){
//
//            return null;
//        }
//        else{
//            User user = new User();
//            user.setUserName(userName);
//            user.setPassword(passWord);
//            user.setRole(role);
//            return userrepository.save(user);
//        }
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//      User user = userrepository.findByUserName(username);
//        return new org.springframework.security.core.userdetails.User(user.getUserName() , user.getPassword() , new ArrayList<>());
//    }
//}
