package commands.find;

import annotation.Active;
import answer.UserRequest;
import commands.IncorrectCommandReason;
import commands.find.weather.builder.AnswerBuilder;
import commands.find.weather.mapper.ModelMapper;
import commands.find.weather.model.WeatherModel;
import commands.find.weather.url.UrlForming;
import java.net.URL;


@Active
public class FindCommandImpl implements FindCommand {

	@Override
    public String formFindAnswer(UserRequest request){
	    try{
            String city = request.getValue();

            UrlForming urlForming = new UrlForming();
            URL url = urlForming.formUrl(city);

            ModelMapper modelMapper = ModelMapper.getInstance();
            WeatherModel weatherModel = modelMapper.mapModel(WeatherModel.class, url);

            AnswerBuilder messageBuilder = new AnswerBuilder();
            String answer = messageBuilder.buildAnswer(weatherModel);

            return answer;

        } catch (RuntimeException exception){
	        return IncorrectCommandReason.INCORRECT_CITY.getReason();
        }
    }
}