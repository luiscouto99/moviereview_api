package mindswap.academy.moviereview_api.command.review.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import mindswap.academy.moviereview_api.persistence.model.review.Review;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 5, message = "Rate has to be between 1 and 5")
    private String rate;
}
