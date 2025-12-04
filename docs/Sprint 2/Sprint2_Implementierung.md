# Sprint 2 – Implementierung

## 1. Implementierungsumgebung

Die Implementierungsumgebung entspricht Sprint 1:

- Programmiersprache: Java 17  
- JDK: Temurin / OpenJDK 17  
- IDE: Visual Studio Code mit Java Extension Pack  
- Kompilierung & Ausführung: integrierte Java-Unterstützung in VS Code

Die Modul- und Paketstruktur wurde um die Timer-Funktionalität erweitert:

```text
/src/
 ├─ core
 │   ├─ CooktopController.java   (erweitert um Timer-Logik)
 │   ├─ ZoneManager.java
 │   └─ TimerManager.java        (neu)
 ├─ power
 │   └─ PowerControl.java
 ├─ safety
 │   └─ SafetyManager.java
 ├─ hmi
 │   ├─ HmiInput.java            (erweitert: Timer-Eingaben)
 │   └─ HmiOutput.java           (erweitert: Timer-/Fehleranzeige)
 └─ util
     └─ Types.java
```
## 2. Bezug zur Traceability-Matrix

Die in **Sprint 2** implementierten Funktionen decken folgende Requirements ab (siehe [Traceability-Matrix](../Traceability-Matrix.md)):

| **Req-ID** | **Inhalt**                                  | **Abgedeckt durch**                                                       |
|------------|----------------------------------------------|---------------------------------------------------------------------------|
| F-06       | Statusanzeige (aus, aktiv, Restwärme)        | CooktopController, ZoneManager, HmiOutput                                 |
| F-08       | Fehler-/Sperrzustände anzeigen               | HmiOutput.showError, SafetyManager                                        |
| F-09       | Timerfunktion pro Kochzone                   | TimerManager, CooktopController, HmiInput                                 |
| F-10       | Auto-Deaktivierung nach Timer-Ablauf         | TimerManager.tick, CooktopController.handleTimerTick                      |
| F-11       | Visuelle & akustische Rückmeldung            | HmiOutput.showTimerExpired, HmiOutput.beep                                |
| F-12       | Timeränderung / Timer-Abbruch                | TimerManager.changeTimer / cancelTimer, HmiInput                           |


---

## 3. Implementierungsüberblick

Die Timer-Funktionalität wurde vollständig innerhalb der bestehenden Architektur realisiert und ergänzt das Verhalten aus Sprint 1 ohne strukturelle Brüche.

### **Ablauf der Timer-Interaktion**

1. **Benutzerinteraktion über HmiInput**  
   Neue Methoden:
   - `setTimer(zone, seconds)`  
   - `changeTimer(zone, seconds)`  
   - `cancelTimer(zone)`  
   - `tickTimer()` (vom System/Taktgeber aufgerufen)

2. **Validierung und Steuerung durch CooktopController**
   - prüft, ob die Zone aktiv ist  
   - prüft, ob die Kindersicherung deaktiviert ist  
   - ruft die entsprechenden Methoden im `TimerManager` auf  
   - aktualisiert die Anzeige über `HmiOutput`

3. **Timer-Verwaltung im TimerManager**
   - speichert Restlaufzeiten je Zone  
   - zählt über `tick()` die Zeit herunter  
   - meldet ausgelaufene Timer über `timerExpired(zone)` an den Controller zurück

4. **Systemreaktion bei Timerablauf**
   - CooktopController deaktiviert die Zone (`setActive(zone, false)`)  
   - Leistungsstufe wird auf 0 gesetzt  
   - Benutzer erhält eine Rückmeldung über:
     - `HmiOutput.showTimerExpired(zone)`  
     - `HmiOutput.beep()` (akustisches Signal)
