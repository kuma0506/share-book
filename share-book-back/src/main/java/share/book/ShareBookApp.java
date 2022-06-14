package share.book;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import com.fasterxml.jackson.core.type.TypeReference;
import jp.co.monocrea.monolib4j.infra.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@SpringBootApplication
@RequiredArgsConstructor
public class ShareBookApp {
    public static void main(String[] args) {
        SpringApplication.run(ShareBookApp.class, args);

    }

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(
                new AbstractConverter<String, List<?>>() {

                    @Override
                    protected List<?> convert(String source) {
                        return StringUtils.isEmpty(source)
                                ? new ArrayList<>()
                                : JsonUtils.str2obj(source, new TypeReference<List<?>>() {
                                });
                    }
                });

        mapper.addConverter(
                new AbstractConverter<List<?>, String>() {

                    @Override
                    protected String convert(List<?> source) {
                        return JsonUtils.obj2str(source);
                    }
                });

        return mapper;
    }

}
