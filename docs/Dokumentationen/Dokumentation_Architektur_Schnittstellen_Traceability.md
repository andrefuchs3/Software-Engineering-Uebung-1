# Dokumentation – Architektur, Schnittstellen und Traceability

Diese Dokumentation beschreibt den aktuellen Stand der Softwarearchitektur, der Schnittstellen und der Anforderungsnachverfolgung (Traceability) für das Projekt **Kochfeldsteuerung**.  
Sie dient als zentrale Grundlage für die Weiterentwicklung der Software und soll sicherstellen, dass alle Entwickler die Struktur, Verantwortlichkeiten und Zusammenhänge des Systems nachvollziehen können.

---

## Systemarchitektur

Die Softwarearchitektur der Kochfeldsteuerung basiert auf einem **schichtenbasierten Aufbau**, der die Bereiche **Eingabe**, **Steuerung**, **Anzeige** und **Hardwareanbindung** klar voneinander trennt.  
Diese Struktur ermöglicht eine modulare Entwicklung, bei der einzelne Komponenten unabhängig voneinander angepasst oder erweitert werden können.

Die oberste Schicht bildet die **Benutzerschnittstelle (Human-Machine Interface, HMI)**.  
Sie setzt sich aus zwei logischen Teilbereichen zusammen: der **Eingabeeinheit** und der **Anzeigeeinheit**.  
Die Eingabeeinheit erfasst Benutzereingaben über Touch-Elemente, beispielsweise zur Aktivierung von Kochzonen, Anpassung der Leistungsstufen oder Aktivierung der Kindersicherung.  
Die erfassten Eingaben werden an die **Kochfeldlogik** weitergeleitet, die als zentrale Steuerinstanz des Systems fungiert.

Die **Kochfeldlogik** verarbeitet sämtliche Eingaben, überwacht sicherheitsrelevante Zustände und steuert die zugehörigen Komponenten.  
Sie koordiniert die **Leistungsregelung**, die **Zonenverwaltung** und die **Anzeige**, berechnet Sollwerte und verwaltet alle Zustände des Systems.  
Die **Leistungsregelung** ist in die Logik integriert und übernimmt die Berechnung der Heizleistung für jede aktive Kochzone.  
Über die **Hardware-Schnittstelle** werden diese Sollwerte an die physischen Heizelemente weitergegeben.  
Sensoren liefern im Gegenzug Messwerte wie Temperatur, Stromaufnahme und Topferkennung, die zur Überwachung und Regelung genutzt werden.

Das **Sicherheitssystem** und die **Energieverwaltung** wirken als querschnittliche Funktionen auf das gesamte System.  
Das Sicherheitssystem überwacht die Eingaben und verhindert unzulässige Aktionen, zum Beispiel durch die Aktivierung der Kindersicherung oder die Erkennung von Fehlbedienungen.  
Die Energieverwaltung stellt sicher, dass die Leistungsaufnahme im Standby-Betrieb unter einem Watt bleibt und koordiniert den Übergang in stromsparende Zustände.

Die Architektur ist so aufgebaut, dass sie als Grundlage für die objektorientierte Umsetzung dient.  
Entwickler können die beschriebenen Systemkomponenten direkt als Klassen oder Module abbilden und ihre Funktionalitäten in den jeweiligen Bereichen erweitern oder verfeinern.

---

### Architekturdiagramm

                     +----------------------+
                     |       Benutzer       |
                     |   (Touch-Eingabe)    |
                     +----------+-----------+
                                |
                                v
      +------------------------------------------------+
      |      Benutzerschnittstelle (HMI)               |
      |  +--------------------+   +------------------+ |
      |  |   Eingabeeinheit   |   |   Anzeigeeinheit | |
      |  |  (Touch-Steuerung) |   | (Display/Buzzer) | |
      |  +--------------------+   +------------------+ |
      +----------------------------+-------------------+
                                   |
                                   v
      +------------------------------------------------+
      |                Kochfeldlogik                   |
      | (Steuer- & Ablauflogik, Leistungsregelung)     |
      |  - Zonenverwaltung                             |
      |  - Leistungsstufenberechnung                   |
      |  - Timer- und Sicherheitsprüfung               |
      +----------------------------+-------------------+
                                   |
                     Statusdaten / Steuerbefehle
                                   v
      +-------------------------------------------------+
      |             Hardware-Schnittstelle              |
      | (Treiber & Kommunikation zu physischen Modulen) |
      |  - Heizelemente (Aktoren)                       |
      |  - Sensoren (Temperatur, Strom, Topferkennung)  |
      |  - Buzzer / Anzeigeelemente                     |
      +-------------------------------------------------+

      ┌────────────────────────────────────────────────────┐
      │ Querschnittsfunktionen: Sicherheit & Energie       │
      │ (Kindersicherung, Fehlbedienungsschutz,            │
      │  Standby-Steuerung, Leistungsbegrenzung ≤ 1 W)     │
      └────────────────────────────────────────────────────┘


