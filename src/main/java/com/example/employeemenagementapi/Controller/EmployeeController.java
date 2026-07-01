package com.example.employeemenagementapi.Controller;

import com.example.employeemenagementapi.Model.Employee;
import com.example.employeemenagementapi.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping // POST: /api/employee [cite: 5]
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
    }

    @DeleteMapping("/{id}") // DELETE: /api/employee/:id [cite: 6]
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/csv", produces = "text/csv") // GET: /api/employee/csv [cite: 7]
    public ResponseEntity<String> getEmployeesCsv() {
        String csvData = employeeService.generateCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"employees.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvData);
    }
}