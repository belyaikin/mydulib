package kz.edu.astanait.mydulib.core.api;

import kz.edu.astanait.mydulib.core.exception.MyDuApiException;

public interface MyDuClient {
    <T> T execute(MyDuRequest<T> myDuRequest, MyDuCredentials credentials) throws MyDuApiException;
}
