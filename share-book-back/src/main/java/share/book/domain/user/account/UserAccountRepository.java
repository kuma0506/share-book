package share.book.domain.user.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import share.book.domain.account.UserAccountEntity;

@Repository
// CrudRepositoryを継承することでクラスを自動で作ってくれるため、簡単なCRUD操作ができる
public interface UserAccountRepository extends CrudRepository<UserAccountEntity, String> {

    // junit用
    @Query("select account from UserAccountEntity account"
            + " LEFT JOIN FETCH account.userDetail userDetail"
            + " WHERE email = :email")
    Optional<UserAccountEntity> findByEmail(@Param("email") String email);
}
