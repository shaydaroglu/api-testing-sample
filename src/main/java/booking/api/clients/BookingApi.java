package booking.api.clients;


import booking.api.dtos.booking.BookingDTO;
import booking.api.dtos.booking.BookingIdDTO;
import booking.api.dtos.booking.BookingExtendedDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BookingApi {

    @GET("booking")
    Call<List<BookingIdDTO>> getBookingIds(
            @Query("firstname") String firstName,
            @Query("lastname") String lastName,
            @Query("checkin") String checkInDate,
            @Query("checkout") String checkOutDate
    );

    @GET("booking/{id}/")
    @Headers("Accept: application/json")
    Call<BookingDTO> getBooking(@Path("id") int bookingId);

    @POST("booking")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<BookingExtendedDTO> createBooking(@Body BookingDTO bookingDTO);

    @PUT("booking/{id}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<BookingDTO> updateBooking(@Path("id") int bookingId,
                                   @Body BookingDTO bookingDTO);

    @DELETE("booking/{id}")
    Call<Void> deleteBooking(@Path("id") int bookingId);

}
