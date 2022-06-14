package share.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserAccountDto {
    private String id;
    private String email;
    private String phoneNumber;
    private UserDetailDto detail;
}
