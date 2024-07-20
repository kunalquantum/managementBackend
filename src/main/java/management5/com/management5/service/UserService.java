package management5.com.management5.service;

import lombok.RequiredArgsConstructor;
import management5.com.management5.Enum.Role;
import management5.com.management5.dto.payload.*;
import management5.com.management5.exception.InvalidDataFoundException;
import management5.com.management5.exception.ResourceNahiMilaException;
import management5.com.management5.exception.WhoAreYouException;
import management5.com.management5.model.UserModel;
import management5.com.management5.model.eventModel;
import management5.com.management5.model.fileModel;
import management5.com.management5.repository.Eventrepository;
import management5.com.management5.repository.Filerepository;
import management5.com.management5.repository.UserRepository;
import management5.com.management5.security.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.List;

import static management5.com.management5.Enum.Role.SALES;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    private final Filerepository filerepository;

    private final Eventrepository eventrepository;
    private final JwtHelper jwtHelper;

    public String getToken(String username){
        return jwtHelper.generateToken(username);
    }

    public boolean checkUserByUsername(String username){

        return userRepository.existsByUsername(username);

    }

    public boolean checkUserByEmail(String email){

        return userRepository.existsByEmail(email);

    }
    public boolean checkUserByPassword(String password){

        return userRepository.existsByPassword(password);

    }

    public String createEmployee(UserRegisteration userRegisteration){



        //username
        if(null == userRegisteration)

            throw new ResourceNahiMilaException("To complete the registeration, please provide neccessary details");
        //firstname
        if (userRegisteration.getUsername() == null || userRegisteration.getUsername().isBlank())

            throw new InvalidDataFoundException("Username already exist");

        if (userRegisteration.getUsername().length() > 20)

            throw new InvalidDataFoundException("The username should not exceed 20 characters");

        if (checkUserByUsername(userRegisteration.getUsername()))

            throw new InvalidDataFoundException("The Username Already exists");

        if(userRegisteration.getFirst_name() == null || userRegisteration.getFirst_name().isBlank())

            throw  new InvalidDataFoundException("Enter the firstname");

        if(userRegisteration.getLast_name() == null || userRegisteration.getLast_name().isBlank())

            throw  new InvalidDataFoundException("Enter the Last name");

        if(userRegisteration.getEmail() == null || userRegisteration.getEmail().isBlank())

            throw new InvalidDataFoundException("Enter the email for registeration");

        if(userRegisteration.getPassword() == null || userRegisteration.getPassword().isBlank())

            throw new InvalidDataFoundException("Enter the password");

        if(!userRegisteration.getPassword().equals(userRegisteration.getConfirm_password()))

            throw new InvalidDataFoundException("Mismatch confirm passoword and password");

        if(userRegisteration.getRole()==null)

            throw new InvalidDataFoundException("provide the Role");

        Role role=Enum.valueOf(Role.class,userRegisteration.getRole());

        if(checkUserByEmail(userRegisteration.getEmail()))

            throw new InvalidDataFoundException("Email Exists");


        UserModel user= UserModel.builder()


                .firstName(userRegisteration.getFirst_name())

                .lastName(userRegisteration.getLast_name())

                .username(userRegisteration.getUsername())

                .email(userRegisteration.getEmail())

                .password(userRegisteration.getPassword())

                .role(role)

                .build();

        userRepository.save(user);

        return "SuccessFully Registered";
    }

    public List<eventModel> getevents(){
        return eventrepository.findAll();
    }

    public String createEvent(Eventfile eventfile){


        String username=jwtHelper.extractUsername(eventfile.getUsername());
        UserModel user=userRepository.findByUsername(username).orElseThrow(()->new WhoAreYouException("Only admins can create a file "));


       eventModel event= eventModel.builder()
               .description(eventfile.getDescription())
               .date(eventfile.getDate())
               .link(eventfile.getLink())
               .build();


       eventrepository.save(event);


        return "Added Event";

    }


    public String createFile(Adminfile adminFile){


        String username=jwtHelper.extractUsername(adminFile.getUsername());
        UserModel user=userRepository.findByUsername(username).orElseThrow(()->new WhoAreYouException("Only admins can create a file "));


        fileModel file=fileModel.builder()
                .path(adminFile.getPath())
                .origin(adminFile.getOrigin())
                .build();


        filerepository.save(file);



return "done";

    }

    public String login(UserLogin userLogin)
    {

       UserModel user=userRepository
                .findByUsernameOrEmail(userLogin.getUsername(),userLogin.getEmail())
                .orElseThrow(() -> new ResourceNahiMilaException("Username or email does not exists"));

       if(!user.getPassword().equals(userLogin.getPassword()))
           throw new WhoAreYouException("MIsmatch");

       String token = jwtHelper.generateToken(user.getUsername());

       return token;
    }


    public String getToken(TokenUsername tokenUsername){
        UserModel user=userRepository
                .findByUsername(tokenUsername.getUsername()).orElseThrow(() -> new ResourceNahiMilaException("Username or email does not exists"));

        return jwtHelper.generateToken(user.getUsername());
    }

    public String checkUsernameByToken(String Token){

            String username1 = jwtHelper.extractUsername(Token);
            UserModel user = userRepository.findByUsername(username1).orElseThrow(() -> new WhoAreYouException("UnAuthorized"));

            //check for existance
            if(user==null)
                return "UnAuthorized  ";

            return "Authorized";

    }


    public String CheckRole(TokenUsername tokenUsername){

        String username=tokenUsername.getUsername();

        if(userRepository.existsByUsername(username)){

            UserModel user=userRepository.findByUsername(username).orElseThrow(()-> new WhoAreYouException("username does exist"));
            Role role=user.getRole();


            if(role.equals("DEFAULT")){

                return "The role is Default";
            }
            else if(role.equals("ADMIN")){
                return "The Role is ADMIN";

            } else if (role.equals("SALES"))
            {
                return "The Role is SALES";
            }
        }
        return "No Role So Far";
    }

    public String  AssignRole(String username,Role role){

        UserModel userModel=userRepository.findByUsername(username).orElseThrow(() ->
                new WhoAreYouException("No such User Found"));

        userModel.setRole(role);

        userRepository.save(userModel);

        switch (role){

            case ADMIN -> {
                return "Admin Role Assigned ";


            }
            case SALES -> {
                return "Sales Role Assigned ";
            }

            default -> {
                return "Default Role Remained";
            }
        }
    }


    public String setuserbuusername(Role role,TokenUsername tokenUsername)
    {
        String username=jwtHelper.extractUsername(tokenUsername.getUsername());

        UserModel user=userRepository.findByUsername(username).orElseThrow(()->new WhoAreYouException("Username not found"));
        if(isadmin(tokenUsername))
            user.setRole(role);
        return "ADMIN set successfully";


    }

    public String setuserbyid(Long id,Role role){

        UserModel user=userRepository.findById(id).orElseThrow(()->new WhoAreYouException("The ID does not exists"));




        if(user==null)
            return " regret to inform you that the user corresponding to the provided ID could not be located.";

        if(Role.SALES.equals(user.getRole()))
            return "Not Admin Sales detected";

        if(Role.DEFAULT.equals((user.getRole())))
            return "Not Admin Default detected";


        AssignRole(user.getUsername(),role);
        return "Role Assigned Successfully";



    }



    public Role checkRole(TokenUsername tokenUsername){

        String username=jwtHelper.extractUsername(tokenUsername.getUsername());
        UserModel user=userRepository.findByUsername(username).orElseThrow(()->new WhoAreYouException("Username not found"));

        return user.getRole();


    }

    public boolean isadmin(TokenUsername tokenUsername)
    {
        Role role=checkRole(tokenUsername);
        if(Role.ADMIN.equals(role)){
            return true;}
        return false;
    }

    public boolean isSales(TokenUsername tokenUsername){

        Role role=checkRole(tokenUsername);
        if(SALES.equals(role))
            return true;
        return false;

    }

    public UserModel getCurrentUser(Long id){
        return userRepository.findById(id).orElseThrow(
                ()->new WhoAreYouException("User Does Not Exist"));
    }



}
