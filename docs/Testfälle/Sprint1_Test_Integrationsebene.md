## Testfälle - Integrationsebene (Sprint 1)


### **IT-01 – HmiInput ↔ CooktopController (Zone aktivieren)**
**Ziel:** Prüfen, ob der Aufruf von `HmiInput` korrekt an den `CooktopController` weitergegeben wird.

|  | Beschreibung |
|-------|--------------|
| **Komponenten** | HmiInput, CooktopController |
| **Vorbedingung** | Ein `CooktopController` ist erstellt und an ein `HmiInput`-Objekt übergeben. Die Zone `FRONT_LEFT` ist zu Beginn inaktiv. |
| **Aktion** | Aufruf von `hmiInput.selectZone(ZoneID.FRONT_LEFT, true)` |
| **Erwartete Reaktion** | `HmiInput` ruft intern `cooktopController.setZoneActive(ZoneID.FRONT_LEFT, true)` auf. Die Zone wird im Controller aktiv gesetzt. |
| **Nachbedingung** | `ZoneManager.isActive(ZoneID.FRONT_LEFT)` meldet **aktiv** |
| **Ergebnis** | Bestanden - Ausgabe in Konsole bestätigt Aktivierung |

---

### **IT-02 – CooktopController ↔ PowerControl (Leistungsstufe erhöhen)**
**Ziel:** Prüfen, ob der Controller die Leistung korrekt über `PowerControl` ändert.

|  | Beschreibung |
|-------|--------------|
| **Komponenten** | CooktopController, PowerControl |
| **Vorbedingung** | `CooktopController` ist instanziiert und besitzt ein internes `PowerControl`. Zone `FRONT_LEFT` ist aktiv, Leistungsstufe = 0. |
| **Aktion** | Aufruf von `cooktopController.increasePower(ZoneID.FRONT_LEFT)` |
| **Erwartete Reaktion** | Der Controller ruft intern `powerControl.increaseLevel(ZoneID.FRONT_LEFT)` auf. Leistungsstufe steigt auf **1**. |
| **Nachbedingung** | `powerControl.getLevel(ZoneID.FRONT_LEFT)` liefert **1** |
| **Ergebnis** | Bestanden - Anzeige zeigt korrekt "Leistungsstufe 1" |

---

### **IT-03 – CooktopController ↔ SafetyManager (Kindersicherung blockiert Änderungen)**
**Ziel:** Prüfen, ob der Controller die Kindersicherung über `SafetyManager` korrekt abfragt und Aktionen blockiert.

|  | Beschreibung |
|-------|--------------|
| **Komponenten** | CooktopController, SafetyManager |
| **Vorbedingung** | `CooktopController` ist instanziiert. `SafetyManager.getInstance()` existiert. Zone `FRONT_LEFT` aktiv, Leistungsstufe = 0. Kindersicherung = aus. |
| **Aktion** | 1. `cooktopController.toggleChildLock()` → ruft `SafetyManager.lockInput()` auf. <br>2. Danach `cooktopController.increasePower(ZoneID.FRONT_LEFT)` |
| **Erwartete Reaktion** | 1. `toggleChildLock()` aktiviert Sperre über `lockInput()`. <br>2. Beim Erhöhen prüft der Controller `isLocked()` und **bricht ab** – kein `increaseLevel()` mehr. |
| **Nachbedingung** | `SafetyManager.isLocked()` ist **true**. Leistungsstufe bleibt **0**. |
| **Ergebnis** | Bestanden - Konsole zeigt Blockierung + Fehlermeldung |