---

## Schnittstellen

Die Kommunikation zwischen den Systemkomponenten erfolgt über klar definierte Schnittstellen.  
Jede Schnittstelle legt fest, welche Art von Daten übertragen wird, in welcher Richtung die Kommunikation erfolgt und welche Bedeutung die Informationen haben.

- **Bedienoberfläche ↔ Kochfeldlogik**  
  *Art:* Daten- und Steuerschnittstelle  
  Überträgt Benutzereingaben, Zustandsänderungen und Rückmeldungen.  
  Die Kommunikation ist bidirektional.

- **Kochfeldlogik ↔ Anzeige & Feedback**  
  *Art:* Informationsschnittstelle  
  Überträgt Statusinformationen, Leistungsstufen, Timerwerte und Fehlermeldungen.  
  Kommunikation verläuft von der Logik zur Anzeigeeinheit.

- **Kochfeldlogik ↔ Zeitsteuerung**  
  *Art:* Ereignisschnittstelle  
  Steuert Timerfunktionen (Start, Änderung, Ablauf) und empfängt Ereignisse bei Ablauf.

- **Kochfeldlogik ↔ Leistungsregelung**  
  *Art:* Sollwertschnittstelle  
  Übergibt berechnete Leistungsstufen an die Heizsteuerung und erhält Statusrückmeldungen.

- **Hardware-Schnittstelle ↔ Sensoren**  
  *Art:* Sensorschnittstelle  
  Liefert physikalische Messwerte wie Temperatur, Stromaufnahme und Topferkennung an die Logik.

- **Kochfeldlogik ↔ Sicherheitssystem**  
  *Art:* Kontrollschnittstelle  
  Überwacht Sperrzustände und Fehlbedienungen und übermittelt entsprechende Sperr- oder Warnsignale.

- **Kochfeldlogik ↔ Energieverwaltung**  
  *Art:* Managementschnittstelle  
  Regelt Energieverbrauch, Standby-Zustände und Leistungsbegrenzungen.

- **Kochfeldlogik ↔ Akustisches Feedback (Buzzer)**  
  *Art:* Signalausgabeschnittstelle  
  Steuert akustische Signale, etwa bei Timerende oder Fehlermeldungen.

Diese Schnittstellenbeschreibung bildet die Grundlage für eine saubere Umsetzung der Interaktion zwischen den Komponenten.  
Sie dient Entwicklern als Referenz, um bestehende Verbindungen zu verstehen und neue Funktionen konsistent zu integrieren.

---

## Traceability

Die **Traceability-Matrix** verknüpft die funktionalen und nicht-funktionalen Anforderungen mit den Systemkomponenten und den zugehörigen Testfällen.  
Sie stellt sicher, dass jede Anforderung umgesetzt und überprüft werden kann und keine Funktionalität unbeachtet bleibt.

Jede Anforderung ist eindeutig einer oder mehreren Komponenten zugeordnet:  
Beispielsweise sind die Anforderungen zur Kochzonenaktivierung und Leistungsstufeneinstellung der **Bedienoberfläche** und der **Kochfeldlogik** zugewiesen.  
Die Reaktionszeit-Anforderung ist mit der **Anzeigeeinheit** und der **Leistungsregelung** verknüpft, während die Kindersicherung über das **Sicherheitssystem** realisiert wird.

Entwickler können die Traceability-Matrix verwenden, um direkt zu erkennen, wo Anforderungen implementiert wurden, welche Komponenten davon betroffen sind und durch welche Testfälle ihre Funktionalität überprüft wird.  
Die Matrix wird fortlaufend gepflegt, um Änderungen an Anforderungen oder Architektur sofort nachvollziehbar zu machen.
