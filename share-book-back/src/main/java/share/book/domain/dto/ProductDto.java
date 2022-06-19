package share.book.domain.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private BookDto book;
    private UserAccountDto userAccount;
    private String description;
    private String returnDate;
    private int rentalPeriod;

    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @JsonGetter
    public BigDecimal getAverageRating() {
        //総合評価
        final BigDecimal averageRating = BigDecimal
                .valueOf(getReviews().stream().mapToInt(o -> o.getEvaluation()).sum());
        //評価数
        final BigDecimal cnt = BigDecimal.valueOf(getReviews().size());

        //総合評価 / 評価数 =　平均評価
        return averageRating.divide(cnt, 2, RoundingMode.HALF_UP );
    }

    @JsonGetter
    public int getNumberofReviews() {
        return getReviews().size();
    }

}
