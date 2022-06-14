package share.book.domain.book;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import share.book.domain.infras.util.ShortUuidUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepositoryitory;

    public Optional<BookEntity> findById(String id) {
        return bookRepositoryitory.findById(id);
    }

    @Transactional
    public String create(BookEntity entity) {
        entity.setId(ShortUuidUtils.generate());
        bookRepositoryitory.save(entity);
        return entity.getId();
    }
}
