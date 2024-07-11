package management5.com.management5.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import lombok.RequiredArgsConstructor;
import management5.com.management5.Enum.Role;
import management5.com.management5.annotations.Authorization;
import management5.com.management5.dto.payload.*;
import management5.com.management5.exception.InvalidDataException;
import management5.com.management5.exception.ResourceNotFoundException;
import management5.com.management5.exception.UnauthorizedException;
import management5.com.management5.model.UserModel;
import management5.com.management5.repository.UserRepository;
import management5.com.management5.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {



    private final UserRepository userRepository;
    private final UserService userService;





    @GetMapping("/gettoken")
    @Authorization
    public ResponseEntity<?> getToken(@RequestBody TokenUsername tokenUsername){
        try{
        String mesaage=userService.getToken(tokenUsername.getUsername());
            return ResponseEntity.ok(mesaage);
        }
        catch (UnauthorizedException e ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        }
    }


    @GetMapping("/get")
    public List<UserModel> getAllUsers() {

        return userRepository.findAll();
    }


//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserModel> getUserById(@PathVariable(value = "id") Long id) {
//        return userRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
//    }
//
//    @PatchMapping("/{id}/Role")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> assignRole(@PathVariable Long id, @RequestBody Role role) {
//        return userRepository.findById(id)
//                .map(userModel -> {
//                    userModel.setRole(management5.com.management5.Enum.Role.DEFAULT);
//                    userRepository.save(userModel);
//                    return ResponseEntity.ok().build();
//                })
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
//    }

//    @GetMapping("/isadmin")
//    public ResponseEntity<?> CheckRole(@RequestBody TokenUsername tokenUsername)
//    {
//        return userService.CheckRole(tokenUsername.getUsername());
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin){

        try {
            String isLoggedIn = userService.login(userLogin);

            return ResponseEntity.ok(isLoggedIn);
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (UnauthorizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    //url :
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(

            @RequestBody UserRegisteration registeration
    )
    {
        try {
            String message = userService.createEmployee(registeration);
            return ResponseEntity.
                    status(HttpStatus.CREATED).
                    body(message);
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (InvalidDataException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (IllegalArgumentException e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Roles only allowed are :[DEFAULT, SALES, ADMIN]");

        }



    }

    @GetMapping("/checktoken")
    public ResponseEntity<?> secureEndpoint(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization
    ){
        System.out.println(authorization);
        try {
            String message = userService.checkUsernameByToken(authorization);
            return ResponseEntity.ok(message);
        }
        catch (UnauthorizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        }

    }


//    @PutMapping("/setrolebyusername")
//    public ResponseEntity<?> setrolebyusername(@RequestBody Roleassign tokenUsername)
//
//    {
//        String message=userService.setuserbuusername(tokenUsername.getRole(),tokenUsername.getUsername());
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
//    }



    @PutMapping("/setrole")
    public ResponseEntity<?> roleadmin(@RequestBody Roleassign role){
        try {


            String message = userService.AssignRole(role.getUsername(), role.getRole());
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (UnauthorizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping("/setbyid/{id}")
    public ResponseEntity<?>setbyid(@PathVariable Long id, @RequestBody Role role){

        try {
            String message = userService.setuserbyid(id, role);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        catch (UnauthorizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        }
    }

    @PatchMapping("/{id}/Role")
    public ResponseEntity<?> assignRole(@PathVariable Long id, @RequestBody Role role) {
        UserModel currentUser = userService.getCurrentUser(id);
        if (!currentUser.getRole().equals("ADMIN")) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can assign roles.");
        }

        return userRepository.findById(id)
                .map(userModel -> {
                    userModel.setRole(role);
                    userRepository.save(userModel);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }






}
