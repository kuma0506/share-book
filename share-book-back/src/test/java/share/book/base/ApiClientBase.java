package share.book.base;

import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import lombok.extern.slf4j.Slf4j;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class ApiClientBase {

    @Autowired
    ApiClient client;

    @Getter
    @Setter
    private Path exportRoot;

    protected ApiClient getClient() {
        return this.client;
    }

    protected <T> T get(String path, Class<T> responseType, Object... uriVariables) throws Exception {
        log.info("GET {} with {}", path, uriVariables);
        return client.apiGet(path, uriVariables).perform(responseType);
    }

    // TypeReferenceはList、SetなどをO/Rマッパーで変換するときに使う
    protected <T> T get(String path, TypeReference<T> responseType, Object... uriVariables)
            throws Exception {
        log.info("GET {} with {}", path, uriVariables);
        return client.apiGet(path, uriVariables).perform(responseType);
    }

    protected <T> T post(String path, Object request, Class<T> responseType, Object... uriVariables)
            throws Exception {
        log.info("POST {} with {}", path, request);
        return client.apiPost(path, request, uriVariables).perform(responseType);
    }

    protected <T> T delete(String path, Class<T> responseType, Object... uriVariables)
            throws Exception {
        log.info("Delete {} with {}", path, uriVariables);
        return client.apiDelete(path, uriVariables).perform(responseType);
    }
}
