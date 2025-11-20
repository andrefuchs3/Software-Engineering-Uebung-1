## Modultests (Unit Tests auf Modulebene)


### **MT-01 – Modul: power (PowerControl)**
**Zweck:** Algorithmische Korrektheit der Leistungsstufen-Logik  

| Punkt | Beschreibung |
|-------|---------------|
| **Modul** | power |
| **Vorbedingung** | Modul *power* ist initialisierbar; `PowerControl` erstellt; Leistungsstufe von `FRONT_LEFT = 5` |
| **Aktion** | `increaseLevel(ZoneID.FRONT_LEFT)` |
| **Erwartete Reaktion** | Stufe steigt auf **6** |
| **Nachbedingung** | `getLevel(FRONT_LEFT)` liefert **6** |
| **Ergebnis** |  -  |

---

### **MT-02 – Modul: safety (SafetyManager)**
**Zweck:** Korrektes Sperren/Entsperren im Singleton-Modul  

| Punkt | Beschreibung |
|-------|---------------|
| **Modul** | safety |
| **Vorbedingung** | `SafetyManager.getInstance()` existiert; Kindersicherung deaktiviert |
| **Aktion** | `lockInput()` danach `isLocked()` |
| **Erwartete Reaktion** | Rückgabe = **true** |
| **Nachbedingung** | Sperre aktiv; später durch `unlockInput()` wieder deaktivierbar |
| **Ergebnis** |  -  |

---

### **MT-03 – Modul: util (Types / Enums)**
**Zweck:** Korrekte Auflistung und Existenz aller `ZoneID`-Werte  

| Punkt | Beschreibung |
|-------|---------------|
| **Modul** | util |
| **Vorbedingung** | `Types.ZoneID.values()` existieren |
| **Aktion** | Abfrage aller Enum-Werte |
| **Erwartete Reaktion** | Werte: `FRONT_LEFT`, `FRONT_RIGHT`, `BACK_LEFT`, `BACK_RIGHT` |
| **Nachbedingung** | Enum unverändert und valide |
| **Ergebnis** |  -  |

---

