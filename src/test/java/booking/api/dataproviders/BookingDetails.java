package test.java.booking.api.dataproviders;

import main.java.booking.api.dtos.booking.BookingDTO;
import main.java.booking.api.dtos.booking.BookingDatesDTO;
import org.testng.annotations.DataProvider;

public class BookingDetails {
    @DataProvider(name = "bookingDetailProvider")
    static Object[][] bookingDetail() {
        Object[][] bookingArray = {
            {new BookingDTO("Sercan", "Haydaroglu", 545, true, new BookingDatesDTO("2017-10-11", "2017-10-23"), "None")},
            {new BookingDTO("Tester", "Quality", 1250, false, new BookingDatesDTO("2019-10-11", "2019-10-23"), "Requirements")}
        };
        return bookingArray;
    }
}
