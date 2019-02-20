package org.olaven.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    BookService bookService;

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

}
