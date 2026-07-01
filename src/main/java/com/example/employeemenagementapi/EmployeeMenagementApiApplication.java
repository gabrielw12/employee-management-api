package com.example.employeemenagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeMenagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeMenagementApiApplication.class, args);
    }

}


/*



1. Zarządzanie Grupami (ClassEmployee)

Dodanie nowej grupy

Typ: POST
Adres URL: http://localhost:8080/api/group
Body (JSON):

{
"className": "Grupa Programistów",
"rozmiar": 5
}

Pobranie listy wszystkich grup

Typ: GET
Adres URL: http://localhost:8080/api/group
Body: (zostaw puste)

Usunięcie grupy (np. o ID = 1)

Typ: DELETE
Adres URL: http://localhost:8080/api/group/1
Body: (zostaw puste)

Sprawdzenie procentowego zapełnienia grupy (np. o ID = 1)

Typ: GET
Adres URL: http://localhost:8080/api/group/1/fill
Body: (zostaw puste)

Pobranie wszystkich pracowników z konkretnej grupy (np. o ID = 1)

Typ: GET
Adres URL: http://localhost:8080/api/group/1/employee
Body: (zostaw puste)

2. Zarządzanie Pracownikami (Employee)

Dodanie pracownika (do grupy o ID = 1)

Typ: POST
Adres URL: http://localhost:8080/api/employee
Body (JSON):

{
"imie": "Jan",
"nazwisko": "Kowalski",
"condition": "PRESENT",
"rok_urodzenia": 1995,
"wynagrodzenie": 8500.00,
"grupa": {
"id": 1
}
}

Pobranie pliku CSV ze wszystkimi pracownikami

Typ: GET
Adres URL: http://localhost:8080/api/employee/csv
Body: (zostaw puste)
(Pamiętaj, że w Postmanie możesz kliknąć "Save Response" -> "Save to a file", aby zapisać to na dysku jako plik .csv!)

Usunięcie pracownika (np. o ID = 1)

Typ: DELETE
Adres URL: http://localhost:8080/api/employee/1
Body: (zostaw puste)

3. Zarządzanie Ocenami (Rating)

Dodanie oceny dla grupy (np. ocena 5 dla grupy o ID = 1)

Typ: POST
Adres URL: http://localhost:8080/api/rating
Body (JSON):

{
"groupId": 1,
"ocena": 5
}

4. Testy Zabezpieczeń (Bonus - sprawdź, czy tarcza ochronna działa!)

Test walidacji (Błąd 400 Bad Request)

Spróbuj dodać pracownika z pustym imieniem i ujemną pensją. Zobaczysz, że system to zablokuje.

Typ: POST
Adres URL: http://localhost:8080/api/employee
Body (JSON):

{
"imie": "",
"nazwisko": "Nowak",
"condition": "PRESENT",
"rok_urodzenia": 1990,
"wynagrodzenie": -1500.00,
"grupa": {
"id": 1
}
}

Test braku obiektu (Błąd 404 Not Found)

Spróbuj usunąć grupę, której na pewno nie ma w bazie. Zobaczysz, że Ochroniarz zwróci ładny komunikat o braku ID.

Typ: DELETE
Adres URL: http://localhost:8080/api/group/999
Body: (zostaw puste)
 */