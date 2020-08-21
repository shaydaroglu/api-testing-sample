package main.java.booking.api.clients;

import main.java.booking.api.dtos.user.UserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth")
    Call<String> authenticate(@Body UserDTO userDTOCredentials);
}
