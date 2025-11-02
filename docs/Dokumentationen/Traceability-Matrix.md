# Traceability-Matrix

Diese Tabelle bildet die Nachverfolgbarkeit (Traceability) zwischen den Requirements,  
den logischen Softwarekomponenten, den zugehörigen Software-Design-Komponenten, den Sprints und den Testfällen ab.

| **Req-ID** | **Beschreibung** | **Zugehörige logische Komponenten** | **Zugehörige Software-Design-Komponenten (Sprint 1)** | **Sprint-Zuordnung** | **Testfälle** |
|:-----------|:-----------------|:------------------------------------|:------------------------------------------------------|:--------------------|:--------------|
| F-01 | Kochzonenaktivierung über Touch | Bedienoberfläche / Eingabesteuerung / Kochfeldlogik | hmiInput, cooktopController, zoneManager | Sprint 1 | – |
| F-02 | Anzeige aktiver Kochzonen | Anzeigeeinheit / Kochfeldlogik | cooktopController, hmiOutput, zoneManager | Sprint 1 | – |
| F-03 | Neun Leistungsstufen pro Kochzone | Leistungsregelung | powerControl, cooktopController | Sprint 1 | – |
| F-04 | Leistungsstufe per Plus/Minus einstellen | Eingabesteuerung / Leistungsregelung | hmiInput, cooktopController, powerControl | Sprint 1 | – |
| F-05 | Reaktionszeit ≤ 200 ms bei Leistungsänderung | Leistungsregelung / Anzeigeeinheit | cooktopController, hmiOutput | Sprint 1 | – |
| F-06 | Statusanzeige (aus, aktiv, Restwärme) | Anzeigeeinheit / Kochfeldlogik | cooktopController, hmiOutput | – | – |
| F-07 | Leistungsstufe jederzeit ablesbar | Anzeigeeinheit | hmiOutput, cooktopController | Sprint 1 | – |
| F-08 | Fehler- oder Sperrzustände anzeigen | Sicherheitssystem / Anzeigeeinheit | safetyManager, hmiOutput | – | – |
| F-09 | Timerfunktion pro Kochzone | Zeitsteuerung / Bedienoberfläche | timerManager, cooktopController | – | – |
| F-10 | Auto-Deaktivierung nach Timer-Ablauf | Zeitsteuerung / Kochfeldlogik | timerManager, cooktopController | – | – |
| F-11 | Visuelle & akustische Rückmeldung nach Ablauf | Zeitsteuerung / Anzeigeeinheit / Signalgeber | timerManager, hmiOutput | – | – |
| F-12 | Timeränderung / Abbruch während Betrieb | Zeitsteuerung / Bedienoberfläche | timerManager, cooktopController | – | – |
| F-13 | Kindersicherung sperrt Eingaben | Sicherheitssystem / Bedienoberfläche | safetyManager, cooktopController, hmiInput | Sprint 1 | – |
| F-14 | Fehlbedienungserkennung & Warnung | Sicherheitssystem / Anzeigeeinheit | safetyManager, hmiOutput | – | – |
| NF-01 | Reaktionszeit ≤ 200 ms | Eingabesteuerung / Leistungsregelung / Anzeigeeinheit | cooktopController, hmiOutput | Sprint 1 | – |
| NF-02 | Keine ungewollte Aktivierung / Deaktivierung | Sicherheitssystem / Kochfeldlogik | safetyManager, cooktopController | Sprint 1 | – |
| NF-03 | Schutz gegen unbeabsichtigte Eingaben | Sicherheitssystem / Bedienoberfläche | safetyManager, hmiInput | Sprint 1 | – |
| NF-04 | Anzeige aus 50 cm lesbar | Anzeigeeinheit | hmiOutput | – | – |
| NF-05 | Standby-Verbrauch ≤ 1 W | Energieverwaltung | energyManager | – | – |
| NF-06 | Normenkonformität (IEC 60335) | Gesamtsystem / Architektur | – | – | – |
| NF-07 | Touchoberfläche ≥ 100 000 Betätigungen | Eingabefeld / Gehäusekomponente | – | – | – |

F=Funktional; NF=Nicht-Funktional

---
