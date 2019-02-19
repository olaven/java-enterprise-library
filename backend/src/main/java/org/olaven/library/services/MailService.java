package org.olaven.library.services;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Scheduled(cron = "0 0 9 * * ?")
    public void notifyCustomersAboutUpcomingDelivery() {
        // find customers that soon have to deliver
        // mail them a notice
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void notifyCustomersAboutFine() {
        // find customers that have due deliverence
        // mail them a notice
    }

    @Scheduled(cron = "* * * * * *")
    public void justPrintingHelloAllTheTime() {
        System.out.println("hello");
    }
}
