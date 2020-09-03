package main.java.booking.utils.interceptors;

import okhttp3.*;
import java.io.IOException;

import static main.java.booking.utils.ConfigReader.getConfig;

public class AuthInterceptor implements Interceptor {

    private String authToken;
    private String basicAuth;

    /**
     * Interceptor for authentication process.
     * PS: For update and delete request auth token alone does not work
     * Basic authentication have been added later as extra.
     * @param username Username for the /auth endpoint and basic auth.
     * @param password Password for the /auth endpoint and basic auth.
     * @throws IOException
     */
    public AuthInterceptor(String username, String password) throws IOException{
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request authRequest = new Request.Builder()
                .url(getConfig("TARGET_API_URL") + "auth")
                .post(formBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(authRequest);
        Response res = call.execute();
        assert res.code() == 200;

        this.basicAuth = Credentials.basic(username, password);
        this.authToken = res.body() != null ? res.body().string()
                .replace("{","")
                .replace(" ","")
                .replace("\"", "")
                .replace("}","") : null;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        CacheControl cacheControl;
        Request authenticatedRequest = request.newBuilder()
                .header("Cookie", authToken)
                .addHeader("Authorization", basicAuth).build();
        return chain.proceed(authenticatedRequest);
    }

}

