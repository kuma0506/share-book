package share.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDetailDto {
    private String firstName;
    private String lastName;
    private String nickName;
    private String birthDay;
    private String profile;
    private String address;
}