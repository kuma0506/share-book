package share.book.domain.share.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import share.book.base.ApiClientBase;
import share.book.domain.book.BookRepository;
import share.book.domain.book.ProductEntity;
import share.book.domain.dto.BookDto;
import share.book.domain.dto.Response;
import share.book.domain.dto.UserAccountDto;
import share.book.domain.infras.util.ShortUuidUtils;
import share.book.domain.product.ProductRepository;
import share.book.domain.share.book.common.TestItems;
import share.book.domain.user.account.UserAccountRepository;
import share.book.domain.user.detail.UserDetailRepository;

public class ProductControllerTest extends ApiClientBase {

    private final String BASE_PATH = "/api/products";
    private final String RANDAM_EMAIL = ShortUuidUtils.generate() + "@email.com";
    private final String RANDAM_NAME = ShortUuidUtils.generate();
    private final String RANDAM_FIRST_NAME = ShortUuidUtils.generate();
    private final String RETURN_DATE = "2021-01-01";

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private TestItems items;
    @Autowired
    private ModelMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        post("/api/users", items.getUserAccount(RANDAM_EMAIL, RANDAM_FIRST_NAME), Response.class).getMessage();
        post("/api/books", items.getBookDto(RANDAM_NAME), Response.class).getMessage();
    }

    @AfterEach
    void clean() {
        productRepository.findByReturnDate(RETURN_DATE).ifPresent(productRepository::delete);
        userDetailRepository.findByFirstName(RANDAM_FIRST_NAME).ifPresent(userDetailRepository::delete);
        userAccountRepository.findByEmail(RANDAM_EMAIL).ifPresent(userAccountRepository::delete);
        bookRepository.findByName(RANDAM_NAME).ifPresent(bookRepository::delete);
    }

    @Test
    void createTest() throws Exception {

        BookDto book = mapper.map(bookRepository.findByName(RANDAM_NAME).get(), BookDto.class);
        UserAccountDto account = mapper.map(userAccountRepository.findByEmail(RANDAM_EMAIL).get(),
                UserAccountDto.class);

        post(BASE_PATH,
                items.getProduct(book,
                        account, RETURN_DATE),
                Response.class);

        ProductEntity entity = productRepository.findByReturnDate(RETURN_DATE).orElse(new ProductEntity());
        assertThat(entity.getReturnDate(), is(RETURN_DATE));
    }
}
