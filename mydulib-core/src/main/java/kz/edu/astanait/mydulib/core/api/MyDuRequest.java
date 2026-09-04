package kz.edu.astanait.mydulib.core.api;

public interface MyDuRequest<T> {
    default HttpMethod getMethod() { return HttpMethod.GET; }

    String getPath();
    default Object getBody() { return null; }

    Class<T> getResponseType();
}
