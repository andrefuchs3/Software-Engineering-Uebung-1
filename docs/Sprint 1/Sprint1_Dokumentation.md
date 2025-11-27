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

Diese Requirements sind in der [Traceability-Matrix](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Traceability-Matrix.md)
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

[📄 Testfälle – Modulebene](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Testf%C3%A4lle/Testf%C3%A4lle_Modulebene.md)

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

[📄 Testfälle - Integrationsebene](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Testf%C3%A4lle/Testf%C3%A4lle_Integrationsebene.md)

| Test-ID | Komponenten                            | Zielsetzung                                              |
|--------:|-----------------------------------------|----------------------------------------------------------|
| IT-01   | HmiInput ↔ CooktopController            | Weiterleitung von Benutzeraktionen (Zone aktivieren)     |
| IT-02   | CooktopController ↔ PowerControl        | Anpassung der Leistungsstufen über den Controller        |
| IT-03   | CooktopController ↔ SafetyManager       | Blockieren von Aktionen bei aktiver Kindersicherung      |

Diese Tests stellen sicher, dass die Systemkomponenten korrekt interagieren und die Controller-Logik mit den Modulen übereinstimmt.

---

## 4. Bezug zur Traceability-Matrix

Alle Testfälle aus Sprint 1 sind direkt mit den Anforderungen verknüpft, die in Sprint 1 definiert und umgesetzt wurden.

**Anforderungsauszug gemäß** [Traceability-Matrix](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Traceability-Matrix.md)

| Requirement | Inhalt                               | Abgedeckt durch      |
|------------|----------------------------------------|-----------------------|
| F-01       | Kochzone aktivieren/deaktivieren       | MT-03, IT-01          |
| F-02       | Anzeige aktiver Kochzonen              | IT-01                 |
| F-03/F-04  | Leistungsstufen erhöhen/verringern     | MT-01, IT-02          |
| F-07       | Leistungsstufe anzeigen                | IT-02                 |
| F-13       | Kindersicherung                        | MT-02, IT-03          |

---

## 5. Durchgeführte Testläufe und Dokumentation der Ergebnisse

Zur Verifikation der in **Sprint 1** implementierten Funktionalitäten wurde ein vollständiger manueller Testdurchlauf durchgeführt.
Alle definierten Testfälle auf **Modulebene** und **Integrationsebene** wurden einzeln ausgeführt und die Ergebnisse dokumentiert.

Die Testausführung erfolgte über die Datei [Test_Sprint1.java](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/src/tests/Test_Sprint1.java), welche:

- die notwendigen **Systemzustände vorbereitet**,
- die definierten **Aktionen der einzelnen Testfälle ausführt**,
- die **Beobachtungen strukturiert in der Konsole ausgibt**.

Die erzeugten Konsolenausgaben wurden anschließend mit den erwarteten Reaktionen abgeglichen und dienten als Grundlage für die manuelle Bewertung der Testergebnisse.

---

## 6. Vergleich von Architektur/Design und Implementierung 

Die Implementierung von Sprint 1 wurde mit der zuvor definierten Software-Architektur und dem Software-Design abgeglichen.  
Insgesamt zeigt sich eine **hohe Übereinstimmung** zwischen Planung und tatsächlicher Umsetzung.  
Alle zentralen Komponenten wurden wie vorgesehen implementiert:

- `HmiInput` und `HmiOutput` im Modul **hmi**
- `CooktopController` als zentrale Steuerkomponente
- `ZoneManager` zur Verwaltung der Kochzonen
- `PowerControl` zur Leistungsregelung
- `SafetyManager` zur Eingabesicherheit
- `Types.ZoneID` als typsichere Zonen-Definition

Auch die vorgesehene **Schichtenarchitektur** (HMI → Controller → Module) wurde konsequent eingehalten.

### Festgestellte Abweichungen

Trotz der hohen Übereinstimmung gab es zwei bewusste Erweiterungen, die sich im Verlauf der Implementierung als sinnvoll oder notwendig herausgestellt haben:

