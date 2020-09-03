package test.java.booking.api.tests;

import lombok.extern.slf4j.Slf4j;
import main.java.booking.api.dtos.booking.BookingDTO;
import main.java.booking.api.dtos.booking.BookingDatesDTO;
import main.java.booking.api.dtos.booking.BookingExtendedDTO;
import main.java.booking.api.services.BookingService;
import main.java.booking.utils.randomhelper.RandomHelper;
import org.testng.annotations.Test;
import test.java.booking.api.BaseTest;
import test.java.booking.api.dataproviders.BookingDetails;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Slf4j
public class BookingApiTest extends BaseTest {

    @Test(dataProvider = "bookingDetailProvider", dataProviderClass = BookingDetails.class)
    public void createNewBookingTest(BookingDTO bookingDTO) {
        BookingService bookingService = new BookingService(retrofit.create(booking.api.clients.BookingApi.class));
        BookingExtendedDTO bookingResponse = bookingService.createBooking(bookingDTO);

        log.info("Comparing the response with the sent request.");
        assertNotNull(bookingResponse.getBookingId());
        assertEquals(bookingResponse.getBookingDetails().getFirstName(),
                bookingDTO.getFirstName(),
                "First name of booking does not match!");
        assertEquals(bookingResponse.getBookingDetails().getLastName(),
                bookingDTO.getLastName(),
                "Last name of booking does not match!");
        assertEquals(bookingResponse.getBookingDetails().getBookingDates(),
                bookingDTO.getBookingDates(),
                "Booking dates does not match!");
        assertEquals(bookingResponse.getBookingDetails().getTotalPrice(),
                bookingDTO.getTotalPrice(),
                "Total price of booking does not match!");
        assertEquals(bookingResponse.getBookingDetails().isDepositPaid(),
                bookingDTO.isDepositPaid(),
                "Deposit payment info does not match!");
        assertEquals(bookingResponse.getBookingDetails().getAdditionalNeeds(),
                bookingDTO.getAdditionalNeeds(),
                "Additional needs does not match!");
    }

    @Test(dataProvider = "bookingDetailProvider", dataProviderClass = BookingDetails.class)
    public void amendmentsOfCreatedBookingTest(BookingDTO bookingDTO) {
        BookingService bookingService = new BookingService(retrofit.create(booking.api.clients.BookingApi.class));
        int randomBookingElementId = RandomHelper.getRandomBookingId(bookingService.getBookingsIdList());
        BookingExtendedDTO extendedDTO = new BookingExtendedDTO(bookingDTO);
        extendedDTO.setBookingId(randomBookingElementId);
        BookingDTO responseBookingDto = bookingService.updateBooking(extendedDTO);

        log.info("Comparing the response with the sent request.");
        assertEquals(responseBookingDto.getFirstName(),
                bookingDTO.getFirstName(),
                "First name of booking does not match!");
        assertEquals(responseBookingDto.getLastName(),
                bookingDTO.getLastName(),
                "Last name of booking does not match!");
        assertEquals(responseBookingDto.getBookingDates(),
                bookingDTO.getBookingDates(),
                "Booking dates does not match!");
        assertEquals(responseBookingDto.getTotalPrice(),
                bookingDTO.getTotalPrice(),
                "Total price of booking does not match!");
        assertEquals(responseBookingDto.isDepositPaid(),
                bookingDTO.isDepositPaid(),
                "Deposit payment info does not match!");
        assertEquals(responseBookingDto.getAdditionalNeeds(),
                bookingDTO.getAdditionalNeeds(),
                "Additional needs does not match!");
    }

    @Test()
    public void getBookingTest() {
        BookingService bookingService = new BookingService(retrofit.create(booking.api.clients.BookingApi.class));
        BookingDTO bookingToCreate = new BookingDTO("Quality",
                "Assurance", 250, false,
                new BookingDatesDTO("2019-11-23", "2019-12-15"),
                "Clear requirements :)");

        BookingExtendedDTO createdBooking = bookingService.createBooking(bookingToCreate);
        BookingDTO getBookingDetail = bookingService.getBookingDetail(createdBooking.getBookingId());

        log.info("Comparing the get response with the sent request.");
        assertEquals(createdBooking.getBookingDetails().getFirstName(),
                getBookingDetail.getFirstName(),
                "First name of booking does not match!");
        assertEquals(createdBooking.getBookingDetails().getLastName(),
                getBookingDetail.getLastName(),
                "Last name of booking does not match!");
        assertEquals(createdBooking.getBookingDetails().getBookingDates(),
                getBookingDetail.getBookingDates(),
                "Booking dates does not match!");
        assertEquals(createdBooking.getBookingDetails().getTotalPrice(),
                getBookingDetail.getTotalPrice(),
                "Total price of booking does not match!");
        assertEquals(createdBooking.getBookingDetails().isDepositPaid(),
                getBookingDetail.isDepositPaid(),
                "Deposit payment info does not match!");
        assertEquals(createdBooking.getBookingDetails().getAdditionalNeeds(),
                getBookingDetail.getAdditionalNeeds(),
                "Additional needs does not match!");
    }

    @Test()
    public void deletionOfBookingTest() {
        BookingService bookingService = new BookingService(retrofit.create(booking.api.clients.BookingApi.class));
        int randomBookingElementId = RandomHelper.getRandomBookingId(bookingService.getBookingsIdList());
        assert bookingService.deleteBooking(randomBookingElementId);

        assertEquals(bookingService.getResponseCodeOfBookingGetRequest(randomBookingElementId),
                404, "Booking removal is not successful!");
    }
}
