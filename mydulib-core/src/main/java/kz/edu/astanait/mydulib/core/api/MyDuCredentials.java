package kz.edu.astanait.mydulib.core.api;

public record MyDuCredentials(String accessToken, String refreshToken) {
    public static MyDuCredentials none() {
        return new MyDuCredentials(null, null);
    }
}
