package com.example.employeemenagementapi.Service;

import com.example.employeemenagementapi.Model.ClassEmployee;
import com.example.employeemenagementapi.Model.Employee;
import com.example.employeemenagementapi.Repository.EmployeeRepository;
import com.example.employeemenagementapi.Repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final GroupRepository groupRepository;

    public EmployeeService(EmployeeRepository employeeRepository, GroupRepository groupRepository) {
        this.employeeRepository = employeeRepository;
        this.groupRepository = groupRepository;
    }

    public Employee addEmployee(Employee employee) {
        if (employee.getGrupa() != null && employee.getGrupa().getId() != null) {
            ClassEmployee group = groupRepository.findById(employee.getGrupa().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Grupa o podanym ID nie istnieje."));

            if (group.getEmployees().size() >= group.getRozmiar()) {
                throw new IllegalArgumentException("Nie można dodać pracownika. Grupa '" + group.getClassName() + "' jest pełna!");
            }
            employee.setGrupa(group);
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Pracownik o ID " + id + " nie istnieje.");
        }
        employeeRepository.deleteById(id);
    }

    public String generateCsv() {
        List<Employee> employees = employeeRepository.findAll();
        StringBuilder csv = new StringBuilder("ID,Imie,Nazwisko,Stan,Wynagrodzenie,Rok_Urodzenia,Grupa_ID\n");

        for (Employee e : employees) {
            csv.append(e.getId()).append(",")
                    .append(e.getImie()).append(",")
                    .append(e.getNazwisko()).append(",")
                    .append(e.getCondition()).append(",")
                    .append(e.getWynagrodzenie()).append(",")
                    .append(e.getRok_urodzenia()).append(",")
                    .append(e.getGrupa() != null ? e.getGrupa().getId() : "Brak").append("\n");
        }
        return csv.toString();
    }
}