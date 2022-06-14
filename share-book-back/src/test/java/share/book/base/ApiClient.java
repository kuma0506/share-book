package share.book.base;

import com.fasterxml.jackson.core.type.TypeReference;

public interface ApiClient {
    public ApiClient apiGet(String path, Object... urlVariables) throws Exception;

    public ApiClient apiPost(String path, Object body, Object... urlVariables)
            throws Exception;

    public ApiClient apiDelete(String path, Object... urlVariables) throws Exception;

    public <T> T perform(Class<T> responseType) throws Exception;

    public <T> T perform(TypeReference<T> responseType) throws Exception;
}
