//package com.matc.MATC.AccessMent.Java.controller;
//
//import com.matc.MATC.AccessMent.Java.dto.JwtRequest;
//import com.matc.MATC.AccessMent.Java.dto.LoginDto;
//import com.matc.MATC.AccessMent.Java.dto.UserDto;
//import com.matc.MATC.AccessMent.Java.model.User;
//import com.matc.MATC.AccessMent.Java.mongoRepository.UserRepositoryMo;
//import com.matc.MATC.AccessMent.Java.mongoServices.UserServiceMongo;
//import com.matc.MATC.AccessMent.Java.repository.UserRepository;
//import com.matc.MATC.AccessMent.Java.securityConfigUser.JWTUtility;
//import com.matc.MATC.AccessMent.Java.service.EmployeeService;
//import com.matc.MATC.AccessMent.Java.service.UserService;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@SecurityRequirement(name = "Authentication")
//@RequestMapping("/api/v1")
//public class UserController {
//    @Autowired
//    UserService userservice;
//
//    @Autowired
//    JWTUtility jwtUtility;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    UserRepositoryMo userRepositoryMo;
//
//    @Autowired
//    EmployeeService employeeService;
//
//    @Autowired
//    UserServiceMongo userServiceMongo;
//
////    @GetMapping("/user/{id}")
////    public UserDto getUser(@PathVariable("id") Long id) {
////        return userservice.getUserById(id);
////    }
//
//    @GetMapping("/user")
//    public List<User> getAllUser() {
//        return userservice.getAllUser();
//    }
//
//    @DeleteMapping("/user/{id}")
//    public void deleteUser(@PathVariable("id") Long id) {
//        userservice.delete(id);
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userReq) {
//        User user = new User();
//        user.setUserName(userReq.getUserName());
//        user.setPassword(userReq.getPassword());
//        user.setRole(userReq.getRole());
//        user = userservice.save(user);
////        UserMo userMo = new UserMo();                 //MO
////        userMo.setUserName(userReq.getUserName());    //MO
////        userMo.setPassword(userReq.getPassword());    //MO
////        userMo.setRole(userReq.getRole());            //MO
////        userMo = userServiceMongo.save(userMo);       //MO
//        return ResponseEntity.status(HttpStatus.CREATED).body(userReq);
//    }
//
////    @PutMapping("/user/{id}")
////
////    public User update(@PathVariable("id") Long id, @RequestBody User user) throws Exception {
////
////        return userservice.updateUser(id, user);
////    }
//
//    @PostMapping("/admin")
//    public ResponseEntity<Object> admin() throws Exception {
//        User user = userservice.saveAdmin();
////        UserMo userMo = userServiceMongo.saveAdmin();  //MO
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginDto> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            jwtRequest.getUsername(),
//                            jwtRequest.getPassword()
//
//                    )
//            );
//        } catch (BadCredentialsException badCredentialsException) {
//            return null;
//        }
//        final User user = userRepository.findByUserName(jwtRequest.getUsername());
//        final String role = user.getRole();
//
////        final UserMo userMo = userRepositoryMo.findByUserName(jwtRequest.getUsername()); //MO
////        final String role1 = userMo.getRole();   //MO
//
//        final String token = jwtUtility.generateToken(jwtRequest.getUsername(), role);
////        final String token1 = jwtUtility.generateToken(jwtRequest.getUsername(), role1);  //Mo
//
//        final Long id = user.getUserId();
//        final String username = user.getUserName();
//
////        final Long userid = userMo.getUserId(); //MO
////        final String username1 = userMo.getUserName(); //MO
//
//        LoginDto loginDto = new LoginDto();
//        loginDto.setId(id);
////        loginDto.setId(userid); //MO
//        loginDto.setUserName(username);
////        loginDto.setUserName(username1);//MO
//        loginDto.setRole(role);
////        loginDto.setRole(role1); //MO
//        loginDto.setToken(token);
////        loginDto.setToken(token1); //MO
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(loginDto);
//
//    }
//
//}