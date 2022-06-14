package share.book.base.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.annotation.PostConstruct;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import share.book.base.ApiClient;

@Component
@RequiredArgsConstructor
public class ApiClientImpl implements ApiClient {
    private final WebApplicationContext context;

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    private MockHttpServletRequestBuilder request;

    @PostConstruct
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public ApiClient apiGet(String path, Object... urlVariables) throws Exception {
        this.request = get(path, urlVariables);
        return this;
    }

    @Override
    public ApiClient apiPost(String path, Object body, Object... urlVariables) throws Exception {
        this.request = post(path, urlVariables)
                .content(mapper.writeValueAsString(body));
        return this;
    }

    @Override
    public ApiClient apiDelete(String path, Object... urlVariables) throws Exception {
        this.request = delete(path, urlVariables);
        return this;
    }

    @Override
    public <T> T perform(Class<T> responseType) throws Exception {
        String response = fetchJsonResult();
        return mapper.readValue(response, responseType);
    }

    @Override
    public <T> T perform(TypeReference<T> responseType) throws Exception {
        String response = fetchJsonResult();
        return mapper.readValue(response, responseType);
    }

    private String fetchJsonResult() throws Exception {
        if (Objects.isNull(request)) {
            throw new IllegalStateException("should call apiGet or apiPost before call perform");
        }

        String response = mvc.perform(request.contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        return response;
    }
}
