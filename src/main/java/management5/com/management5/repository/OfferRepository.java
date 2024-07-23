package management5.com.management5.repository;

import management5.com.management5.model.FileModel;
import management5.com.management5.model.OfferModel;
import management5.com.management5.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OfferRepository extends JpaRepository<OfferModel,Long> {

    @Override
    Optional<OfferModel> findById(Long aLong);

    Optional<OfferModel>findByName(String Name);
}
