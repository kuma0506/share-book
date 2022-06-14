package share.book.domain.share.book.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import share.book.domain.book.BookRepository;
import share.book.domain.dto.BookDto;
import share.book.domain.dto.ProductDto;
import share.book.domain.dto.RentalDto;
import share.book.domain.dto.UserAccountDto;
import share.book.domain.dto.UserDetailDto;
import share.book.domain.product.ProductRepository;
import share.book.domain.user.account.UserAccountRepository;

@Component
public class TestItems {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private ProductRepository productRepository;

    public String getBookId(String name) {
        return bookRepository.findByName(name).orElseThrow().getId();
    }

    public String getAccountId(String email) {
        return userAccountRepository.findByEmail(email).orElseThrow().getId();
    }

    public String getProductId(String returnDate) {
        return productRepository.findByReturnDate(returnDate).orElseThrow().getId();
    }

    public ProductDto getProduct(BookDto book, UserAccountDto account, String returnDate) {
        return ProductDto.builder().book(book).userAccount(account).description("説明")
                .returnDate(returnDate)
                .rentalPeriod(1).build();
    }

    public UserAccountDto getUserAccount(String email, String firstName) {
        return UserAccountDto.builder().email(email).phoneNumber("090-1234-5678").detail(getUserDetail(firstName))
                .build();
    }

    public UserDetailDto getUserDetail(String name) {
        return UserDetailDto.builder().firstName(name).lastName("hoge").nickName("hoge").birthDay("1999-12-31")
                .profile("hogehogehoge").address("東京都品川区")
                .build();
    }

    public BookDto getBookDto(String name) {
        return BookDto.builder().name(name).author("book太郎").dateOfIssue("2020-12-31").build();
    }

    public RentalDto getRental(String productId, String userAccountId, String rentalDate) {
        return RentalDto.builder().productId(productId).userAccountId(userAccountId).rentalDate(rentalDate)
                .rentalStates("USER-POSSESSION").build();
    }
}
