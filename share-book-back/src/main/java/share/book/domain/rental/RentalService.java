package share.book.domain.rental;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import share.book.domain.account.UserAccountEntity;
import share.book.domain.book.ProductEntity;
import share.book.domain.infras.util.ShortUuidUtils;
import share.book.domain.product.ProductRepository;
import share.book.domain.user.account.UserAccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final ProductRepository productRepository;
    private final UserAccountRepository userAccountRepository;
    private final RentalRepository rentalRepository;

    @Transactional
    public String create(RentalEntity entity, String productId, String accountId) {

        ProductEntity product = productRepository.findById(productId).orElseThrow();
        UserAccountEntity acccount = userAccountRepository.findById(accountId).orElseThrow();

        entity.setId(ShortUuidUtils.generate());
        entity.setProduct(product);
        entity.setUserAccount(acccount);

        rentalRepository.save(entity);
        return entity.getId();
    }
}
