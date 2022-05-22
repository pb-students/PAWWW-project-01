> Aplikacja do zapisywania i udostępniania wartych uwagi informacji, np. strony www, listy zakupów, zaproszenia na spotkanie, notatki.

> Główną funkcjonalnością jest umożliwienie zapisania na swoim koncie ciekawych informacji, udostępniania ich
innym oraz wygodnego przeglądania i przeszukiwania w dogodnym czasie.

## DB
### Dane informacji:
- [ ] tytuł
    - [ ] 3-20 znakow
- [ ] treść
    - [ ] 5-500 znakow
- [ ] link 
    - [ ] opcjonalny
- [ ] data dodania 
    - [ ] dd-mm-yyyy
- [ ] kategoria

### Dane kategorii:
- [ ] nazwa 
    - [ ] 3-20 znakow
    - [ ] lowercase

### Dane użytkownika:
- [ ] imię 
    - [ ] 3-20 znakow
    - [ ] litery
    - [ ] pierwsza duża
- [ ] nazwisko
    - [ ] 3-50 znakow
    - [ ] pierwsza duża
    - [ ] litery
- [x] login
    - [ ] 3-20 znakow
    - [ ] lowercase
- [x] hasło
    - [ ] 5+ znakow
- [ ] wiek 
    - [ ] 18+

## Role użytkowników
### `admin`
Zarządza użytkownikami
- [ ] Wyśw. listy użytkowników
- [ ] Zarządzanie rolami


### `limited_user`
Jest zarejestrowany, może przegladać udostępnione linki, ale nie może tworzyć informacji

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
- [ ] Rejestracja
- [ ] Walidacja formularza
- [x] Strona powitalna
- [ ] Wyświetlenie informacji z udostępnionego linku

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
- [ ] Formularze z walidacją
- [ ] Thymeleaf
- [ ] Sesja
- [ ] Ciasteczka
- [ ] Usługa REST (do uwierzytelniania użytkowników)
- [ ] Klient REST
- [x] Spring Security