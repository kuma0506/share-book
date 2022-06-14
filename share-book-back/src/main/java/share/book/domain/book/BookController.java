package share.book.domain.book;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import share.book.domain.dto.BookDto;
import share.book.domain.dto.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper mapper;

    @GetMapping("{id}")
    public BookDto get(@PathVariable String id) {
        return mapper.map(bookService.findById(id).get(), BookDto.class);
    }

    @GetMapping("test/{testId}")
    public Response test(@RequestParam String testId) {
        return Response.builder().message(testId).build();
    }

    @PostMapping
    public Response create(@RequestBody BookDto dto) {
        BookEntity entity = mapper.map(dto, BookEntity.class);
        return Response.builder().message(bookService.create(entity)).build();
    }

}
