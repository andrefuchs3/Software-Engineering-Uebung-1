# Sprint 2 – Implementierung

## 1. Implementierungsumgebung

Die Implementierungsumgebung entspricht Sprint 1:

- Programmiersprache: Java 17  
- JDK: Temurin / OpenJDK 17  
- IDE: Visual Studio Code mit Java Extension Pack  
- Kompilierung & Ausführung: integrierte Java-Unterstützung in VS Code

Die Modul- und Paketstruktur wurde um die Timer-Funktionalität erweitert:

```text
/src/
 ├─ core
 │   ├─ CooktopController.java   (erweitert um Timer-Logik)
 │   ├─ ZoneManager.java
 │   └─ TimerManager.java        (neu)
 ├─ power
 │   └─ PowerControl.java
 ├─ safety
 │   └─ SafetyManager.java
 ├─ hmi
 │   ├─ HmiInput.java            (erweitert: Timer-Eingaben)
 │   └─ HmiOutput.java           (erweitert: Timer-/Fehleranzeige)
 └─ util
     └─ Types.java
