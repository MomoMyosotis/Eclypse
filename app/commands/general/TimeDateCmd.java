// first line

package app.commands.general;
import app.commands.Command;
import app.helpers.events.InputReader;

import java.util.Scanner;

import app.modules.general.TimeDate;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class TimeDateCmd implements Command {

    @Override
    public void execute(Scanner miao){
        System.out.print("\nlocal?\nchoce: ");
        String choice = InputReader.WhatTs(miao).toLowerCase();
        ZonedDateTime zona = ZonedDateTime.now(ZoneId.systemDefault());
        String city;
        if (InputReader.yes_no(choice)){
            city = zona.getZone().toString().replace(" ", "%20");
            String time = zona.toLocalTime().toString();
            String date = zona.toLocalDate().toString();
            System.out.println("Fuso orario: " + city + " - time: " + time + " - date: " + date);
        } else{
            System.out.print("\ntarget city name: ");
            city = InputReader.WhatTs(miao).toLowerCase().replace(" ", "%20");
            TimeDate.timedate(miao, city);
        }
    }
}

// last line