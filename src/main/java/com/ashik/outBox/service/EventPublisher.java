package com.ashik.outBox.service;

import com.ashik.outBox.entity.OutboxEvent;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    public void publish(OutboxEvent event) {
        System.out.println("Publishing event" + event);
    }
}
