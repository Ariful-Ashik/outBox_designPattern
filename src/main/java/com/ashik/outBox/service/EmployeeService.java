package com.ashik.outBox.service;

import com.ashik.outBox.entity.Employee;
import com.ashik.outBox.entity.OutboxEvent;
import com.ashik.outBox.repository.EmployeeRepository;
import com.ashik.outBox.repository.OutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        // Save event asynchronously
        new Thread(() -> saveOutboxEvent(savedEmployee)).start();

        return savedEmployee;
    }

    private void saveOutboxEvent(Employee savedEmployee) {
        try {
            String payload = objectMapper.writeValueAsString(savedEmployee);
            OutboxEvent event = new OutboxEvent(null, "EMPLOYEE_CREATED", payload, null);
            outboxRepository.save(event);

            // 🔥 Log event creation
            log.info("Outbox event created: ID = {}, Type = {}, Payload = {}", event.getId(), event.getEventType(), event.getPayload());
        } catch (Exception e) {
            log.error(e.getMessage());
            System.err.println("Error saving outbox event: " + e.getMessage());
        }
    }

    public ResponseEntity<List<OutboxEvent>> findAll() {
        List<OutboxEvent> outboxEvents = outboxRepository.findAll();
        return ResponseEntity.ok(outboxEvents);
    }

    public List<OutboxEvent> findAlls() {
        return outboxRepository.findAll();
    }
}


//@Service
/// /@RequiredArgsConstructor
//public class EmployeeService {
//    private final EmployeeRepository employeeRepository;
//    private final OutboxRepository outboxRepository;
//    private final ObjectMapper objectMapper; // This is still null
//
//    @Autowired
//    public EmployeeService(EmployeeRepository employeeRepository, OutboxRepository outboxRepository, ObjectMapper objectMapper) {
//        this.employeeRepository = employeeRepository;
//        this.outboxRepository = outboxRepository;
//        this.objectMapper = objectMapper;
//    }
//
//    @Transactional
//    public Employee createEmployee(Employee employee) {
//        Employee savedEmployee = employeeRepository.save(employee);
//        try {
//            String payload = objectMapper.writeValueAsString(savedEmployee);
//            OutboxEvent event = new OutboxEvent(null, "EMPLOYEE_CREATED", payload, null);
//            outboxRepository.save(event);

//        } catch (Exception e) {
//            throw new RuntimeException("Error saving outbox event", e);
//        }
//        return savedEmployee;
//    }
//}

