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
import java.util.Objects;

public final class OkHttpMyDuClient implements MyDuClient {
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    private OkHttpMyDuClient(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public <T> T execute(MyDuRequest<T> myDuRequest, MyDuCredentials credentials) throws MyDuApiException {
        var builder = new Request.Builder()
                .url(MyDuUrl.DEFAULT + myDuRequest.getPath());

        var cookies = new ArrayList<String>();
        if (!Objects.equals(credentials, MyDuCredentials.none())) {
            cookies.add("access_token=" + credentials.accessToken());
            cookies.add("refresh_token=" + credentials.refreshToken());
            cookies.add("is_authenticated=true");
        }
        if (!cookies.isEmpty()) {
            builder.header("Cookie", String.join("; ", cookies));
        }

        builder.header("Content-Type", "application/json");

        switch (myDuRequest.getMethod()) {
            case GET -> builder.get();
            case POST -> {
                try {
                    var json = mapper.writeValueAsString(myDuRequest.getBody());

                    var requestBody = RequestBody.create(
                            json,
                            MediaType.parse("application/json")
                    );

                    builder.post(requestBody);
                } catch (JsonProcessingException e) {
                    throw new MyDuApiException("Failed to serialize request body", e);
                }
            }
        }

        var request = builder.build();

        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new MyDuApiException("Unable to retrieve a response from DU");
            }

            var body = response.body().string();

            return mapper.readValue(body, myDuRequest.getResponseType());
        } catch (IOException e) {
            throw new MyDuApiException("Unexpected error", e);
        }
    }
}
