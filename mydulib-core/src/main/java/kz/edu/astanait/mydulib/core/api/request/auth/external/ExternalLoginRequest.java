package kz.edu.astanait.mydulib.core.api.request.auth.external;

import kz.edu.astanait.mydulib.core.api.HttpMethod;
import kz.edu.astanait.mydulib.core.api.MyDuRequest;
import kz.edu.astanait.mydulib.core.api.response.auth.MyDuAuthResponse;

public abstract class ExternalLoginRequest implements MyDuRequest<MyDuAuthResponse> {
    public record DeviceInfo(
            String platform,
            String deviceId
    ) { }

    private final String provider;
    private final String token;
    private final String email;
    private final DeviceInfo deviceInfo;
    private final String avatarUrl;
    private final String firstName;
    private final String lastName;

    protected ExternalLoginRequest(String provider, String token, String email, DeviceInfo deviceInfo, String avatarUrl, String firstName, String lastName) {
        this.provider = provider;
        this.token = token;
        this.email = email;
        this.deviceInfo = deviceInfo;
        this.avatarUrl = avatarUrl;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Object getBody() {
        return new Body(provider, token, email, deviceInfo, avatarUrl, firstName, lastName);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getPath() {
        return "/api/auth/external-login";
    }

    @Override
    public Class<MyDuAuthResponse> getResponseType() {
        return MyDuAuthResponse.class;
    }

    private record Body(
            String provider,
            String token,
            String email,
            DeviceInfo deviceInfo,
            String avatarUrl,
            String firstName,
            String lastName
    ) {}
}
