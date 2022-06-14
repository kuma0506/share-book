package share.book.domain.rental;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// CrudRepositoryを継承することでクラスを自動で作ってくれるため、簡単なCRUD操作ができる
public interface RentalRepository extends CrudRepository<RentalEntity, String> {

    // junit用
    @Query("select rental from RentalEntity rental WHERE rentalDate = :rentalDate")
    Optional<RentalEntity> findByRentalDate(@Param("rentalDate") String rentalDate);

}
