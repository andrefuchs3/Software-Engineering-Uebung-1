# Requirements – Kochfeldsteuerung

## 1. Teilfunktionalitäten

| Nr. | Teilfunktionalität | Beschreibung |
|-----|--------------------|---------------|
| 1 | Kochzonenaktivierung und -auswahl | Der Benutzer kann über die Toucheingabe eine oder mehrere Kochzonen aktivieren und deaktivieren. |
| 2 | Leistungsstufeneinstellung | Die Heizleistung jeder aktiven Kochzone kann über Toucheingaben (Plus-/Minus-Tasten) eingestellt werden. |
| 3 | Benutzer-Feedback über Display | Das Kochfeld zeigt aktive Kochzonen, Leistungsstufen, Timer und Restwärme über Anzeigen an. |
| 4 | Timer- und Abschaltlogik | Für jede Kochzone kann eine jeweilige Zeit eingestellt werden; nach Ablauf erfolgt eine automatische Abschaltung. |
| 5 | Sicherheits- und Sperrfunktionen | Das Kochfeld verfügt über eine Tastensperre und erkennt Fehlbedienungen zur Erhöhung der Sicherheit. |


---

## 2. Requirements

### 2.1 Funktionale Requirements

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


---

### 2.2 Nicht-funktionale Requirements

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

## 3. Passagen für das Pflichtenheft

| Teilfunktion | Beschreibung |
|---------------|--------------|
| Kochzonenaktivierung und -auswahl | Das System implementiert eine Toucheingabe, über die einzelne Kochzonen aktiviert und deaktiviert werden können. Jede Aktivierung wird durch eine visuelle Anzeige bestätigt. |
| Leistungsstufeneinstellung | Für jede aktivierte Kochzone werden neun Leistungsstufen zur Verfügung gestellt. Die Einstellung erfolgt über Toucheingaben (Plus-/Minus-Tasten). Die gewählte Leistungsstufe wird direkt übernommen und in der Anzeige aktualisiert. |
| Benutzer-Feedback über Display | Das System stellt visuelles Feedback über den aktuellen Status jeder Kochzone bereit. Folgende Zustände werden angezeigt: aus, aktiv, Restwärme und Fehler. Leistungsstufen, Timer und Fehlermeldungen werden durch Symbole und Zahlen dargestellt. |
| Timer- und Abschaltlogik | Für jede Kochzone ist ein separater Timer implementiert, der eine gewünschte Kochdauer ermöglicht. Nach Ablauf des Timers wird die entsprechende Kochzone automatisch deaktiviert. Der Benutzer kann die Timerwerte während des Betriebs ändern oder abbrechen. |
| Sicherheits- und Sperrfunktionen | Das System enthält eine Kindersicherungsfunktion, die alle Eingaben sperrt, sobald sie aktiviert wird. Fehlbedienungen oder unerlaubte Eingaben werden erkannt und über eine Warnanzeige signalisiert. |


---

## 4. Passagen für das Lastenheft

| Teilfunktion | Beschreibung |
|---------------|--------------|
| Kochzonenaktivierung und -auswahl | Der Benutzer soll über die Toucheingabe einzelne Kochzonen aktivieren und deaktivieren können. Das System soll eindeutig anzeigen, welche Kochzonen aktuell in Betrieb sind. |
| Leistungsstufeneinstellung | Für jede aktive Kochzone soll der Benutzer eine Leistungsstufe einstellen können. Die Bedienung soll über Toucheingaben (Plus-/Minus-Tasten) erfolgen. Die eingestellte Leistungsstufe soll sofort wirksam und auf der Anzeige sichtbar sein. |
| Benutzer-Feedback über Display | Das Kochfeld soll über Anzeigen verfügen, die den aktuellen Zustand jeder Kochzone darstellen. Es sollen Zustände wie aktiv, aus, Restwärme oder Fehler erkennbar sein. Benutzerinformationen wie Timer oder Leistungsstufen sollen gut lesbar dargestellt werden. |
| Timer- und Abschaltlogik | Der Benutzer soll für jede Kochzone eine Zeit einstellen können, nach deren Ablauf die Zone automatisch abgeschaltet wird. Der Timer soll während des Betriebs veränderbar und manuell deaktivierbar sein. Nach Ablauf soll eine visuelle und akustische Rückmeldung erfolgen. |
| Sicherheits- und Sperrfunktionen | Das Kochfeld soll über eine Kindersicherung verfügen, die versehentliche oder unbefugte Bedienung verhindert. Fehlbedienungen sollen erkannt und dem Benutzer über eine Warnanzeige mitgeteilt werden. |

