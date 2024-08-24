package Book_My_Show.Services;

import Book_My_Show.Entities.*;
import Book_My_Show.Enums.SeatType;
import Book_My_Show.Repositories.MovieRepository;
import Book_My_Show.Repositories.ShowRepository;
import Book_My_Show.Repositories.ShowSeatRepository;
import Book_My_Show.Repositories.TheaterRepository;
import Book_My_Show.RequestDTO.AddShowRequest;
import Book_My_Show.RequestDTO.AddShowSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShow(AddShowRequest showRequest){

        //Get the Movie Entity and Theater Entity to create the Show entity

//        Movie movie = movieRepository.findMovieByMovieName(showRequest.getMovieName());
        Movie movie = movieRepository.findMovieByMovieName(showRequest.getMovieName());

        Theater theater = theaterRepository.findById(showRequest.getTheaterId()).get();

        //Build object of Show entity and save it in Database
        Show show = Show.builder().showDate(showRequest.getShowDate())
                                  .showTime(showRequest.getShowTime())
                                  .movie(movie)
                                  .theater(theater)
                                  .build();

        show = showRepository.save(show);

        return "The show has been saved to the Database with showId : " + show;
    }

    public String addShowSeats(AddShowSeatsRequest showSeatsRequest){

        Integer showSeatId = showSeatsRequest.getShowSeatId(); // get the showSeatId
        Show show = showRepository.findById(showSeatId).get(); // find with that showSeatId

        Theater theater = show.getTheater(); // find the theater of that show
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList(); // find seat list of that theater


        // Generate show seats list
        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = ShowSeat.builder().seatNo(theaterSeat
                                                .getSeatNo())
                                                .seatType(theaterSeat.getSeatType())
                                                .isAvailable(Boolean.TRUE)
                                                .show(show)
                                                .build();

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatsRequest.getPriceOfClassicSeats());
            } else {
                showSeat.setPrice(showSeatsRequest.getPriceOfPremiumSeats());
            }

            showSeatList.add(showSeat);

        }

        showSeatRepository.saveAll(showSeatList);

        return "Show seats have been generated for the given showId";
    }

}
