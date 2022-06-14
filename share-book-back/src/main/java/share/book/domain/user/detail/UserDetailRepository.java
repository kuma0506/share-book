package share.book.domain.user.detail;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import share.book.domain.account.UserDetailEntity;

@Repository
// CrudRepositoryを継承することでクラスを自動で作ってくれるため、簡単なCRUD操作ができる
public interface UserDetailRepository extends CrudRepository<UserDetailEntity, String> {

    // junit用
    @Query("select detail from UserDetailEntity detail WHERE firstName = :firstName")
    Optional<UserDetailEntity> findByFirstName(@Param("firstName") String firstName);
}
