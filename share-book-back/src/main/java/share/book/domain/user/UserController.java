package share.book.domain.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import share.book.domain.dto.Response;
import share.book.domain.dto.UserAccountDto;
import share.book.domain.user.account.UserAccountService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserAccountService userAccountService;

    @PostMapping
    public Response create(@RequestBody UserAccountDto dto) {
        // todo:Keycloakで実装予定
        return Response.builder().message(userAccountService.create(dto)).build();
    }

}
