package commands.find.weather.model;

import commands.find.weather.annotations.Mapped;
import commands.find.weather.annotations.Translate;
import commands.find.weather.annotations.SpecialEnd;

public class WeatherModel {
    @Translate("Город")
    @Mapped("name")
    private String city;

    @Translate("Температура")
    @Mapped("temp")
    @SpecialEnd("C")
    private double temperature;

    @Translate("Влажность")
    @Mapped("humidity")
    @SpecialEnd("%")
    private double humidity;

    @Translate("Описание")
    @Mapped("description")
    private String description;
}
