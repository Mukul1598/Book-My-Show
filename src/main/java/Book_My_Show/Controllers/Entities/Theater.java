package Book_My_Show.Controllers.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theaters")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterID;

    private String theaterName;

    private String theaterAddress;

    private Integer noOfScreens;

}
