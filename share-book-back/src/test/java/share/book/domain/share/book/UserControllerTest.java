package share.book.domain.share.book;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import share.book.base.ApiClientBase;
import share.book.domain.account.UserAccountEntity;
import share.book.domain.dto.Response;
import share.book.domain.infras.util.ShortUuidUtils;
import share.book.domain.share.book.common.TestItems;
import share.book.domain.user.account.UserAccountRepository;
import share.book.domain.user.detail.UserDetailRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserControllerTest extends ApiClientBase {

    private final String BASE_PATH = "/api/users";
    private final String RANDAM_EMAIL = ShortUuidUtils.generate() + "@email.com";
    private final String RANDAM_FIRST_NAME = ShortUuidUtils.generate();

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private TestItems items;

    @AfterEach
    public void clean() {
        userDetailRepository.findByFirstName(RANDAM_FIRST_NAME).ifPresent(userDetailRepository::delete);
        userAccountRepository.findByEmail(RANDAM_EMAIL).ifPresent(userAccountRepository::delete);
    }

    @Test
    void createTest() throws Exception {
        post(BASE_PATH, items.getUserAccount(RANDAM_EMAIL, RANDAM_FIRST_NAME), Response.class);

        Optional<UserAccountEntity> entity = userAccountRepository.findByEmail(RANDAM_EMAIL);
        assertThat(entity.get().getEmail(), is(RANDAM_EMAIL));
    }
}
