package Book_My_Show.RequestDTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketRequest {

    private String movieName;
    private LocalDate localDate;
    private LocalTime localTime;
    private List<String> requestedSeats;
    private Integer theaterId;
    private String mobileNo;

}
