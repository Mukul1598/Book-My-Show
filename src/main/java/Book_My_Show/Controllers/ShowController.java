package Book_My_Show.Controllers;

import Book_My_Show.RequestDTO.AddShowRequest;
import Book_My_Show.RequestDTO.AddShowSeatsRequest;
import Book_My_Show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public String addShow(@RequestBody AddShowRequest showRequest) {
        String result = showService.addShow(showRequest);
        return result;
    }

    @PostMapping("/addShowSeats")
    public String addShowSeats(@RequestBody AddShowSeatsRequest showSeatsRequest){
        String result = showService.addShowSeats(showSeatsRequest);
        return result;
    }


}
