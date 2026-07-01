package com.example.employeemenagementapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class ClassEmployee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "grupa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("grupa")
    private List<Employee> employees= new ArrayList<>();
    @Positive(message = "Rozmiar grupy musi być dodatni!")
    private int rozmiar;
    @NotBlank(message = "Nazwa grupy nie może być pusta!")
    private String className;



    public int getRozmiar() {
        return rozmiar;
    }
    public String getClassName() { return className; }

    public ClassEmployee(List<Employee> employees, int rozmiar)
    {
        this.employees = employees;
        this.rozmiar = rozmiar;
    }

    public ClassEmployee(String className, int rozmiar) {
        this.rozmiar = rozmiar;
        this.className = className;
    }

    public ClassEmployee() {}



    public void addEmployee(Employee employee)
    {
        if(employees.contains(employee))
        {
            System.out.println("juz taki pracownik jest dodany\n");
            return;
        }
        if(employees.size() >= rozmiar)
        {
            System.out.println("grupa jest pelna\n");
            return;
        }
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addSalary(Employee employee, double amount)
    {
        int index = employees.indexOf(employee);
        Employee employeeToAdd = employees.get(index);
        employeeToAdd.addSalary(amount);
    }

    public void removeEmployee(Employee employee)
    {
        int index = employees.indexOf(employee);
        Employee employeeToRemove = employees.get(index);
        employees.remove(employeeToRemove);
    }

    public void changeCondition(Employee employee, EmployeeCondition condition)
    {
        int index = employees.indexOf(employee);
        Employee employeeToChange = employees.get(index);
        employeeToChange.setCondition(condition);
    }
    public List<Employee> search(String lastName)
    {
        List<Employee> searchedEmployees = new ArrayList<>();
        for (Employee e : employees)
        {
            if(e.getNazwisko().equals(lastName))
                searchedEmployees.add(e);
        }
        return searchedEmployees;
    }
    public List<Employee> searchPartial(String dynamicString)
    {
        List<Employee> searchedEmployees = new ArrayList<>();
        String szukanaCzesc = dynamicString.toLowerCase();
        for (Employee e : employees)
        {
            String przeszukiwanyString = e.getNazwisko().toLowerCase();
            if(przeszukiwanyString.contains(szukanaCzesc))
                searchedEmployees.add(e);
        }
        return searchedEmployees;
    }
    public int countByCondition(EmployeeCondition condition)
    {
        List<Employee> searchedEmployees = new ArrayList<>();
        for (Employee e : employees)
        {
            if(e.getCondition().equals(condition))
                searchedEmployees.add(e);
        }
        return searchedEmployees.size();
    }
    public void summary()
    {
        for (Employee e : employees)
        {
            System.out.println(" --  ---  -- ");
            e.printing();
            System.out.println("   --   --   \n");
        }
    }
    public List<Employee> sortByName()
    {
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort((emp1, emp2) ->
        {
            return emp1.getImie().compareTo(emp2.getImie());
        });
        return sortedEmployees;
    }
    public List<Employee> sortBySalary()
    {
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort((emp1, emp2) ->
        {
            return emp1.getImie().compareTo(emp2.getImie());
        });
        return sortedEmployees;
    }
    public Employee max()
    {
        return Collections.max(employees);
    }

    public double getZapelnienie()
    {
        if (rozmiar == 0) {
            return 0.0;
        }
        return ((double) employees.size() / rozmiar) * 100.0;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setRozmiar(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
