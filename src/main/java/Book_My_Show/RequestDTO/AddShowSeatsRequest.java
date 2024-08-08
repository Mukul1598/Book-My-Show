package Book_My_Show.RequestDTO;

import lombok.Data;

@Data
public class AddShowSeatsRequest {

    private Integer showSeatId;

    private Integer priceOfClassicSeats;

    private Integer priceOfPremiumSeats;

}
