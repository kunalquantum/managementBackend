package management5.com.management5.repository;


import management5.com.management5.model.FileModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Filerepository extends JpaRepository<FileModel,Long> {

    List<FileModel> findAll();

}
