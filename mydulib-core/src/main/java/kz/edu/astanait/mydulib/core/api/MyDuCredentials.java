package kz.edu.astanait.mydulib.core.api;

public record MyDuCredentials(String accessToken, String refreshToken, boolean isAuthenticated) {
    public MyDuCredentials {
        if (isAuthenticated && accessToken == null) {
            throw new IllegalArgumentException("isAuthenticated=true requires a non-null accessToken");
        }
    }

    public static MyDuCredentials none() {
        return new MyDuCredentials(null, null, false);
    }

    public static MyDuCredentials of(String accessToken, String refreshToken) {
        return new MyDuCredentials(accessToken, refreshToken, true);
    }
}
