package Book_My_Show.RequestDTO;

import Book_My_Show.Entities.Movie;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest {

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private Integer theaterId;
}
