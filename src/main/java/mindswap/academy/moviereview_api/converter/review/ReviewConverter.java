package mindswap.academy.moviereview_api.converter.review;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter implements IReviewConverter{

    private ModelMapper modelMapper;

    @Autowired
    public ReviewConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper getMapper() {
        return this.modelMapper;
    }
}
