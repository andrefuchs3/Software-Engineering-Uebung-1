# Dokumentation Testfälle – Sprint 1

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

Die definierten Testfälle decken die wichtigsten Basismodule ab:  
`PowerControl`, `SafetyManager` und das Enum `ZoneID`.

**Vollständige Modultests:**  
 `docs/Dokumentationen/Sprint1_Test_Modulebene.md`

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

**Vollständige Integrationstests:**  
`docs/Dokumentationen/Sprint1_Test_Integrationsebene.md`

| Test-ID | Komponenten                            | Zielsetzung                                              |
|--------:|-----------------------------------------|----------------------------------------------------------|
| IT-01   | HmiInput ↔ CooktopController            | Weiterleitung von Benutzeraktionen (Zone aktivieren)     |
| IT-02   | CooktopController ↔ PowerControl        | Anpassung der Leistungsstufen über den Controller        |
| IT-03   | CooktopController ↔ SafetyManager       | Blockieren von Aktionen bei aktiver Kindersicherung      |

Diese Tests stellen sicher, dass die Systemkomponenten korrekt interagieren und die Controller-Logik mit den Modulen übereinstimmt.

---

## 4. Bezug zur Traceability-Matrix

Alle Testfälle aus Sprint 1 sind direkt mit den Anforderungen verknüpft, die in Sprint 1 definiert und umgesetzt wurden.

**Anforderungsauszug gemäß Traceability-Matrix:**

| Requirement | Inhalt                               | Abgedeckt durch      |
|------------|----------------------------------------|-----------------------|
| F-01       | Kochzone aktivieren/deaktivieren       | MT-03, IT-01          |
| F-02       | Anzeige aktiver Kochzonen              | IT-01                 |
| F-03/F-04  | Leistungsstufen erhöhen/verringern     | MT-01, IT-02          |
| F-07       | Leistungsstufe anzeigen                | IT-02                 |
| F-13       | Kindersicherung                        | MT-02, IT-03          |


Die vollständige Nachverfolgbarkeit ist dokumentiert unter:

 `docs/Dokumentationen/Traceability-Matrix.md`


