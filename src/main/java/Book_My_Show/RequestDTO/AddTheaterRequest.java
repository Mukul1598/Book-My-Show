package Book_My_Show.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheaterRequest {

    private String theaterName;

    private String theaterAddress;

    private Integer noOfScreens;
}
