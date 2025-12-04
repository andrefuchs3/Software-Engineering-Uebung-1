# Sprint 2 – Kochfeldsteuerung

## Ziel des Sprints

Sprint 2 erweitert die Kochfeldsteuerung um zeit- und sicherheitsrelevante Funktionen.  
Im Fokus stehen die Timerfunktionen pro Kochzone, die automatische Abschaltung nach Ablauf  
sowie eine verbesserte Status- und Fehleranzeige.

Der Benutzer soll in der Lage sein,

- für jede Kochzone einen Timer zu setzen, zu ändern und abzubrechen,
- sich über Restlaufzeiten und Ablauf des Timers informieren zu lassen,
- bei Ablauf des Timers eine visuelle und akustische Rückmeldung zu erhalten,
- Fehler- und Sperrzustände klar auf der Anzeige zu erkennen.

---

## Scope Sprint 2 (Requirements)

Folgende Requirements aus der Traceability-Matrix werden in Sprint 2 adressiert:

**Funktionale Anforderungen**

- **F-06**: Statusanzeige (aus, aktiv, Restwärme)  
- **F-08**: Fehler- oder Sperrzustände anzeigen  
- **F-09**: Timerfunktion pro Kochzone  
- **F-10**: Auto-Deaktivierung nach Timer-Ablauf  
- **F-11**: Visuelle & akustische Rückmeldung nach Ablauf  
- **F-12**: Timeränderung / Abbruch während Betrieb  

**Nicht-funktionale Anforderungen**

- **NF-04**: Anzeige aus 50 cm lesbar (wird konzeptionell berücksichtigt über klare, eindeutige Zustandsanzeigen)  

Die Zuordnung zu den Software-Design-Komponenten ist in der
[Traceability-Matrix](../Traceability-Matrix.md) dokumentiert.

---

## Software-Design-Komponenten Sprint 2

Im Vergleich zu Sprint 1 werden die bestehenden Komponenten erweitert und um einen
Timer-Manager ergänzt:

- **cooktopController**  
  - bleibt die zentrale Steuerinstanz  
  - delegiert Timerfunktionen an den `timerManager`  
  - entscheidet über Auto-Abschaltung und Restwärmeanzeige

- **timerManager** (neu)  
  - verwaltet Timer pro Kochzone (`ZoneID`)  
  - speichert Restlaufzeiten und erkennt Timerablauf  
  - informiert den `cooktopController` bei Ablauf eines Timers

- **hmiInput** (erweitert)  
  - ermöglicht das Setzen, Ändern und Abbrechen von Timern  
  - bietet eine Funktion zum „Ticks“ simulieren (z. B. ein Timer-Tick pro Sekunde)

- **hmiOutput** (erweitert)  
  - zeigt Timer-Restlaufzeiten und Ablaufmeldungen an  
  - kennzeichnet Fehler- und Sperrzustände deutlich  
  - stellt eine einfache „akustische“ Rückmeldung über Konsolenausgabe bereit

- **zoneManager** (erweitert)  
  - verwaltet weiterhin aktive Zonen  
  - wird nach Timerablauf zur Deaktivierung der Zone genutzt  
  - unterstützt die Darstellung des Status (aus, aktiv, Restwärme)

---

## UML-Diagramme Sprint 2

Die UML-Diagramme für Sprint 2 liegen im Ordner `docs/Sprint 2/UML-Diagramme/` und wurden mit PlantUML erzeugt.

**Klassendiagramm**  
  ![Klassendiagramm](./UML-Diagramme/Klassendiagramm_Sprint2.png)
  
zeigt insbesondere die neue Klasse `TimerManager` und die erweiterten
Methoden in `CooktopController`, `HmiInput` und `HmiOutput`.
    
**Kommunikationsdiagramm – Timerfunktion**  
  ![Kommunikationsdiagramm](./UML-Diagramme/Kommunikationsdiagramm_Timer.png)
  
stellt die Interaktionen zwischen `HmiInput`, `CooktopController`,
`TimerManager`, `ZoneManager` und `HmiOutput` dar.

**Sequenzdiagramm – Use Case „Timer läuft ab“**  
  ![Sequenzdiagramm](./UML-Diagramme/Sequenzdiagramm_Timer.png)
  
beschreibt den Ablauf vom Setzen eines Timers bis zur Auto-Abschaltung
der Kochzone und der Anzeige der Rückmeldung.

Die Diagramme sind konsistent mit der in Sprint 1 definierten Architektur und bilden
die Erweiterungen aus Sprint 2 ab.
