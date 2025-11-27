# Requirement Engineering

## Stakeholder-Analyse

**Zentrale Stakeholder:** Endnutzer / Anwender des Kochfelds

Da das Projekt nicht im Auftrag eines Unternehmens entsteht, sondern zu Studienzwecken umgesetzt wird,  
gibt es keine externen Auftraggeber oder wirtschaftlichen Interessen.  
Der Fokus liegt daher vollständig auf den Endnutzern, die das Kochfeld später bedienen.  
Ihre Erwartungen an Bedienkomfort, Sicherheit und Zuverlässigkeit bilden die Grundlage für die Anforderungsdefinition.

---

## Lasten- und Pflichtenheft

[Lastenheft](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/referenziert/Lastenheft.md): Steuerung und Bedienkonzept eines Induktionskochfelds  
[Pflichtenheft](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/referenziert/Pflichtenheft.md): Umsetzung der Kochfeldsteuerung auf Softwareebene  

Beide Dokumente dienen als Orientierungshilfe, um die Anforderungen aus Sicht des Nutzers und aus Sicht der Entwicklung zu strukturieren.  
Das Lastenheft beschreibt, **was** das System leisten soll, während das Pflichtenheft festlegt, **wie** diese Anforderungen technisch umgesetzt werden.  
Die vollständigen Fassungen werden später separat ausgearbeitet und an dieser Stelle verlinkt.

---

## Use Cases

Die Use Cases konzentrieren sich auf die Interaktion zwischen Benutzer und System.  
Im Mittelpunkt steht die Bedienung über Touch-Eingaben, die Auswahl von Kochzonen, das Einstellen von Leistungsstufen  
sowie die Rückmeldung des Systems über Anzeigen und Signaltöne.  
Ziel ist ein sicherer und intuitiver Ablauf, bei dem der Benutzer stets über den aktuellen Zustand informiert ist.

---

## Teilfunktionalitäten

Aus den bisherigen Analysen und den identifizierten Use Cases ergeben sich folgende Hauptfunktionen des Systems:

1. **Kochzonenaktivierung und -auswahl**  
   Aktivieren und Deaktivieren einzelner Kochzonen über die Touch-Bedienoberfläche.

2. **Leistungsstufeneinstellung**  
   Anpassung der Heizleistung jeder aktiven Kochzone über Plus-/Minus-Tasten.

3. **Benutzer-Feedback über Display**  
   Anzeige von aktiven Zonen, Leistungsstufen, Timer und Restwärme auf dem Display.

4. **Timer- und Abschaltlogik**  
   Zeitsteuerung pro Kochzone mit automatischer Abschaltung nach Ablauf und Feedbacksignal.

5. **Sicherheits- und Sperrfunktionen**  
   Kindersicherung, Erkennung von Fehlbedienungen und visuelle Warnhinweise zur Erhöhung der Betriebssicherheit.

---

## Requirements

Aus der bisherigen Analyse wurden funktionale und nicht-funktionale Anforderungen abgeleitet.  
Diese beschreiben sowohl das Verhalten des Systems als auch Qualitätsmerkmale wie Reaktionszeit, Zuverlässigkeit und Sicherheit.  
Die vollständige Anforderungsliste mit Priorisierung und Zuordnung zu den Teilfunktionen ist im folgenden Dokument zu finden:

[Requirements](https://github.com/andrefuchs3/Software-Engineering-Induktionskochfeld-Kochfeldsteuerung/blob/main/docs/Requirements.md)

