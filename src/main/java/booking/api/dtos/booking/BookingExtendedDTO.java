package main.java.booking.api.dtos.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BookingExtendedDTO extends BookingIdDTO{
    @JsonProperty("booking")
    @NonNull
    private BookingDTO bookingDetails;
}
