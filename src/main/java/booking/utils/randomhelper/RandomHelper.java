package booking.utils.randomhelper;

import booking.api.dtos.booking.BookingIdDTO;

import java.util.List;
import java.util.Random;

public class RandomHelper {

    public static Integer getRandomNumberBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Randomizer for getting random booking id.
     * @param bookingIdList List of current bookings' id.
     * @return int value of booking id.
     */
    public static int getRandomBookingId(List<BookingIdDTO> bookingIdList) {
        return bookingIdList.get(getRandomNumberBetween(0, bookingIdList.size())).getBookingId();
    }
}

