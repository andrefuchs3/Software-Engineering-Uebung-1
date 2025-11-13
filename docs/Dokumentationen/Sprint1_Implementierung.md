# Dokumentation – Implementierung Sprint 1

## 1. Implementierungsumgebung

Für die Implementierung von Sprint 1 wurde eine einfache, klar strukturierte Java-Entwicklungsumgebung verwendet:

- **Programmiersprache:** Java 17  
- **JDK:** Temurin / OpenJDK 17  
- **Entwicklungsumgebung:** Visual Studio Code  
- **Extensions:** Java Extension Pack  
- **Kompilierung & Ausführung:** über die integrierte Java-Unterstützung in VS Code

Die Projektstruktur folgt der zuvor definierten Modulstruktur:

/src/main/java

├─ core

│ ├─ CooktopController.java

│ └─ ZoneManager.java

├─ power

│ └─ PowerControl.java

├─ safety

│ └─ SafetyManager.java

├─ hmi

│ ├─ HmiInput.java

│ └─ HmiOutput.java

└─ util

└─ Types.java

App.java

---

## 2. Traceability-Matrix

Die implementierte Funktionalität basiert vollständig auf den in Sprint 1 markierten Anforderungen der Traceability-Matrix:

➡️ **Traceability-Matrix:**  
[Traceability-Matrix.md](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Dokumentationen/Traceability-Matrix.md)

### In Sprint 1 umgesetzte Requirements

| Req-ID | Inhalt | Zugehörige Module | Implementiert durch |
|--------|--------|-------------------|----------------------|
| **F-01** | Kochzone aktivieren/deaktivieren | core, hmi | ZoneManager, CooktopController, HmiInput |
| **F-02** | Anzeige aktiver Kochzonen | hmi | HmiOutput |
| **F-03 / F-04** | Leistungsstufen erhöhen/verringern | power, core | PowerControl, CooktopController |
| **F-07** | Leistungsstufe jederzeit ablesbar | hmi | HmiOutput |
| **F-13** | Kindersicherung sperrt Eingaben | safety | SafetyManager |
| **NF-01** | Reaktionszeit ≤ 200 ms | gesamte Architektur | direkte Methodenaufrufe ohne Verzögerung |

Alle diese Anforderungen sind in der Matrix mit „Sprint 1“ markiert und vollständig implementiert.

---

## 3. Implementierungsüberblick

### 3.1 Architekturbezug

Die Implementierung folgt exakt der zuvor definierten Systemstruktur:

- **Eingabe (hmi/HmiInput):** simuliert Touch-Eingaben des Benutzers  
- **Steuerlogik (core/CooktopController):** zentrale Kontrolleinheit  
- **Zonenverwaltung (core/ZoneManager):** speichert den Aktivstatus jeder Kochzone  
- **Leistungsregelung (power/PowerControl):** verwaltet die Leistungsstufen (0–9)  
- **Sicherheitssystem (safety/SafetyManager):** Singleton für zentrale Kindersicherung  
- **Ausgabe (hmi/HmiOutput):** zeigt Systemzustände über Konsole an  
- **Typdefinitionen (util/Types):** enthält Enum `ZoneID` für typsichere Zonenbezeichner  

---

### 3.2 Zentrale Komponenten und Funktionsweise

#### CooktopController (core)
- Steuert alle Abläufe  
- Prüft die Kindersicherung  
- Delegiert an ZoneManager und PowerControl  
- Aktualisiert die Anzeige über HmiOutput  

#### PowerControl (power)
- Hält die aktuelle Leistungsstufe jeder Zone  
- Werte werden begrenzt (0–9)  
- Wird ausschließlich vom Controller verwendet  

#### ZoneManager (core)
- Speichert, welche Kochzonen aktiv sind  
- Prüft Aktivzustände für Leistungsänderungen  

#### SafetyManager (safety)
- Als Singleton implementiert  
- Sperrt alle Eingaben bei aktivierter Kindersicherung  
- Wird über `getInstance()` verwendet  

#### HmiInput (hmi)
Simuliert Touch-Eingaben:
- Zone aktivieren/deaktivieren  
- Leistung erhöhen/verringern  
- Kindersicherung toggeln  

#### HmiOutput (hmi)
Zeigt:
- aktive Zonen  
- Leistungsstufen  
- Sperrzustand  
- Fehlermeldungen  

#### Types (util)
- Enthält Enum `ZoneID`  
- Stellt typsichere Bezeichner für Kochzonen bereit  

---

## 4. Ablauf der wichtigsten Funktionen

### 4.1 Zone aktivieren (F-01)
1. `HmiInput.selectZone()` ruft  
2. `CooktopController.setZoneActive()` auf  
3. Controller prüft Kindersicherung  
4. ZoneManager speichert Zustand  
5. HmiOutput zeigt neuen Status  

### 4.2 Leistungsstufe ändern (F-03/F-04)
1. `HmiInput.increasePower()`  
2. Controller prüft:
   - ist die Zone aktiv?  
   - ist die Kindersicherung deaktiviert?  
3. PowerControl erhöht oder verringert die Stufe  
4. HmiOutput zeigt die neue Leistungsstufe an  

### 4.3 Kindersicherung (F-13)
Die Sperre wirkt global, da `SafetyManager` als Singleton umgesetzt wurde.

---

## 5. Demoausführung mit App.java

Die Datei **App.java** demonstriert typische Szenarien:

```java
hmi.selectZone(ZoneID.FRONT_LEFT, true);
hmi.increasePower(ZoneID.FRONT_LEFT);
hmi.toggleChildLock();
hmi.increasePower(ZoneID.FRONT_LEFT); // wird gesperrt

