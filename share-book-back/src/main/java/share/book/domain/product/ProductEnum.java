package share.book.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductEnum {

    @AllArgsConstructor
    @Getter
    public enum Rating {
        RATING0("rating-0", "全て", 0),
        RATING1("rating-1", "評価5", 5),
        RATING2("rating-2", "評価4以上", 4),
        RATING3("rating-3", "評価3以上", 3);

        private String id;
        private String displayName;
        private int value;

    }

    public static int getRatingValue(String id) {
        for (Rating rtg : Rating.values()) {
            if (rtg.id.equals(id)) {
                return rtg.getValue();
            }
        }
        return 0;
    }
}
