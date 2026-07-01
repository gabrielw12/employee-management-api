package com.example.employeemenagementapi.Controller;

import com.example.employeemenagementapi.Model.Employee;
import com.example.employeemenagementapi.Model.EmployeeCondition;
import com.example.employeemenagementapi.Service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldAddEmployee() throws Exception {
        Employee employee = new Employee("Jan", "Kowalski", EmployeeCondition.PRESENT, 1995, 4500.0);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.imie").value("Jan"))
                .andExpect(jsonPath("$.nazwisko").value("Kowalski"));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employee/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnCsvFile() throws Exception {
        String mockCsv = "ID,Imie,Nazwisko\n1,Jan,Kowalski\n";
        when(employeeService.generateCsv()).thenReturn(mockCsv);

        mockMvc.perform(get("/api/employee/csv"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"employees.csv\""))
                .andExpect(content().contentType("text/csv"))
                .andExpect(content().string(mockCsv));
    }
}