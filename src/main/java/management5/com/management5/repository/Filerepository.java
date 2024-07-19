package management5.com.management5.repository;

import management5.com.management5.model.fileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Filerepository extends JpaRepository<fileModel,Long> {

    @Override
    Optional<fileModel> findById(Long aLong);
}