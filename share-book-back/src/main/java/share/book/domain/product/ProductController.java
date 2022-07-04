package share.book.domain.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import share.book.domain.book.ProductEntity;
import share.book.domain.dto.ProductDto;
import share.book.domain.dto.Response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ModelMapper mapper;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return mapper.map(productService.findAll(), new TypeToken<List<ProductDto>>() {
        }.getType());
    }

    @GetMapping("/search")
    public List<ProductDto> search(@RequestParam("word") String word, @RequestParam("ratingId") String ratingId) {
        
        int ratingValue = ProductEnum.getRatingValue(ratingId);

        List<ProductDto> products = mapper.map(productService.findByWord(word), new TypeToken<List<ProductDto>>() {
        }.getType());
        // TODO 暫定対応：実装方法を再検討
        return products.stream().filter(product -> product.getAverageRating().compareTo(new BigDecimal(ratingValue)) != -1).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable("id") String id) {
        return mapper.map(productService.findById(id), ProductDto.class);
    }

    @PostMapping
    public Response create(@RequestBody ProductDto dto) {

        ProductEntity entity = mapper.map(dto, ProductEntity.class);

        String id = productService.create(entity);
        return Response.builder().message(id).build();
    }

}
