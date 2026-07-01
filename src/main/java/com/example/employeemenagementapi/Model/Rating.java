package com.example.employeemenagementapi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Ocena musi wynosić co najmniej 1")
    @Max(value = 5, message = "Ocena nie może być wyższa niż 5")
    private int ocena;

    @ManyToOne
    @JoinColumn(name = "grupa_id")
    private ClassEmployee grupa;

    public Rating() {}

    public Rating(int ocena, ClassEmployee grupa) {
        this.ocena = ocena;
        this.grupa = grupa;
    }

    public Long getId() { return id; }
    public int getOcena() { return ocena; }
    public void setOcena(int ocena) { this.ocena = ocena; }
    public ClassEmployee getGrupa() { return grupa; }
    public void setGrupa(ClassEmployee grupa) { this.grupa = grupa; }
}