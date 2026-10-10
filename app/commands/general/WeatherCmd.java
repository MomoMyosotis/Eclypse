// first line

package app.commands.general;
import app.commands.Command;
import app.helpers.events.InputReader;
import app.helpers.web.DuckingWhere;
import app.modules.general.Weather;

public class WeatherCmd implements Command {

    @Override
    public void execute(){

        System.out.print("\nCity name: ");
        String city = InputReader.WhatTs().toLowerCase().replace(" ", "%20");
        System.out.println("\n");
        double[] coords = DuckingWhere.geocoder(city);
        Weather.weather(coords);
    }
}

// last line