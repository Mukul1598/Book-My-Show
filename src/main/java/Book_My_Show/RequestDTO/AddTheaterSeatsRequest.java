package Book_My_Show.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheaterSeatsRequest {

    private int theaterId;

    private int noOfPremiumSeats;

    private int noOfClassicSeats;

}
