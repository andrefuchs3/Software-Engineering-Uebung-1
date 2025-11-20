# Traceability-Matrix

Diese Tabelle bildet die Nachverfolgbarkeit (Traceability) zwischen den Requirements,  
den logischen Softwarekomponenten, den zugehörigen Software-Design-Komponenten, den Sprints und den Testfällen ab.

| **Req-ID** | **Beschreibung** | **Zugehörige logische Komponenten** | **Zugehörige Software-Design-Komponenten** | **Sprint** | **Module** | **Testfälle** | **Ergebnis** |
|-----------|------------------|-------------------------------------|--------------------------------------------|------------|------------|----------------|--------------|
| F-01 | Kochzonenaktivierung über Touch | Bedienoberfläche / Eingabesteuerung / Kochfeldlogik | hmiInput, cooktopController, zoneManager | Sprint 1 | core, hmi | MT-03, IT-01 | – |
| F-02 | Anzeige aktiver Kochzonen | Anzeigeeinheit / Kochfeldlogik | cooktopController, hmiOutput, zoneManager | Sprint 1 | core, hmi | IT-01 | – |
| F-03 | Neun Leistungsstufen pro Kochzone | Leistungsregelung | powerControl, cooktopController | Sprint 1 | core, power | MT-01, IT-02 | – |
| F-04 | Leistungsstufe per Plus/Minus einstellen | Eingabesteuerung / Leistungsregelung | hmiInput, cooktopController, powerControl | Sprint 1 | core, hmi, power | MT-01, IT-02 | – |
| F-05 | Reaktionszeit ≤ 200 ms bei Leistungsänderung | Leistungsregelung / Anzeigeeinheit | cooktopController, hmiOutput | Sprint 1 | core, hmi | – | – |
| F-06 | Statusanzeige (aus, aktiv, Restwärme) | Anzeigeeinheit / Kochfeldlogik | cooktopController, hmiOutput | – | core, hmi | – | – |
| F-07 | Leistungsstufe jederzeit ablesbar | Anzeigeeinheit | hmiOutput, cooktopController | Sprint 1 | core, hmi | IT-02 | – |
| F-08 | Fehler- oder Sperrzustände anzeigen | Sicherheitssystem / Anzeigeeinheit | safetyManager, hmiOutput | – | hmi, safety | – | – |
| F-09 | Timerfunktion pro Kochzone | Zeitsteuerung / Bedienoberfläche | timerManager, cooktopController | – | core | – | – |
| F-10 | Auto-Deaktivierung nach Timer-Ablauf | Zeitsteuerung / Kochfeldlogik | timerManager, cooktopController | – | core | – | – |
| F-11 | Visuelle & akustische Rückmeldung nach Ablauf | Zeitsteuerung / Anzeigeeinheit / Signalgeber | timerManager, hmiOutput | – | hmi | – | – |
| F-12 | Timeränderung / Abbruch während Betrieb | Zeitsteuerung / Bedienoberfläche | timerManager, cooktopController | – | core | – | – |
| F-13 | Kindersicherung sperrt Eingaben | Sicherheitssystem / Bedienoberfläche | safetyManager, cooktopController, hmiInput | Sprint 1 | core, safety | MT-02, IT-03 | – |
| F-14 | Fehlbedienungserkennung & Warnung | Sicherheitssystem / Anzeigeeinheit | safetyManager, hmiOutput | – | hmi, safety | – | – |
| NF-01 | Reaktionszeit ≤ 200 ms | Eingabesteuerung / Leistungsregelung / Anzeigeeinheit | cooktopController, hmiOutput | Sprint 1 | core, hmi | – | – |
| NF-02 | Keine ungewollte Aktivierung/Deaktivierung | Sicherheitssystem / Kochfeldlogik | safetyManager, cooktopController | Sprint 1 | core, safety | IT-03 | – |
| NF-03 | Schutz gegen unbeabsichtigte Eingaben | Sicherheitssystem / Bedienoberfläche | safetyManager, hmiInput | Sprint 1 | hmi, safety | MT-02 | – |
| NF-04 | Anzeige aus 50 cm lesbar | Anzeigeeinheit | hmiOutput | – | hmi | – | – |
| NF-05 | Standby-Verbrauch ≤ 1 W | Energieverwaltung | energyManager | – | – | – | – |
| NF-06 | Normenkonformität (IEC 60335) | Gesamtsystem / Architektur | – | – | – | – | – |
| NF-07 | Touchoberfläche ≥ 100 000 Betätigungen | Eingabefeld / Gehäusekomponente | – | – | – | – | – |

F = Funktional, NF = Nicht-Funktional


