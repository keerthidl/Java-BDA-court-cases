package com.personal.persistence.repos;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    private NotificationRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        Notification notification = new Notification();
        notification.setAppId("BDA2");
        notification.setChannel("EMAIL");
        notification.setDtRequested(new Date(10,10,1972));
        notification.setSentDate(new Date(10,10,1972));
        notification.setLastUpdated(new Date(10,10,1972));
        notification.setNotificationId("22222");
        notification.setNotificationType("Land Dispute");
        notification.setStatus("CLOSED");
       
        
        repository.save(notification);
        System.out.println(" SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS ");
       // repository.save(notification);
        

        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById ==================================(1L)");
        repository.findById(new Long(1)).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Node')");
       // repository.findByName("Node").forEach(x -> System.out.println(x));

    }

}
