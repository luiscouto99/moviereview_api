package mindswap.academy.moviereview_api.command.review.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Pattern(regexp="(?i)(?:1)?|(?i)(?:2)?|(?i)(?:3)?|(?i)(?:4)?|(?i)(?:5)?",message = "Rate should be a string of a number between 1-5!")
    private String rate;
}
