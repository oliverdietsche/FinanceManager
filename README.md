# FinanceManager
Dieses Projekt wurde als Schulprojekt während der Berufslehre als Informatiker erstellt.

## Summary
Diese Applikation wird zum Erfassen von Zahlungen genutzt. Die Zahlungen müssen jeweils einem Projekt angehören, die man verwalten kann. Um die Zahlungen noch ein wenig zu ordnen kann man ihnen noch eine Kategorie zuweisen.

Zum Login gibt es Nutzer, welche eine beliebige Anzahl an Rollen haben können.

## Starting Guide
1. In das Verzeichnis des Projects im Bash oder CMD wechseln
2.  - Wenn du Bash verwendest: `./gradlew bootRun`
    - Wenn du CMD verwendest: `gradlew.bat bootRun`
3. Unter `localhost:8081/fe/projects` sind alle Projekte verwaltbar
4. Unter `localhost:8081/fe/users` sind alle Nutzer verwaltbar
5. Wenn das Login-Formular kommt, dann kann sich mit folgendem User eingeloggt werden (neu erstellte User funktionieren auch):
    - Username: `user`
    - Password: `password`
    
## Logout Möglichkeit
Falls man sich ausloggen möchte kann man manuell in den Cookies die `JSESSIONID` löschen.
