package Book_My_Show.Services;

import Book_My_Show.Entities.*;
import Book_My_Show.Exceptions.SeatUnavailableException;
import Book_My_Show.Repositories.*;
import Book_My_Show.RequestDTO.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        //1. Calculate total cost of tickets
        Movie movie = movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());

        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();

        //1.1 Find the ShowEntity with this Date and Time
        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getLocalDate(),
                                                                                   bookTicketRequest.getLocalTime(),
                                                                                   movie,
                                                                                   theater);

        Integer showId = show.getShowId();
        List<ShowSeat> showSeatList = showSeatRepository.findShowSeats(showId);

        //1.2 Calculate the total amount and if all seats are available or not
        int totalAmount = 0;
        Boolean areAllSeatsAvailable = Boolean.TRUE;

        for(String seatNo : bookTicketRequest.getRequestedSeats()){ // gives seatNo from List<String>RequestedSeats
            for(ShowSeat showSeat : showSeatList){
                if(showSeat.getSeatNo().equals(seatNo)){
                    if(showSeat.getIsAvailable() == Boolean.FALSE){
                        areAllSeatsAvailable = Boolean.FALSE;
                        break;
                    }
                    totalAmount = totalAmount + showSeat.getPrice();
                }
            }
        }

        if(areAllSeatsAvailable == Boolean.FALSE){
            throw new SeatUnavailableException("The requested seats are unavailable");
        }

        //2. Make the seats booked : (Only if seats are available : otherwise throw exception)

        for(String seatNo : bookTicketRequest.getRequestedSeats()){ // gives seatNo from List<String>RequestedSeats
            for(ShowSeat showSeat : showSeatList){
                if(showSeat.getSeatNo().equals(seatNo)){
                    showSeat.setIsAvailable(Boolean.FALSE);
                    }
                }
            }


        User user = userRepository.findUserByMobileNo(bookTicketRequest.getMobileNo());

        //3. Save the ticketEntity

        Ticket ticket = Ticket.builder().user(user)
                                        .movieName(bookTicketRequest
                                        .getMovieName())
                                        .localDate(bookTicketRequest.getLocalDate())
                                        .localTime(bookTicketRequest.getLocalTime())
                                        .theaterNameAndAddress(theater.getTheaterName() + " " + theater.getTheaterAddress())
                                        .totalAmountPaid(totalAmount)
                                        .build();

        ticket = ticketRepository.save(ticket);

        //Generate and return back the actual ticket response

        return ticket;











    }

}
