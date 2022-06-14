package share.book.domain.rental;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import share.book.domain.dto.RentalDto;
import share.book.domain.dto.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final ModelMapper mapper;

    @PostMapping
    public Response create(@RequestBody RentalDto dto) {
        RentalEntity entity = mapper.map(dto, RentalEntity.class);
        String id = rentalService.create(entity, dto.getProductId(), dto.getUserAccountId());

        return Response.builder().message(id).build();
    }
}
