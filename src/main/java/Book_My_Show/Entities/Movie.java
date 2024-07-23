package Book_My_Show.Entities;

import Book_My_Show.Enums.Genre;
import Book_My_Show.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  automatically generates unique movieIDs
    private Integer movieId;

    private String movieName;

    private double movieDuration;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    private String movieRating; // U, U/A, A, R

    private LocalDate releaseDate;
}
