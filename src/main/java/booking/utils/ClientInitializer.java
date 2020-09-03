package main.java.booking.utils;

import main.java.booking.utils.interceptors.AuthInterceptor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static main.java.booking.utils.ConfigReader.getConfig;

public class ClientInitializer {

    /**
     * Initializing Retrofit as a REST Client of project with the OkHttp Authenticating Interceptor.
     * @return Retrofit client.
     */
    @SneakyThrows
    public static Retrofit setupRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new AuthInterceptor(getConfig("username"), getConfig("password")))
                .build();
        return new Retrofit.Builder()
                .baseUrl(getConfig("TARGET_API_URL"))
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
