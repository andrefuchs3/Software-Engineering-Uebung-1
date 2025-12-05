## Testfälle - Modulebene


### **MT-01 – Modul: power (PowerControl)**
**Zweck:** Algorithmische Korrektheit der Leistungsstufen-Logik  

|  | Beschreibung |
|-------|---------------|
| **Modul** | power |
| **Vorbedingung** | Modul *power* ist initialisierbar; `PowerControl` erstellt; Leistungsstufe von `FRONT_LEFT = 5` |
| **Aktion** | `increaseLevel(ZoneID.FRONT_LEFT)` |
| **Erwartete Reaktion** | Stufe steigt auf **6** |
| **Nachbedingung** | `getLevel(FRONT_LEFT)` liefert **6** |
| **Ergebnis** |  Bestanden  |

---

### **MT-02 – Modul: safety (SafetyManager)**
**Zweck:** Korrektes Sperren/Entsperren im Singleton-Modul  

|  | Beschreibung |
|-------|---------------|
| **Modul** | safety |
| **Vorbedingung** | `SafetyManager.getInstance()` existiert; Kindersicherung deaktiviert |
| **Aktion** | `lockInput()` danach `isLocked()` |
| **Erwartete Reaktion** | Rückgabe = **true** |
| **Nachbedingung** | Sperre aktiv; später durch `unlockInput()` wieder deaktivierbar |
| **Ergebnis** |  Bestanden  |

---

### **MT-03 – Modul: util (Types / Enums)**
**Zweck:** Korrekte Auflistung und Existenz aller `ZoneID`-Werte  

|  | Beschreibung |
|-------|---------------|
| **Modul** | util |
| **Vorbedingung** | `Types.ZoneID.values()` existieren |
| **Aktion** | Abfrage aller Enum-Werte |
| **Erwartete Reaktion** | Werte: `FRONT_LEFT`, `FRONT_RIGHT`, `BACK_LEFT`, `BACK_RIGHT` |
| **Nachbedingung** | Enum unverändert und valide |
| **Ergebnis** |  Bestanden - Manuelle Sichtprüfung korrekt  |

---

### MT-04 – Modul: TimerManager (Timer starten & runterzählen)

**Zweck:** Algorithmische Korrektheit beim Starten eines Timers und beim Herunterzählen per `tick()`.

| Punkt              | Beschreibung                                                                 |
|--------------------|------------------------------------------------------------------------------|
| Modul              | timer (TimerManager)                                                         |
| Vorbedingung       | `TimerManager` ist initialisiert. Für Zone `FRONT_LEFT` ist kein Timer aktiv. |
| Aktion             | `startTimer(FRONT_LEFT, 5)` aufrufen, danach 5× `tick()` ausführen.          |
| Erwartete Reaktion | Interne Restzeit wird von 5 auf 0 heruntergezählt. Beim Übergang auf 0 wird ein Ablauf-Ereignis für `FRONT_LEFT` erzeugt (z. B. Rückgabe/Callback). |
| Nachbedingung      | Kein positiver Restwert mehr für `FRONT_LEFT`; Timer für diese Zone gilt als **abgelaufen**. |
| Ergebnis           | – (wird nach Testdurchführung eingetragen, z. B. *Bestanden* / *Fehlverhalten*) |

---

### MT-05 – Modul: TimerManager (Timer ändern)

**Zweck:** Änderung eines laufenden Timers (Restzeit anpassen).

| Punkt              | Beschreibung                                                                 |
|--------------------|------------------------------------------------------------------------------|
| Modul              | timer (TimerManager)                                                         |
| Vorbedingung       | `TimerManager` ist initialisiert. Timer für `FRONT_RIGHT` wurde mit `startTimer(FRONT_RIGHT, 10)` gestartet. Restzeit ist noch ≥ 7 Sekunden. |
| Aktion             | Aufruf von `changeTimer(FRONT_RIGHT, 3)`. Anschließend 3× `tick()` ausführen. |
| Erwartete Reaktion | Restzeit wird unmittelbar auf 3 gesetzt. Nach 3 weiteren `tick()` läuft der Timer ab und erzeugt ein Ablauf-Ereignis für `FRONT_RIGHT`. |
| Nachbedingung      | Timer für `FRONT_RIGHT` ist nach genau 3 Ticks abgelaufen. Kein weiterer Ablauf für diese Zone nach zusätzlichen Ticks. |
| Ergebnis           | – |

---

### MT-06 – Modul: TimerManager (Timer abbrechen)

**Zweck:** Sicherstellen, dass ein abgebrochener Timer nicht mehr abläuft.

| Punkt              | Beschreibung                                                                 |
|--------------------|------------------------------------------------------------------------------|
| Modul              | timer (TimerManager)                                                         |
| Vorbedingung       | `TimerManager` ist initialisiert. Timer für `BACK_LEFT` wurde mit `startTimer(BACK_LEFT, 8)` gestartet. |
| Aktion             | 3× `tick()` ausführen, danach `cancelTimer(BACK_LEFT)` aufrufen und anschließend weitere 10× `tick()` ausführen. |
| Erwartete Reaktion | Während der ersten 3 Ticks wird die Restzeit reduziert. Nach `cancelTimer(...)` tritt **kein** Ablauf-Ereignis für `BACK_LEFT` mehr auf. |
| Nachbedingung      | Timer für `BACK_LEFT` gilt als **inaktiv**; kein Ablauf-Ereignis wurde nach dem Cancel registriert. |
| Ergebnis           | – |

---
