package share.book.domain.product;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import share.book.domain.book.ProductEntity;
import share.book.domain.infras.util.ShortUuidUtils;
import share.book.domain.specification.CustamSpec;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CustamSpec custamSpec;

    public ProductEntity findById(String id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Iterable<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findByWord(String word) {
        Specification<ProductEntity> spec = Specification.where(custamSpec.prodoctWhereDesc(word))
                .or(custamSpec.bookWhereName(word));
        return productRepository.findAll(spec);
    }

    @Transactional
    public String create(ProductEntity entity) {

        entity.setId(ShortUuidUtils.generate());
        productRepository.save(entity);

        return entity.getId();
    }

}
