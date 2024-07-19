package management5.com.management5.repository;

import management5.com.management5.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;



public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String emaisuhhhxdsxssl);

    boolean existsByPassword(String password);

    Optional<UserModel>findById(long id);



    Optional<UserModel>findByUsernameOrEmail(String username,String email);

//    @Query("SELECT u.password FROM UserModel u WHERE u.username=:username")
//    String findPasswordByUsername(@Param("username") String username);


}
