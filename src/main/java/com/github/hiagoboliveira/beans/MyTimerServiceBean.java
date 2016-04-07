package com.github.hiagoboliveira.beans;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Date;

@Singleton
@Startup
public class MyTimerServiceBean {

    @Schedule(hour = "*", minute = "*", second = "0, 30")
    public void doScheduledJob() {
        System.out.println("=========================");
        System.out.println("Doing my job! " + new Date());
    }
}
