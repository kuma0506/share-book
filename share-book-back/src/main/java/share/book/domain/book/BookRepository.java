package share.book.domain.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// CrudRepositoryを継承することでクラスを自動で作ってくれるため、簡単なCRUD操作ができる
public interface BookRepository extends CrudRepository<BookEntity, String>, JpaSpecificationExecutor<BookEntity> {

    // junit用
    @Query("select book from BookEntity book WHERE name = :name")
    Optional<BookEntity> findByName(@Param("name") String name);
}
