package share.book.domain.share.book;

import share.book.domain.infras.util.ShortUuidUtils;
import share.book.domain.product.ProductRepository;
import share.book.domain.rental.RentalEntity;
import share.book.domain.rental.RentalRepository;
import share.book.domain.share.book.common.TestItems;
import share.book.domain.user.account.UserAccountRepository;
import share.book.domain.user.detail.UserDetailRepository;
import share.book.domain.dto.BookDto;
import share.book.domain.dto.Response;
import share.book.domain.dto.UserAccountDto;
import share.book.base.ApiClientBase;
import share.book.domain.book.BookRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RentalControllerTest extends ApiClientBase {

    private final String BASE_PATH = "/api/rentals";
    private final String RANDAM_EMAIL = ShortUuidUtils.generate() + "@email.com";
    private final String RANDAM_NAME = ShortUuidUtils.generate();
    private final String RANDAM_FIRST_NAME = ShortUuidUtils.generate();
    private final String RETURN_DATE = "2021-01-02";
    private final String RENTAL_DATE = "2021-01-03";

    @Autowired
    private RentalRepository rentalRepository;
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
    void setup() throws Exception {
        post("/api/users", items.getUserAccount(RANDAM_EMAIL, RANDAM_FIRST_NAME), Response.class).getMessage();
        post("/api/books", items.getBookDto(RANDAM_NAME), Response.class).getMessage();

        BookDto book = mapper.map(bookRepository.findByName(RANDAM_NAME).get(), BookDto.class);
        UserAccountDto account = mapper.map(userAccountRepository.findByEmail(RANDAM_EMAIL).get(),
                UserAccountDto.class);

        post("/api/products", items.getProduct(book, account, RETURN_DATE),
                Response.class).getMessage();
    }

    @AfterEach
    void clean() {
        rentalRepository.findByRentalDate(RENTAL_DATE).ifPresent(rentalRepository::delete);
        productRepository.findByReturnDate(RETURN_DATE).ifPresent(productRepository::delete);
        userDetailRepository.findByFirstName(RANDAM_FIRST_NAME).ifPresent(userDetailRepository::delete);
        userAccountRepository.findByEmail(RANDAM_EMAIL).ifPresent(userAccountRepository::delete);
        bookRepository.findByName(RANDAM_NAME).ifPresent(bookRepository::delete);
    }

    @Test
    void create() throws Exception {
        post(BASE_PATH, items.getRental(items.getProductId(RETURN_DATE), items.getAccountId(RANDAM_EMAIL), RENTAL_DATE),
                Response.class);

        RentalEntity entity = rentalRepository.findByRentalDate(RENTAL_DATE).orElseThrow();
        assertThat(entity.getRentalDate(), is(RENTAL_DATE));
    }
}
