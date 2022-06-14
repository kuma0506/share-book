package share.book.domain.specification;

import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import share.book.domain.book.ProductEntity;

@Component
public class CustamSpec {
    public Specification<ProductEntity> prodoctWhereDesc(String description) {
        return (root, query, cb) -> cb.like(root.get("description"), "%" + description + "%");
    }

    public Specification<ProductEntity> bookWhereName(String name) {
        return (root, query, cb) -> cb.like(root.join("book", JoinType.LEFT).get("name"), "%" + name + "%");
    }
}
