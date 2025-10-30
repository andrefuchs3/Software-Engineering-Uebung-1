# Dokumentation – Sprint 1 : Kochfeldsteuerung

## 1  Zielsetzung und Kontext
Der erste Sprint diente dem Aufbau der Kernfunktionen des Systems.  
Dabei stand die grundlegende Interaktion zwischen Benutzer, Steuerlogik und Anzeige im Vordergrund.  
Das Ziel war die Entwicklung einer funktionalen Basisversion, mit der der Benutzer eine Kochzone aktivieren, deren Leistungsstufe verändern und die Änderungen über das Display nachvollziehen kann.  
Zudem wurde die Kindersicherung als sicherheitsrelevante Kernfunktion implementiert, um Fehlbedienungen zu vermeiden.  
Die Reaktionszeit auf Benutzereingaben durfte dabei 200 ms nicht überschreiten.

---

## 2  Abgedeckte Requirements
Im Sprint 1 wurden folgende Anforderungen aus der Gesamtspezifikation umgesetzt:

| ID | Beschreibung | Typ |
|:--|:--|:--|
| F-01 | Kochzonenaktivierung über Touch | Funktional |
| F-02 | Anzeige aktiver Kochzonen | Funktional |
| F-03 / F-04 | Leistungsstufeneinstellung (Plus/Minus) | Funktional |
| F-07 | Leistungsstufe jederzeit ablesbar | Funktional |
| F-13 | Kindersicherung sperrt Eingaben | Funktional |
| NF-01 | Reaktionszeit ≤ 200 ms | Nicht-funktional |

Diese Auswahl bildet die funktionale Grundlage des Systems und ermöglicht eine erste lauffähige Version, auf der spätere Erweiterungen (Timer, Energieverwaltung u. a.) aufbauen können.

---

## 3  Design und Architektur
Aus der bestehenden Systemarchitektur wurden für Sprint 1 folgende logische Komponenten konkretisiert:

- **hmiInput** – erfasst die Touch-Eingaben des Benutzers.  
- **cooktopController** – koordiniert alle Interaktionen und steuert die Ausführung der Befehle.  
- **powerControl** – verarbeitet Leistungsänderungen und verwaltet die aktuelle Leistungsstufe.  
- **zoneManager** – verfolgt den Zustand einzelner Kochzonen (aktiv/inaktiv).  
- **safetyManager** – überwacht und steuert die Kindersicherung.  
- **hmiOutput** – visualisiert alle Zustände (aktive Zonen, Leistung, Sperrstatus).

Diese Aufteilung folgt dem Prinzip der **Modularität** und ermöglicht eine spätere Erweiterung um neue Funktionen, ohne bestehende Module zu verändern.

---

## 4  UML-Modelle

### 4.1  Klassendiagramm
Das Klassendiagramm beschreibt die strukturelle Beziehung zwischen den Komponenten und bildet die Grundlage für die objektorientierte Umsetzung.  
Die zentralen Klassen sind:
- `HmiInput` → nimmt Benutzereingaben entgegen  
- `CooktopController` → steuert den Ablauf  
- `SafetyManager` → überprüft Eingabesicherheit  
- `PowerControl` → verändert Leistungswerte  
- `ZoneManager` → verwaltet Zonenstatus  
- `HmiOutput` → stellt Zustände dar  

![Klassendiagramm](./UML-Diagramme/Klassendiagramm.png)

---

### 4.2  Sequenzdiagramm
Das Sequenzdiagramm beschreibt den Ablauf des Use Cases **„Leistungsstufe erhöhen“**.  
Der Benutzer interagiert über die Touch-Oberfläche, die Steuerung prüft den Sicherheitsstatus und aktualisiert die Leistungsanzeige in Echtzeit.

![Sequenzdiagramm](./UML-Diagramme/Sequenzdiagramm.png)

---

### 4.3  Kommunikationsdiagramm
Das Kommunikationsdiagramm zeigt die strukturellen Nachrichtenbeziehungen zwischen den beteiligten Komponenten für denselben Ablauf.  
Es verdeutlicht die logische Kommunikation zwischen Eingabe, Steuerung, Sicherheitsprüfung, Leistungsregelung und Ausgabe.

![Kommunikationsdiagramm](./UML-Diagramme/Kommunikationsdiagramm.png)

---

## 5  Bewertung und Ausblick
Mit Sprint 1 wurde die funktionale Basis der Kochfeldsteuerung erfolgreich modelliert.  
Die zentralen Kommunikations- und Steuerungswege sind definiert und getestet.  
Die Architektur ist so aufgebaut, dass sie im nächsten Sprint um **Timer-Funktionen**, **akustisches Feedback** und **Energieverwaltung** erweitert werden kann.  

Künftige Arbeitsschritte:
- Integration der Timer- und Abschaltlogik (F-09 – F-12)  
- Erweiterung des `CooktopController` um Zeitsteuerung  
- Ergänzung zusätzlicher Testfälle in der Traceability-Matrix  
- Vorbereitung der Implementierung in Sprint 2  

---

📄 **Verknüpfte Dokumente:**
- [Gesamtdokumentation](../Dokumentationen/Gesamtdokumentation.md)  
- [Traceability-Matrix (Google Sheets)](https://docs.google.com/spreadsheets/d/1D1JOeRtp_v65UR6-8XR4Qe0IuIOz1JcNGKBrt2488po/edit?gid=0#gid=0)  
- [Dokumentation Architektur, Schnittstellen & Traceability](../Dokumentationen/Dokumentation_Architektur_Schnittstellen_Traceability.md)
