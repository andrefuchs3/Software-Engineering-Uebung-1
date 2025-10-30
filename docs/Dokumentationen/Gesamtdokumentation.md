# Gesamtdokumentation – Induktionskochfeld: Kochfeldsteuerung

Diese Gesamtdokumentation beschreibt den aktuellen Stand der Softwareentwicklung des Projekts **Induktionskochfeld – Kochfeldsteuerung**.  
Das Dokument fasst alle bisher erarbeiteten Konzepte, Anforderungen, architektonischen Strukturen und Modellierungen zusammen und dient als zentraler Überblick über den Entwicklungsfortschritt.  
Es wird fortlaufend mit jedem Sprint aktualisiert und bildet somit die Hauptreferenz für das gesamte Projekt.

---

## 1. Projektüberblick

Ziel des Projekts ist die Entwicklung einer modularen, wartbaren und erweiterbaren Softwarearchitektur für die Steuerung eines Induktionskochfeldes.  
Das System soll die wichtigsten Funktionen eines modernen Kochfelds abbilden – von der Kochzonenaktivierung und Leistungssteuerung bis hin zu Timer-, Sicherheits- und Energieverwaltungsfunktionen.

Die Softwareentwicklung orientiert sich am **agilen Vorgehensmodell**, bei dem die Funktionalität schrittweise in mehreren **Sprints** umgesetzt wird.  
Jeder Sprint liefert ein lauffähiges Inkrement der Software und wird in einer eigenen Unterdokumentation beschrieben.

---

## 2. Anforderungsanalyse und Requirement Engineering

Die Anforderungsanalyse und das Requirement Engineering bilden die Grundlage für die gesamte Softwareentwicklung.  
Hier wurden alle **funktionalen** und **nicht-funktionalen Requirements** spezifiziert, strukturiert und priorisiert.

📄 [Requirement_Engineering.md](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Requirement_Engineering.md)  
📄 [Requirements.md](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Requirements.md)

Die Anforderungen umfassen u. a.:

- **Funktionale Requirements**
  - Aktivierung und Deaktivierung einzelner Kochzonen  
  - Leistungsstufeneinstellung und -anzeige  
  - Timerfunktion und automatische Abschaltung  
  - Kindersicherung und Fehlbedienungserkennung  
  - Akustisches und visuelles Feedback  

- **Nicht-funktionale Requirements**
  - Reaktionszeit ≤ 200 ms  
  - Energieverbrauch im Standby ≤ 1 W  
  - Normenkonformität (IEC 60335)  
  - Robustheit und Lesbarkeit der Benutzeroberfläche  
  - Sicherheit gegen unbeabsichtigte Eingaben  

Die vollständige Liste aller Requirements mit Zuordnung zu funktionalen Gruppen ist im verlinkten Dokument ersichtlich.

---

## 3. Architektur, Schnittstellen und Traceability

Im Anschluss an die Anforderungsanalyse wurde eine erste Softwarearchitektur entworfen, die alle zentralen Systemfunktionen abbildet.  
Die Architektur folgt einem **schichtenbasierten Modell**, bestehend aus:

- **Eingabeschicht** (Benutzerschnittstelle / HMI-Input)  
- **Steuerungsschicht** (Kochfeldlogik, Sicherheits- und Zeitsteuerung)  
- **Ausgabeschicht** (Anzeige- und Feedback-Komponenten)  
- **Hardwareabstraktionsschicht** (Kommunikation zu Sensoren und Heizelementen)  

Darüber hinaus wurden die Kommunikationsschnittstellen zwischen den Modulen definiert und alle Anforderungen über eine Traceability-Matrix mit Komponenten und Testfällen verknüpft.

📄 [Dokumentation – Architektur, Schnittstellen und Traceability](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Dokumentationen/Dokumentation_Architektur_Schnittstellen_Traceability.md)  
📊 [Traceability-Matrix (Google Sheets)](https://docs.google.com/spreadsheets/d/1D1JOeRtp_v65UR6-8XR4Qe0IuIOz1JcNGKBrt2488po/edit?gid=0#gid=0)

Die Architektur definiert bereits alle Hauptkomponenten des Systems, z. B.:

| Komponente | Beschreibung |
|:--|:--|
| `hmiInput` | Erfassung von Touch-Eingaben (Zone, Plus/Minus, Sperre, Timer) |
| `hmiOutput` | Anzeige und Feedback (Display, LEDs, akustische Signale) |
| `cooktopController` | Zentrale Steuerlogik des Systems |
| `powerControl` | Leistungsstufenverwaltung und Regelung |
| `zoneManager` | Verwaltung aktiver/inaktiver Kochzonen |
| `timerManager` | Zeitsteuerung (Start, Ablauf, Änderung) |
| `safetyManager` | Kindersicherung und Fehlbedienungsschutz |
| `energyManager` | Energieüberwachung und Standby-Steuerung |
| `hardwareAbstraction` | Schnittstelle zu Sensoren, Heizelementen, Buzzer |

Diese Komponenten bilden die Grundlage für die objektorientierte Modellierung im weiteren Verlauf.

---

## 4. Objektorientiertes Design
Auf Basis der zuvor definierten Architektur wurde das System in **Software-Design-Komponenten** und **UML-Modelle** überführt.  
Im ersten Sprint wurde die **Basisfunktionalität** modelliert, die folgende Requirements abdeckt:

- F-01 – Kochzonenaktivierung über Touch  
- F-02 – Anzeige aktiver Kochzonen  
- F-03 – Leistungsstufeneinstellung  
- F-04 – Leistungsänderung über Plus/Minus  
- F-07 – Leistungsanzeige  
- F-13 – Kindersicherung  
- NF-01 – Reaktionszeit ≤ 200 ms  

Diese Anforderungen bilden den Kern der Steuerungslogik und stellen eine lauffähige Grundversion des Systems dar.

