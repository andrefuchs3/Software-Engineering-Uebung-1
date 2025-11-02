# Traceability-Matrix – Sprint 1

Diese Tabelle bildet die Nachverfolgbarkeit (Traceability) zwischen den Requirements, 
den logischen Softwarekomponenten, den zugehörigen Sprints und Testfällen ab.

| **Req-ID** | **Beschreibung** | **Zugehörige logische Komponenten** | **Sprint-Zuordnung** | **Testfälle** |
|:------------|:-----------------|:------------------------------------|:--------------------|:--------------|
| F-01 | Kochzonenaktivierung über Touch | Bedienoberfläche / Eingabesteuerung / Kochfeldlogik | Sprint 1 | TC-01, TC-02 |
| F-02 | Anzeige aktiver Kochzonen | Anzeigeeinheit / Kochfeldlogik | Sprint 1 | TC-03 |
| F-03 | Neun Leistungsstufen pro Kochzone | Leistungsregelung | Sprint 1 | TC-04 |
| F-04 | Leistungsstufe per Plus/Minus einstellen | Eingabesteuerung / Leistungsregelung | Sprint 1 | TC-04 |
| F-05 | Reaktionszeit ≤ 200 ms bei Leistungsänderung | Leistungsregelung / Anzeigeeinheit | Sprint 1 | TC-05 |
| F-06 | Statusanzeige ( aus, aktiv, Restwärme ) | Anzeigeeinheit / Kochfeldlogik | – | – |
| F-07 | Leistungsstufe jederzeit ablesbar | Anzeigeeinheit | Sprint 1 | TC-06 |
| F-08 | Fehler- oder Sperrzustände anzeigen | Sicherheitssystem / Anzeigeeinheit | – | TC-07 |
| F-09 | Timerfunktion pro Kochzone | Zeitsteuerung / Bedienoberfläche | – | TC-08 |
| F-10 | Auto-Deaktivierung nach Timer-Ablauf | Zeitsteuerung / Kochfeldlogik | – | TC-08 |
| F-11 | Visuelle & akustische Rückmeldung nach Ablauf | Zeitsteuerung / Anzeigeeinheit / Signalgeber | – | TC-09 |
| F-12 | Timeränderung / Abbruch während Betrieb | Zeitsteuerung / Bedienoberfläche | – | TC-10 |
| F-13 | Kindersicherung sperrt Eingaben | Sicherheitssystem / Bedienoberfläche | Sprint 1 | TC-11 |
| F-14 | Fehlbedienungserkennung & Warnung | Sicherheitssystem / Anzeigeeinheit | – | TC-12 |
| NF-01 | Reaktionszeit ≤ 200 ms | Eingabesteuerung / Leistungsregelung / Anzeigeeinheit | Sprint 1 | TC-05 |
| NF-02 | Keine ungewollte Aktivierung/Deaktivierung | Sicherheitssystem / Kochfeldlogik | – | TC-12 |
| NF-03 | Schutz gegen unbeabsichtigte Eingaben | Sicherheitssystem / Bedienoberfläche | – | TC-11 |
| NF-04 | Anzeige aus 50 cm lesbar | Anzeigeeinheit | – | TC-13 |
| NF-05 | Standby-Verbrauch ≤ 1 W | Energieverwaltung | – | TC-14 |
| NF-06 | Normenkonformität ( IEC 60335 ) | Gesamtsystem / Architektur | – | – |
| NF-07 | Touchoberfläche ≥ 100 000 Betätigungen | Eingabefeld / Gehäusekomponente | – | – |

---

📊 **Hinweis:**  
- Anforderungen, die in Sprint 1 umgesetzt wurden, sind mit *Sprint 1* markiert.  
- Spätere Sprints (Timer-, Energie-, oder Sicherheitsfunktionen) werden in dieser Tabelle ergänzt.  
- Testfall-IDs beziehen sich auf die in der Sprint-1-Dokumentation beschriebenen Testfälle.
