package kz.edu.astanait.mydulib.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.edu.astanait.mydulib.core.MyDuUrl;
import kz.edu.astanait.mydulib.core.api.MyDuClient;
import kz.edu.astanait.mydulib.core.api.MyDuCredentials;
import kz.edu.astanait.mydulib.core.api.MyDuRequest;
import kz.edu.astanait.mydulib.core.exception.MyDuApiException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.ArrayList;

public final class OkHttpMyDuClient implements MyDuClient {
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public OkHttpMyDuClient(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public <T> T execute(MyDuRequest<T> myDuRequest, MyDuCredentials credentials) throws MyDuApiException {
        var builder = new Request.Builder()
                .url(MyDuUrl.DEFAULT + myDuRequest.getPath());

        var cookies = new ArrayList<String>();
        if (credentials.accessToken() != null)
            cookies.add("access_token=" + credentials.accessToken());
        if (credentials.refreshToken() != null)
            cookies.add("refresh_token=" + credentials.refreshToken());
        if (credentials.isAuthenticated()) {
            cookies.add("is_authenticated=true");
        }
        if (!cookies.isEmpty()) {
            builder.header("Cookie", String.join("; ", cookies));
        }

        builder.header("Content-Type", "application/json");

        switch (myDuRequest.getMethod()) {
            case GET -> builder.get();
            case POST -> builder.post(toRequestBody(myDuRequest.getBody()));
            case PUT -> builder.put(toRequestBody(myDuRequest.getBody()));
            case DELETE -> builder.delete();
        }

        var request = builder.build();

        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new MyDuApiException("Unable to retrieve a response from My DU");

            var body = response.body().string();

            if (body.isBlank())
                throw new MyDuApiException("My DU response body is empty for " + myDuRequest.getPath());

            return mapper.readValue(body, myDuRequest.getResponseType());
        } catch (IOException e) {
            throw new MyDuApiException("Unexpected error", e);
        }
    }

    private RequestBody toRequestBody(Object body) {
        try {
            var json = mapper.writeValueAsString(body);

            return RequestBody.create(
                    json,
                    MediaType.parse("application/json")
            );
        } catch (JsonProcessingException e) {
            throw new MyDuApiException("Failed to serialize request body", e);
        }
    }
}
