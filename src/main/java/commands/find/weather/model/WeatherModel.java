package commands.find.weather.model;

import commands.find.weather.annotations.Mapped;
import commands.find.weather.annotations.SpecialEnd;

public class WeatherModel {
    @Mapped("name")
    private String city;

    @Mapped("temp")
    @SpecialEnd("C")
    private double temperature;

    @Mapped("humidity")
    @SpecialEnd("%")
    private double humidity;

    @Mapped("description")
    private String description;
}
