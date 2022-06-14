package share.book.domain.share.book;

import share.book.base.ApiClientBase;
import share.book.domain.book.BookEntity;
import share.book.domain.book.BookRepository;
import share.book.domain.dto.Response;
import share.book.domain.infras.util.ShortUuidUtils;
import share.book.domain.share.book.common.TestItems;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

public class BookControllerTest extends ApiClientBase {

    private final String BASE_PATH = "/api/books";
    private final String RANDAM_NAME = ShortUuidUtils.generate();

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestItems items;

    @AfterEach
    public void clean() {
        bookRepository.findByName(RANDAM_NAME).ifPresent(bookRepository::delete);
    }

    @Test
    void createTest() throws Exception {
        post(BASE_PATH, items.getBookDto(RANDAM_NAME), Response.class);

        Optional<BookEntity> entity = bookRepository.findByName(RANDAM_NAME);
        assertThat(entity.get().getName(), is(RANDAM_NAME));
    }
}
