package booking.api.services;

import booking.api.clients.BookingApi;
import booking.api.dtos.booking.BookingDTO;
import booking.api.dtos.booking.BookingIdDTO;
import booking.api.dtos.booking.BookingExtendedDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

import java.util.List;

@Slf4j
public class BookingService {

    private BookingApi bookingApi;

    public BookingService(BookingApi bookingApi) {
        this.bookingApi = bookingApi;
    }

    private void logResponseCode(Response response) {
        if (response.code() == 200 || response.code() == 201)
            log.info("Api request is successful with code of: HTTP {}", response.code());
        else
            log.error("Request have failed with error code of: HTTP {}.", response.code());
    }

    public List<BookingIdDTO> getBookingsIdList() {
        return getBookingsIdList(null, null, null, null);
    }

    @SneakyThrows
    public List<BookingIdDTO> getBookingsIdList(String firstName, String lastName, String checkInDate, String checkOutDate) {
        log.info("Getting available booking list from service");
        Response<List<BookingIdDTO>> response = bookingApi.getBookingIds(firstName, lastName, checkInDate, checkOutDate).execute();
        logResponseCode(response);
        return response.body();
    }

    public BookingDTO getBookingDetail(int bookingId) {
        BookingIdDTO bookingIdDTO = new BookingIdDTO(bookingId);
        return getBookingDetail(bookingIdDTO);
    }

    @SneakyThrows
    public BookingDTO getBookingDetail(BookingIdDTO bookingIdDTO) {
        log.info("Getting booking detail for the booking with id of {}.", bookingIdDTO.getBookingId());
        Response<BookingDTO> response = bookingApi.getBooking(bookingIdDTO.getBookingId()).execute();
        logResponseCode(response);
        return response.body();
    }

    @SneakyThrows
    public BookingExtendedDTO createBooking(BookingDTO bookingDTO) {
        log.info("Setting a booking for the name of {} {}.", bookingDTO.getFirstName(), bookingDTO.getLastName());
        Response<BookingExtendedDTO> response = bookingApi.createBooking(bookingDTO).execute();
        logResponseCode(response);
        if(response.code() == 200)
            log.info("Booking has been created.");
        return response.body();
    }

    @SneakyThrows
    public BookingDTO updateBooking(BookingExtendedDTO bookingExtendedDTO) {
        BookingDTO bookingDTO = bookingExtendedDTO.getBookingDetails();
        int bookingId = bookingExtendedDTO.getBookingId();
        log.info("Updating booking for booking id of {}", bookingId);
        Response<BookingDTO> response = bookingApi.updateBooking(bookingId, bookingDTO).execute();
        logResponseCode(response);
        return response.body();
    }

    @SneakyThrows
    public boolean deleteBooking(int bookingId) {
        log.info("Removing the booking with booking id of {}", bookingId);
        return bookingApi.deleteBooking(bookingId).execute().code() == 201;
    }

    @SneakyThrows
    public int getResponseCodeOfBookingGetRequest(int bookingId) {
        return bookingApi.getBooking(bookingId).execute().code();
    }

}
