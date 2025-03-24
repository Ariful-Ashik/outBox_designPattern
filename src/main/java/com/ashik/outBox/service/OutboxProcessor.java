//package com.ashik.outBox.service;
//
//import com.ashik.outBox.entity.OutboxEvent;
//import com.ashik.outBox.repository.OutboxRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class OutboxProcessor {
//
//    @Autowired
//    private OutboxRepository outboxRepository;
//    @Autowired
//    private EventPublisher eventPublisher;
//
//    @Scheduled(fixedRate = 5000)
//    public void process() {
//        List<OutboxEvent> outboxEvents = outboxRepository.findAll();
//        for (OutboxEvent outboxEvent : outboxEvents) {
//            eventPublisher.publish(outboxEvent);
//            outboxRepository.delete(outboxEvent);
//        }
//    }
//}
