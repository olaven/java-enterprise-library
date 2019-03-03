package org.olaven.library.backend.services;

import org.olaven.library.backend.entities.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Autowired
    BorrowingService borrowingService;
    @Autowired
    BookService bookService;

    @Async
    @Scheduled(cron = "0 0 9 * * ?")
    public void notifyCustomersWeekBefore() {
        // find customers that soon have to deliver
        // mail them a notice


    }

    @Async
    @Scheduled(cron = "0 0 9 * * ?")
    public void notifyCustomersDayBeforeDelivery() {

    }

    @Async
    @Scheduled(cron = "0 0 9 * * ?")
    public void notifyCustomersAboutFine() {
        // find customers that have due deliverence
        // mail them a notice

        List<Record> records = borrowingService.getRecordsPastDelivery();
        for(Record record: records) {
            String email = record.getCustomer().getEmail();
            String title = record.getBook().getTitle();
            String borrowingDate = record.getDate().toString();
        }
    }

}
