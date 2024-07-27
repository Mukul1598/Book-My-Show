package Book_My_Show.Controllers.Services;

import Book_My_Show.Controllers.Entities.Theater;
import Book_My_Show.Controllers.Entities.TheaterSeat;
import Book_My_Show.Controllers.Enums.SeatType;
import Book_My_Show.Controllers.Repositories.TheaterRepository;
import Book_My_Show.Controllers.Repositories.TheaterSeatRepository;
import Book_My_Show.Controllers.RequestDTO.AddTheaterRequest;
import Book_My_Show.Controllers.RequestDTO.AddTheaterSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){
        // Convert this AddRequest to an Entity
        //How to do this???

        // Use constructors to create an object : generally not available

        // Creating object the usual way
//        Theater theater = new Theater();
//        theater.setTheaterName(addTheaterRequest.getTheaterName());
//        theater.setTheaterAddress(addTheaterRequest.getTheaterAddress());
//        theater.setNoOfScreens(addTheaterRequest.getNoOfScreens());


        //Modern way of creating objects
        Theater theater = Theater.builder().theaterName(addTheaterRequest.getTheaterName())
                                           .theaterAddress(addTheaterRequest.getTheaterAddress())
                                           .noOfScreens(addTheaterRequest.getNoOfScreens())
                                           .build();


        // Save entity to database

        theater = theaterRepository.save(theater);
        return "The theater is with theaterID : " + theater.getTheaterID();

    }

    public String addTheaterSeats(AddTheaterSeatsRequest addTheaterSeatsRequest){

        int noOfPremiumSeats = addTheaterSeatsRequest.getNoOfPremiumSeats();
        int noOfClassicSeats = addTheaterSeatsRequest.getNoOfClassicSeats();

        Integer theaterId = addTheaterSeatsRequest.getTheaterId();
        Theater theater = theaterRepository.findById(theaterId).get();

//        Integer theaterId = addTheaterSeatsRequest.getTheaterId();
//        Optional<Theater> optionalTheater = theaterRepository.findById(theaterId);
//        Theater theater;
//
//        if (optionalTheater.isPresent()) {
//            theater = optionalTheater.get();
//        } else {
//            throw new TheaterNotFoundException("Theater with ID " + theaterId + " not found");
//        }


        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        int premiumSeatCounter = 0;
        char premium_seat_Row = 'A'; // A, B, C, ......etc, where each row(A, B, ...) contains seats
        int premium_rowSeatNo = 1; // 1, 2, 3, .......etc, where 1, 2, 3,... is seat in each row

        while(premiumSeatCounter < noOfPremiumSeats){

            String seatNo =  premium_rowSeatNo + "" + premium_seat_Row; // 1A, 2A, 3A,... 1B, 2B, 3C... etc

            TheaterSeat theaterSeat = TheaterSeat.builder()
                                                 .theater(theater) // theater id
                                                 .seatNo(seatNo)
                                                 .seatType(SeatType.PREMIUM)
                                                 .build();

            theaterSeatList.add(theaterSeat);

            premium_rowSeatNo++;  // incrementing the seat by 1

            if(premiumSeatCounter % 5 == 4){ // after 5 seats in a row, start in new row
                premium_seat_Row++;  // incrementing the character by 1
                premium_rowSeatNo = 1;  // restart the seats from 1 for each new row
            }
            premiumSeatCounter++;
        }


        int classicSeatCounter = 0;
        char classic_seat_Row;

        if(premiumSeatCounter == 0){
            classic_seat_Row = 'A'; // A, B, C, ......etc, where each row(A, B, ...) contains seats
        } else if(premiumSeatCounter % 5 == 0){
            classic_seat_Row = premium_seat_Row;
        } else {
            classic_seat_Row = (char)(premium_seat_Row + 1);
        }

        int classic_rowSeatNo = 1; // 1 , 2, 3, .......etc, where 1, 2, 3,... is seat in each row

        while(classicSeatCounter < noOfClassicSeats){

            String seatNo =  classic_rowSeatNo + "" + classic_seat_Row; // 1 A, 2 A, 3 A,... 1 B, 2 B, 3 C... etc

            TheaterSeat theaterSeat = TheaterSeat.builder()                     //
                                                 .theater(theater)
                                                 .seatNo(seatNo)
                                                 .seatType(SeatType.CLASSIC)
                                                 .build();

            theaterSeatList.add(theaterSeat);

            classic_rowSeatNo++;  // incrementing the seat by 1

            if(classicSeatCounter % 5 == 4){ // after 5 seats in a row, start in new row
                classic_seat_Row++;  // incrementing the character by 1
                classic_rowSeatNo = 1;  // restart the seats from 1 for each new row
            }
            classicSeatCounter++;
        }

        theaterSeatRepository.saveAll(theaterSeatList);

        return "Theater seats have been generated";
    }

}
