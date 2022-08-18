package com.ayorinde.dronetechjob.eventlog;

import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask extends TimerTask {
    Date now;

    public void run()
    {
        try {
            now = new Date();
            System.out.println("Time is :" + now + ". ");
            DroneClient dr = new DroneClient();
            dr.getEventLog();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

