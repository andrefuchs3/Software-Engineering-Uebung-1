# Sprint 1 – Kochfeldsteuerung

## Ziel des Sprints
Sprint 1 definiert die Kernfunktionalität der Kochfeldsteuerung.  
Der Benutzer kann eine Kochzone aktivieren, die Leistungsstufe verändern und den aktuellen Zustand auf der Anzeige ablesen.  
Eingaben können durch eine Kindersicherung gesperrt werden.  
Die Systemreaktion auf Benutzereingaben muss innerhalb von 200 ms erfolgen.

---

## Scope Sprint 1 (Requirements)
Folgende Requirements sind Bestandteil von Sprint 1:
- F-01: Kochzonenaktivierung über Touch  
- F-02: Anzeige aktiver Kochzonen  
- F-03 / F-04: Leistungsstufeneinstellung (Plus/Minus)  
- F-07: Leistungsstufe jederzeit ablesbar  
- F-13: Kindersicherung sperrt Eingaben  
- NF-01: Reaktionszeit ≤ 200 ms  

Diese Requirements sind in der [Traceability-Matrix](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Dokumentationen/Traceability-Matrix.md)
 markiert.

---

## Software-Design-Komponenten Sprint 1
- **hmiInput** – Erfasst Benutzereingaben (Zonenwahl, Leistungsänderung, Sperre)  
- **cooktopController** – Zentrale Steuerinstanz, prüft Sicherheit, löst Aktionen aus, aktualisiert Anzeige  
- **safetyManager** – Verwaltet die Kindersicherung und blockiert Eingaben bei Bedarf  
- **powerControl** – Setzt die Leistungsstufen der Kochzonen und hält die aktuelle Stufe vor  
- **zoneManager** – Verwaltet den Zustand der Kochzonen (aktiv/inaktiv etc.)  
- **hmiOutput** – Visualisiert den aktuellen Zustand (aktive Zone, Stufe, Sperre)

---

## UML-Diagramme Sprint 1

### Klassendiagramm
![Klassendiagramm](./UML-Diagramme/Klassendiagramm.png)

Das Klassendiagramm wurde angepasst, um das **Singleton-Pattern** zu verdeutlichen.
Der **SafetyManager** ist nun als Singleton gekennzeichnet, und der CooktopController greift über die statische Methode **getInstance()** darauf zu.
Dadurch bleibt die Kindersicherung systemweit konsistent, ohne die Architektur zu verändern:

![Klassendiagramm_v2](./UML-Diagramme/Klassendiagramm_v2.png)

### Sequenzdiagramm  
Use Case: *Leistungsstufe erhöhen*  
![Sequenzdiagramm](./UML-Diagramme/Sequenzdiagramm.png)

### Kommunikationsdiagramm  
Strukturierte Sicht auf denselben Use Case  
![Kommunikationsdiagramm](./UML-Diagramme/Kommunikationsdiagramm.png)

# Testfälle – Sprint 1

## 1. Zielsetzung der Testaktivitäten

Im Rahmen von **Sprint 1** wurden zentrale Funktionen der Kochfeldsteuerung implementiert, darunter:

- Aktivieren und Deaktivieren einzelner Kochzonen  
- Anpassen der Leistungsstufen  
- Anzeige von Systemzuständen  
- Zentrale Kindersicherung  

Zur Sicherstellung der fachlichen und technischen Qualität wurden Testfälle auf zwei Ebenen definiert:

- **Modulebene**: Prüfung der algorithmischen Korrektheit einzelner Komponenten  
- **Integrationsebene**: Prüfung der Zusammenarbeit zweier Software-Design-Komponenten inklusive korrekter Aufrufsyntax  

---

## 2. Testfälle auf Modulebene

Die **Modulebene** (Unit-Test-Level) fokussiert auf das unabhängige Verhalten einzelner Komponenten.  
Ziel ist die Überprüfung:

- korrekter Algorithmen  
- korrekter Datenverwaltung  
- stabilen, erwartungskonformen Verhaltens ohne externe Abhängigkeiten

[📄 Testfälle – Modulebene](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Dokumentationen/Sprint1_Test_Modulebene.md)

Die definierten Testfälle decken die wichtigsten Basismodule ab:  
`PowerControl`, `SafetyManager` und das Enum `ZoneID`.


| Test-ID | Modul                        | Zweck                                       |
|--------:|------------------------------|---------------------------------------------|
| MT-01   | power (PowerControl)         | Validierung der Leistungsstufenlogik        |
| MT-02   | safety (SafetyManager)       | Korrektes Sperren/Entsperren des Systems    |
| MT-03   | util (Types / ZoneID Enum)   | Überprüfung der definierten Kochzonen       |

Diese Tests stellen sicher, dass die Kernlogik unabhängig von anderen Komponenten korrekt funktioniert.

---

## 3. Testfälle auf Integrationsebene

Die **Integrationsebene** untersucht das Zusammenspiel zweier Komponenten und analysiert dabei:

- korrekte Übergabe der Daten  
- korrekte Aufrufsyntax  
- erwartetes Verhalten in der Zusammenarbeit  
- Einhaltung der Architekturvorgaben  

[📄 Testfälle - Integrationsebene](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Dokumentationen/Sprint1_Test_Integrationsebene.md)

| Test-ID | Komponenten                            | Zielsetzung                                              |
|--------:|-----------------------------------------|----------------------------------------------------------|
| IT-01   | HmiInput ↔ CooktopController            | Weiterleitung von Benutzeraktionen (Zone aktivieren)     |
| IT-02   | CooktopController ↔ PowerControl        | Anpassung der Leistungsstufen über den Controller        |
| IT-03   | CooktopController ↔ SafetyManager       | Blockieren von Aktionen bei aktiver Kindersicherung      |

Diese Tests stellen sicher, dass die Systemkomponenten korrekt interagieren und die Controller-Logik mit den Modulen übereinstimmt.

---

## 4. Bezug zur Traceability-Matrix

Alle Testfälle aus Sprint 1 sind direkt mit den Anforderungen verknüpft, die in Sprint 1 definiert und umgesetzt wurden.

**Anforderungsauszug gemäß** [Traceability-Matrix](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Dokumentationen/Traceability-Matrix.md)

| Requirement | Inhalt                               | Abgedeckt durch      |
|------------|----------------------------------------|-----------------------|
| F-01       | Kochzone aktivieren/deaktivieren       | MT-03, IT-01          |
| F-02       | Anzeige aktiver Kochzonen              | IT-01                 |
| F-03/F-04  | Leistungsstufen erhöhen/verringern     | MT-01, IT-02          |
| F-07       | Leistungsstufe anzeigen                | IT-02                 |
| F-13       | Kindersicherung                        | MT-02, IT-03          |




