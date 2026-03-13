package booking.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookinController {

    private final List<Booking> db = new ArrayList<>();

    @GetMapping
    public List<Booking> getAllBookings() {
        return db;
    }
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking newBooking) {

        if (newBooking.getRoomNumber() < 1 || newBooking.getRoomNumber() > 9) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erreur : Les numéros de chambres sont compris entre 1 à 9 uniquement.");
        }

        boolean isAlreadyBooked = db.stream().anyMatch(b ->
                b.getRoomNumber() == newBooking.getRoomNumber() &&
                        b.getDate().equals(newBooking.getDate())
        );

        if (isAlreadyBooked) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("La chambre est déjà réservée à cette date.");
        }


        db.add(newBooking);
        return ResponseEntity.ok(db);
    }
}
