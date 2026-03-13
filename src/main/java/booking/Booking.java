package booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Booking {
    private int roomNumber;
    private String guestName;
    private LocalDate date;
}