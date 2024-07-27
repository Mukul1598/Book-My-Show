package Book_My_Show.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMovieAttributes {

    private Integer movieId;

    private double movieRating;

    private double movieDuration;

}
