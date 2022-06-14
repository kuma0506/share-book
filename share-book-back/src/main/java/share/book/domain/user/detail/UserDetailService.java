package share.book.domain.user.detail;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import share.book.domain.account.UserAccountEntity;
import share.book.domain.account.UserDetailEntity;
import share.book.domain.dto.UserDetailDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserDetailRepository userDetailRepositoryitory;

    @Transactional
    public void create(UserAccountEntity userAccountEntity, UserDetailDto dto) {

        UserDetailEntity entity = new UserDetailEntity();

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNickName(dto.getNickName());
        entity.setBirthDay(dto.getBirthDay());
        entity.setProfile(dto.getProfile());
        entity.setAddress(dto.getAddress());
        entity.setUserAccount(userAccountEntity);

        userDetailRepositoryitory.save(entity);
    }
}
