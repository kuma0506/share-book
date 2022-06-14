package share.book.domain.user.account;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import share.book.domain.account.UserAccountEntity;
import share.book.domain.dto.UserAccountDto;
import share.book.domain.infras.util.ShortUuidUtils;
import share.book.domain.user.detail.UserDetailService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserDetailService userDetailService;

    @Transactional
    public String create(UserAccountDto dto) {

        UserAccountEntity entity = new UserAccountEntity();

        entity.setId(ShortUuidUtils.generate());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());

        userAccountRepository.save(entity);

        Optional<UserAccountEntity> savedEntity = userAccountRepository.findById(entity.getId());

        userDetailService.create(savedEntity.get(), dto.getDetail());

        return entity.getId();
    }
}
