package com.example.employeemenagementapi.Model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*; // Zapewnia adnotacje bazy danych
import jakarta.validation.constraints.*;



@Entity
public class Employee implements Comparable<Employee>
{

    @Id // 2. Mówi Springowi: "To pole to klucz główny (Primary Key)"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Baza sama będzie nadawać numery 1, 2, 3...
    private Long id;
    @NotBlank(message = "Imię nie może być puste!")
    private String imie;
    @NotBlank(message = "Nazwisko nie może być puste!")
    private String nazwisko;
    @NotNull(message = "Stan musi być podany!")
    @Enumerated(EnumType.STRING)
    private EmployeeCondition condition;
    @Min(value = 1900, message = "Rok urodzenia musi być sensowny!")
    private int rok_urodzenia;
    @PositiveOrZero(message = "Wynagrodzenie nie może być ujemne!")
    private double wynagrodzenie;

    @ManyToOne
    @JoinColumn(name = "grupa_id") // Tak będzie nazywać się kolumna łącząca w bazie
    @JsonIgnoreProperties("employees")
    private ClassEmployee grupa;

    public void printing()
    {
        System.out.println("Imie: " + imie  + "\nNazwisko: " + nazwisko + "\nStan" + condition + "\nwynagrodzenie: " + wynagrodzenie+ "\nRok urodzenia: " + rok_urodzenia);
    }

    public Employee(String imie, String nazwisko, EmployeeCondition condition, int rok_urodzenia, double wynagrodzenie)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.condition = condition;
        this.rok_urodzenia = rok_urodzenia;
        this.wynagrodzenie = wynagrodzenie;
    }

    public Employee(){};

    public double getWynagrodzenie()
    {
        return wynagrodzenie;
    }
    @Override
    public int compareTo(Employee o) {
        return nazwisko.compareTo(o.nazwisko);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(imie, nazwisko);
    }
    @Override
    public boolean equals(Object e)
    {
        if (this == e)
            return true;
        if (e==null || this.getClass()!=e.getClass() )
            return false;
        Employee ee = (Employee)e;
        return imie.equals(ee.imie) && nazwisko.equals(ee.nazwisko);
    }
    public void addSalary(double salary)
    {
        wynagrodzenie += salary;
    }
    public void setCondition(EmployeeCondition condition)
    {
        this.condition = condition;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public EmployeeCondition getCondition() {
        return condition;
    }

    public int getRok_urodzenia() {
        return rok_urodzenia;
    }

    public void setRok_urodzenia(int rok_urodzenia) {
        this.rok_urodzenia = rok_urodzenia;
    }

    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public ClassEmployee getGrupa() {
        return grupa;
    }

    public void setGrupa(ClassEmployee grupa) {
        this.grupa = grupa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
