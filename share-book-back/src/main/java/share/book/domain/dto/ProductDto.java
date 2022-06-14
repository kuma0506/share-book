package share.book.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    public int getTotalReting() {
        return getReviews().stream().mapToInt(o -> o.getEvaluation()).sum();
    }

    @JsonGetter
    public int getCount() {
        return getReviews().size();
    }

}
