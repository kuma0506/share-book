package share.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// getter, setterなどを自動生成する
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private String id;
    private String productId;
    private String userAccountId;
    private String rentalDate;
    private String rentalStates;
}
