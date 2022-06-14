package share.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BookDto {
    private String id;
    private String name;
    private String author;
    private String dateOfIssue;
}
