> Aplikacja do zapisywania i udostępniania wartych uwagi informacji, np. strony www, listy zakupów, zaproszenia na spotkanie, notatki.

> Główną funkcjonalnością jest umożliwienie zapisania na swoim koncie ciekawych informacji, udostępniania ich
innym oraz wygodnego przeglądania i przeszukiwania w dogodnym czasie.

## DB
### Dane informacji:
- [x] tytuł
    - [x] 3-20 znakow
- [x] treść
    - [x] 5-500 znakow
- [x] link 
    - [x] opcjonalny
- [x] data dodania 
    - [x] dd-mm-yyyy
- [x] kategoria

### Dane kategorii:
- [x] nazwa 
    - [x] 3-20 znakow
    - [x] lowercase

### Dane użytkownika:
- [x] imię 
    - [x] 3-20 znakow
    - [x] litery
    - [x] pierwsza duża
- [x] nazwisko
    - [x] 3-50 znakow
    - [x] pierwsza duża
    - [x] litery
- [x] login
    - [x] 3-20 znakow
    - [x] lowercase
- [x] hasło
    - [x] 5+ znakow
- [x] wiek 
    - [x] 18+

## Role użytkowników
### `admin`
Zarządza użytkownikami
- [x] Wyśw. listy użytkowników
- [ ] Zarządzanie rolami


### `limited_user`
Jest zarejestrowany, może przegladać udostępnione linki, ale nie może tworzyć informacji
- [ ] Wyświetlenie informacji z udostępnionego linku

### `user`
Może tworzyć informacje i udostępniać je innym, przeglądać w oddzielnym widoku informacje udostępnione dla niego
- [ ] dodanie/edycja/usunięcie przez siebie zebranych informacji
- [ ] Walidacja formularza
- [ ] Edycja na danych bieżących
- [ ] Dodanie nowej kategorii
- [ ] Wyświetlenie udostępnionych przez innych informacji
- [ ] Udostępnienie: ze wskazaniem na konkretnego użytkownika lub w linku
- [ ] Wyświetlanie „swoich” informacji: sortowanie w obu kierunkach (data, kategoria, alfabetycznie)
- [ ] Zapamiętanie kierunków i kryteriów sortowania
- [ ] Filtrowanie według daty (od aktualnej) i kategorii (od najbardziej popularnej)
- [x] Logowanie
- [ ] Zapis do bazy danych dopiero przy wylogowaniu/wygaśnięciu sesji

### użytkownik niezalogowany
Ma dostęp tylko do strony początkowej i strony rejestracji
- [x] Rejestracja
- [x] Walidacja formularza
- [x] Strona powitalna

## Elementy techniczne
- [x] Kontrolery
- [ ] Baza danych
  - Obecnie mamy H2, ktore dziala w pamieci, trzeba dodac postgresa
    ```properties
      # application.properties
      spring.datasource.url=jdbc:postgres://localhost:5432/db
      spring.datasource.username=postgres
      spring.datasource.password=password
    ```
- [x] Formularze z walidacją
- [x] Thymeleaf
- [ ] Sesja
- [ ] Ciasteczka
- [ ] Usługa REST (do uwierzytelniania użytkowników)
- [ ] Klient REST
- [x] Spring Security