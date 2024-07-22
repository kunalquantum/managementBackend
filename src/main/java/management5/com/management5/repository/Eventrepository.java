package management5.com.management5.repository;

import management5.com.management5.model.eventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Eventrepository extends JpaRepository<eventModel,Long> {


  
}
