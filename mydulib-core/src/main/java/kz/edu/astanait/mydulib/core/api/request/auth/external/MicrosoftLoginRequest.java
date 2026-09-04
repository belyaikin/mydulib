package kz.edu.astanait.mydulib.core.api.request.auth.external;

public final class MicrosoftLoginRequest extends ExternalLoginRequest {
    public MicrosoftLoginRequest(String token, DeviceInfo deviceInfo) {
        super("microsoft", token, null, deviceInfo, null, null, null);
    }
}