#### 1. Einführung des Singleton-Patterns für den SafetyManager
- **Planung:** SafetyManager ohne spezielle Instanzierungsregelung  
- **Implementierung:** SafetyManager als **Singleton** (`getInstance()`)
- **Grund:**  
  Die Kindersicherung soll systemweit einheitlich sein.  
  Mehrere Instanzen könnten zu widersprüchlichen Zuständen führen.
- **Begründung:**  
  Sinnvolle Designverbesserung, keine negative Auswirkung auf die Architektur.

#### 2. Ergänzung einer separaten Teststruktur (`src/tests`)
- **Planung:** Keine explizite Teststruktur definiert
- **Implementierung:** Ordner `src/tests` mit `Test_Sprint1.java` angelegt
- **Grund:**  
  Zur strukturierten Durchführung von Testfällen auf Modul- und Integrationsebene.
- **Begründung:**  
  Organisatorische Erweiterung, keine Abweichung im funktionalen Design.

---

## 7. Erkenntnisse aus Sprint 1

Im Verlauf der Implementierung, des Designs und der Testdurchführung in Sprint 1 wurden mehrere technische und organisatorische Erkenntnisse gewonnen.  
Diese Erkenntnisse dienen als Grundlage für Verbesserungen in Sprint 2 und darüber hinaus.

### 7.1 Positiv aufgefallene Punkte

#### ✔ Klare Modul- und Schichtenstruktur
Die zuvor definierte Schichtenarchitektur (HMI → Controller → Fachmodule) hat sich als sinnvoll erwiesen.  
Die Verantwortlichkeiten der Komponenten blieben sauber getrennt, wodurch die Implementierung nachvollziehbar und wartbar war.

#### ✔ Gute Erweiterbarkeit
Durch die modulare Struktur konnten Funktionen wie Kindersicherung, Leistungssteuerung, HMI-Eingaben und Displayausgaben klar getrennt implementiert werden.  
Dies erleichtert zukünftige Erweiterungen (z. B. Timerfunktion, akustische Signale).

#### ✔ Einführung einer Teststruktur ist hilfreich
Der separate Ordner `src/tests` und die Datei `Test_Sprint1.java` haben den Testprozess wesentlich vereinfacht.  
Ergebnisse waren reproduzierbar und logisch nachvollziehbar – ein wichtiger Schritt hin zu strukturierter Qualitätssicherung.

#### ✔ Design Pattern (Singleton) wirkt stabilisierend
Die Umstellung des `SafetyManager` auf ein **Singleton-Pattern** sorgt dafür, dass die Kindersicherung systemweit konsistent bleibt.  
Dies hat Fehlerquellen effektiv reduziert und die Architektur verbessert.


### 7.2 Herausforderungen und Verbesserungspotenziale

#### ⚠ HMI-Ausgaben abhängig von Console-Output  
Aktuell erfolgt die Validierung vieler Testfälle durch manuelle Sichtprüfung des Console-Outputs.  
Dies ist zwar für Sprint 1 ausreichend, aber langfristig fehleranfällig.

#### ⚠ Fehlendes Fehler-/Statushandling  
Der Controller gibt Fehlermeldungen über `HmiOutput` aus, aber intern existiert kein explizit modelliertes Fehler- oder Statussystem.  

#### ⚠ Noch keine persistente Zustandsverwaltung  
Alle Daten leben aktuell nur im Speicher.  
Für spätere Erweiterungen könnte eine persistente oder zentral verwaltete Zustandslogik benötigt werden.


### 7.3 Auswirkungen auf Sprint 2 und spätere Sprints

- **Einführung automatisierter Tests** (JUnit)
- **Erweiterung der Architektur um Timer und weitere Logik**
- **Einführung strukturierter Fehlerbehandlung**
- **Konsequente Versionierung nach jedem Meilenstein**
- **Vorbereitung auf weitere Design Patterns (Strategy/State)**

Diese Erkenntnisse werden in Sprint 2 berücksichtigt und dienen der Verbesserung des Entwicklungsprozesses.

