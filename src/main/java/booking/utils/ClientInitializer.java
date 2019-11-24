package booking.utils;

import booking.utils.interceptors.AuthInterceptor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ClientInitializer {

    private static final String username = "admin";
    private static final String password = "password123";
    private static final String API_URL = "https://restful-booker.herokuapp.com/";

    /**
     * Initializing Retrofit as a REST Client of project with the OkHttp Authenticating Interceptor.
     * @return Retrofit client.
     */
    @SneakyThrows
    public static Retrofit setupRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new AuthInterceptor(username, password))
                .build();
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
