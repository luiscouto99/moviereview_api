package mindswap.academy.moviereview_api.command.movie;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDto {
    private String title;
    private String fullTitle;
    private String image;
    private String contentRating;
}
