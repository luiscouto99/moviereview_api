package mindswap.academy.moviereview_api.dataloader.movieloader;

import lombok.*;

import java.util.List;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieList {
    private List<MovieId> items;
}
