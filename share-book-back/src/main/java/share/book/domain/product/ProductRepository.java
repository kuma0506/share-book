package share.book.domain.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import share.book.domain.book.ProductEntity;

@Repository
// CrudRepositoryを継承することでクラスを自動で作ってくれるため、簡単なCRUD操作ができる
public interface ProductRepository
                extends CrudRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {

        static final String BASE_QUERY = "SELECT product from ProductEntity product "
                        + "LEFT JOIN FETCH product.book "
                        + "LEFT JOIN FETCH product.userAccount ";

        @Query(BASE_QUERY + "WHERE product.id = :id")
        Optional<ProductEntity> findById(@Param("id") String id);

        @Query(BASE_QUERY)
        List<ProductEntity> findAll();

        // junit用
        @Query(BASE_QUERY + "WHERE product.returnDate = :returnDate")
        Optional<ProductEntity> findByReturnDate(@Param("returnDate") String returnDate);
}
