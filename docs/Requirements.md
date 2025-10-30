# Requirements – Kochfeldsteuerung

### 1. Funktionale Requirements

#### Kochzonenaktivierung und -auswahl

| Req.-Nr. | Requirement |
|-----------|--------------|
| F1.1 | Das System muss ermöglichen, einzelne Kochzonen über die Toucheingabe zu aktivieren oder zu deaktivieren. |
| F1.2 | Das System muss eindeutig anzeigen, welche Kochzonen aktiv sind. |

#### Leistungsstufeneinstellung

| Req.-Nr. | Requirement |
|-----------|--------------|
| F2.1 | Das System muss für jede aktive Kochzone neun Leistungsstufen anbieten. |
| F2.2 | Die Leistungsstufe muss durch Toucheingaben (Plus-/Minus-Tasten) einstellbar sein. |
| F2.3 | Änderungen der Leistungsstufe müssen innerhalb von 200 ms nach der Eingabe umgesetzt und angezeigt werden. |

#### Benutzer-Feedback über ein Display

| Req.-Nr. | Requirement |
|-----------|--------------|
| F3.1 | Das System muss den Status jeder Kochzone (aus, aktiv, Restwärme) visuell anzeigen. |
| F3.2 | Die aktuelle Leistungsstufe muss jederzeit ablesbar sein. |
| F3.3 | Fehlermeldungen oder Sperrzustände müssen eindeutig durch ein passendes Symbol dargestellt werden. |

#### Timer- und Abschaltlogik

| Req.-Nr. | Requirement |
|-----------|--------------|
| F4.1 | Das System muss für jede Kochzone eine Timerfunktion bereitstellen. |
| F4.2 | Nach Ablauf des Timers muss die betroffene Kochzone automatisch deaktiviert werden. |
| F4.3 | Nach Ablauf des Timers muss eine visuelle und akustische Rückmeldung erfolgen. |
| F4.4 | Der Timer muss während des Betriebs jederzeit geändert oder abgebrochen werden können. |

#### Sicherheits- und Sperrfunktionen

| Req.-Nr. | Requirement |
|-----------|--------------|
| F5.1 | Das System muss eine Kindersicherung bereitstellen, die alle Eingaben sperrt. |
| F5.2 | Das System muss Fehlbedienungen erkennen und durch eine visuelle Warnung anzeigen. |





### 2. Nicht-funktionale Requirements

| Req.-Nr. | Kategorie | Beschreibung |
|-----------|------------|---------------|
| NF1 | Reaktionszeit | Das System muss auf Benutzereingaben innerhalb von 200 ms reagieren. |
| NF2 | Zuverlässigkeit | Die Steuerung darf keine ungewollte Aktivierung oder Deaktivierung von Kochzonen verursachen. |
| NF3 | Sicherheit | Das System muss gegen unbeabsichtigte oder parallele Eingaben geschützt sein (z. B. durch Entprellung oder Sperrlogik). |
| NF4 | Benutzerfreundlichkeit | Die Anzeigeelemente müssen aus einem Abstand von mindestens 50 cm gut lesbar sein. |
| NF5 | Stromverbrauch | Der Energieverbrauch im Standby darf 1 W nicht überschreiten. |
| NF6 | Normenkonformität | Das System muss die einschlägigen Sicherheits- und EMV-Normen für Haushaltsgeräte erfüllen (z. B. IEC 60335). |
| NF7 | Robustheit | Die Touchoberfläche muss mindestens 100 000 Betätigungen ohne Funktionsverlust standhalten. |


---
