package kz.edu.astanait.mydulib.core.api.response.auth;

public record MyDuAuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        int expiresIn
) {
}
