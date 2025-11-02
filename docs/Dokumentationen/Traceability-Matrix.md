# Traceability-Matrix

Diese Tabelle bildet die Nachverfolgbarkeit (Traceability) zwischen den Requirements, 
den logischen Softwarekomponenten, den zugehörigen Sprints und Testfällen ab.

| **Req-ID** | **Beschreibung** | **Zugehörige logische Komponenten** | **Sprint-Zuordnung** | **Testfälle** |
|:------------|:-----------------|:------------------------------------|:--------------------|:--------------|
| F-01 | Kochzonenaktivierung über Touch | Bedienoberfläche / Eingabesteuerung / Kochfeldlogik | Sprint 1 | - |
| F-02 | Anzeige aktiver Kochzonen | Anzeigeeinheit / Kochfeldlogik | Sprint 1 | - |
| F-03 | Neun Leistungsstufen pro Kochzone | Leistungsregelung | Sprint 1 | - |
| F-04 | Leistungsstufe per Plus/Minus einstellen | Eingabesteuerung / Leistungsregelung | Sprint 1 | - |
| F-05 | Reaktionszeit ≤ 200 ms bei Leistungsänderung | Leistungsregelung / Anzeigeeinheit | Sprint 1 | - |
| F-06 | Statusanzeige ( aus, aktiv, Restwärme ) | Anzeigeeinheit / Kochfeldlogik | – | – |
| F-07 | Leistungsstufe jederzeit ablesbar | Anzeigeeinheit | Sprint 1 | - |
| F-08 | Fehler- oder Sperrzustände anzeigen | Sicherheitssystem / Anzeigeeinheit | – | - |
| F-09 | Timerfunktion pro Kochzone | Zeitsteuerung / Bedienoberfläche | – | - |
| F-10 | Auto-Deaktivierung nach Timer-Ablauf | Zeitsteuerung / Kochfeldlogik | – | - |
| F-11 | Visuelle & akustische Rückmeldung nach Ablauf | Zeitsteuerung / Anzeigeeinheit / Signalgeber | – | - |
| F-12 | Timeränderung / Abbruch während Betrieb | Zeitsteuerung / Bedienoberfläche | – | - |
| F-13 | Kindersicherung sperrt Eingaben | Sicherheitssystem / Bedienoberfläche | Sprint 1 | - |
| F-14 | Fehlbedienungserkennung & Warnung | Sicherheitssystem / Anzeigeeinheit | – | - |
| NF-01 | Reaktionszeit ≤ 200 ms | Eingabesteuerung / Leistungsregelung / Anzeigeeinheit | Sprint 1 | - |
| NF-02 | Keine ungewollte Aktivierung/Deaktivierung | Sicherheitssystem / Kochfeldlogik | – | - |
| NF-03 | Schutz gegen unbeabsichtigte Eingaben | Sicherheitssystem / Bedienoberfläche | – | - |
| NF-04 | Anzeige aus 50 cm lesbar | Anzeigeeinheit | – | - |
| NF-05 | Standby-Verbrauch ≤ 1 W | Energieverwaltung | – | - |
| NF-06 | Normenkonformität ( IEC 60335 ) | Gesamtsystem / Architektur | – | – |
| NF-07 | Touchoberfläche ≥ 100 000 Betätigungen | Eingabefeld / Gehäusekomponente | – | – |

---

📊 **Hinweis:**  
- Anforderungen, die in Sprint 1 umgesetzt wurden, sind mit *Sprint 1* markiert.  
- Spätere Sprints (Timer-, Energie-, oder Sicherheitsfunktionen) werden in dieser Tabelle ergänzt.  
