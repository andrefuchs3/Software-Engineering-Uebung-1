📄 Dokumentation – Implementierung Sprint 1
1. Implementierungsumgebung

Für die Implementierung von Sprint 1 wurde eine einfache, klar strukturierte Java-Entwicklungsumgebung verwendet:

Programmiersprache: Java 17

JDK: Temurin / OpenJDK 17

Entwicklungsumgebung: Visual Studio Code

verwendet mit Java Extension Pack

Kompilierung & Ausführung: über die integrierte Java-Unterstützung in VS Code

Die Projektstruktur folgt der zuvor definierten Modul- und Paketstruktur:

/src/main/java
 ├─ core
 │   ├─ CooktopController.java
 │   └─ ZoneManager.java
 ├─ power
 │   └─ PowerControl.java
 ├─ safety
 │   └─ SafetyManager.java
 ├─ hmi
 │   ├─ HmiInput.java
 │   └─ HmiOutput.java
 └─ util
     └─ Types.java

App.java (Demo)


Damit entspricht die Implementierung exakt der Architektur aus Sprint 1.

2. Bezug zur Traceability-Matrix

Die implementierte Funktionalität basiert vollständig auf den in Sprint 1 markierten Anforderungen der Traceability-Matrix:

➡️ Traceability-Matrix:
Traceability-Matrix.md

In Sprint 1 umgesetzte Requirements:
Req-ID	Inhalt	Zugehörige Module	Implementiert durch
F-01	Kochzone aktivieren/deaktivieren	core, hmi	ZoneManager, CooktopController, HmiInput
F-02	Anzeige aktiver Kochzonen	hmi	HmiOutput
F-03 / F-04	Leistungsstufen erhöhen/verringern	power, core	PowerControl, CooktopController
F-07	Leistungsstufe jederzeit ablesbar	hmi	HmiOutput
F-13	Kindersicherung sperrt Eingaben	safety	SafetyManager
NF-01	Reaktionszeit ≤ 200 ms	gesamte Architektur	direkte Methodenaufrufe ohne Verzögerung

Alle diese Anforderungen sind in der Matrix mit „Sprint 1“ markiert und vollständig implementiert.

3. Implementierungsüberblick
3.1 Architekturbezug

Die Implementierung folgt exakt der zuvor definierten Systemstruktur:

Eingabe (hmi/HmiInput):
simuliert Touch-Eingaben des Benutzers

Steuerlogik (core/CooktopController):
zentrale Kontrolleinheit

Zonenverwaltung (core/ZoneManager):
speichert den Aktivstatus jeder Kochzone

Leistungsregelung (power/PowerControl):
verwaltet die Leistungsstufen (0–9)

Sicherheitssystem (safety/SafetyManager):
Singleton für zentrale Kindersicherung

Ausgabe (hmi/HmiOutput):
zeigt Systemzustände über Konsole an

Typdefinitionen (util/Types):
enthält Enum ZoneID

Damit ist die Architektur aus Sprint 1 zu 100 % umgesetzt.

3.2 Zentrale Komponenten und Funktionsweise
CooktopController (core)

Steuert alle Abläufe

Prüft die Kindersicherung

Delegiert an ZoneManager und PowerControl

Aktualisiert die Anzeige über HmiOutput

PowerControl (power)

Hält die aktuelle Leistungsstufe jeder Zone

Werte werden begrenzt (0–9)

Wird nur vom Controller aufgerufen

ZoneManager (core)

Speichert, welche Kochzonen aktiv sind

Prüft Aktivzustände für Leistungsänderungen

SafetyManager (safety)

Als Singleton implementiert

Sperrt alle Eingaben bei aktivierter Kindersicherung
(erfüllt F-13)

Wird über getInstance() verwendet
(entspricht dem implementierten Design Pattern)

HmiInput (hmi)

Simuliert Touch-Eingaben:

Zone aktivieren/deaktivieren

Leistung erhöhen/verringern

Kindersicherung toggeln

HmiOutput (hmi)

Zeigt:

aktive Zonen

Leistungsstufen

Sperrzustand

Fehlermeldungen

Types (util)

enthält ZoneID als Enum

stellt typsichere Bezeichner für Kochzonen bereit

4. Ablauf der wichtigsten Funktionen
4.1 Zone aktivieren (F-01)

HmiInput.selectZone() ruft

CooktopController.setZoneActive() auf

Controller prüft Kindersicherung

ZoneManager speichert Zustand

HmiOutput zeigt neuen Status an

4.2 Leistungsstufe ändern (F-03/F-04)

HmiInput.increasePower()

Controller prüft:

ob Kindersicherung aktiv ist

ob Zone aktiv ist

PowerControl erhöht/verringert Stufe

Anzeige aktualisiert (HmiOutput.showPowerLevel)

4.3 Kindersicherung (F-13)

Die Sperre wirkt global, da SafetyManager als Singleton umgesetzt wurde.

5. Demoausführung mit App.java

Die Datei App.java demonstriert typische Szenarien:

hmi.selectZone(ZoneID.FRONT_LEFT, true);
hmi.increasePower(ZoneID.FRONT_LEFT);
hmi.toggleChildLock();
hmi.increasePower(ZoneID.FRONT_LEFT); // wird gesperrt


Dies ermöglicht eine einfache Funktionsprüfung ohne UI oder Testframework.
