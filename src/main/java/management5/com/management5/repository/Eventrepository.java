package management5.com.management5.repository;

import management5.com.management5.model.eventModel;
import management5.com.management5.model.fileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Eventrepository extends JpaRepository<eventModel,Long> {

    @Override
    public List<eventModel> findAll();
}
