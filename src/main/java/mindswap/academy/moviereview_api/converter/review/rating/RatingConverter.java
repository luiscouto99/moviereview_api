package mindswap.academy.moviereview_api.converter.review.rating;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingConverter implements IRatingConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public RatingConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper getMapper() {
        return this.modelMapper;
    }
}
