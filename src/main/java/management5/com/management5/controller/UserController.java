package management5.com.management5.controller;

import lombok.RequiredArgsConstructor;
import management5.com.management5.Enum.Role;
import management5.com.management5.annotations.Authorization;
import management5.com.management5.dto.payload.*;
import management5.com.management5.model.UserModel;

import management5.com.management5.model.eventModel;
import management5.com.management5.repository.UserRepository;
import management5.com.management5.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/getevent")
    public ResponseEntity<?> getEvents(@PathVariable Username username){
        List<eventModel> list=userService.getevents(username);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/setevent")
    public ResponseEntity<?> setevent(
            @RequestBody Eventfile eventfile){

        String message=userService.createEvent(eventfile);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }
    @PostMapping("/setfile")
    public ResponseEntity<?> createfile(
            @RequestBody Adminfile adminfile
    ){
        String message = userService.createFile(adminfile);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/gettoken")
    @Authorization
    public ResponseEntity<?> getToken(
            @RequestBody TokenUsername tokenUsername){

        String mesaage=userService.getToken(tokenUsername.getUsername());

        return ResponseEntity
                .ok(mesaage);
    }

    @GetMapping("/get")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin){
        String isLoggedIn = userService.login(userLogin);
        return ResponseEntity.ok(isLoggedIn);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody UserRegisteration registeration
    )
    {
        String message = userService.createEmployee(registeration);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(message);
    }
    @GetMapping("/checktoken")
    public ResponseEntity<?> secureEndpoint(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization
    ){
        System.out.println(authorization);

            String message = userService.checkUsernameByToken(authorization);
            return ResponseEntity.ok(message);
    }
    @PutMapping("/setrole")
    public ResponseEntity<?> roleadmin(@RequestBody Roleassign role){
            String message = userService.AssignRole(role.getUsername(), role.getRole());
            return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PutMapping("/setbyid/{id}")
    public ResponseEntity<?>setbyid(@PathVariable Long id, @RequestBody Role role){
            String message = userService.setuserbyid(id, role);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
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
