# Arbeitsprobe
Eine gestellte Arbeitsprobe zur Praktikumsbewerbung
<h2>Arbeitsprobe</h2>
<p>Ein verkürztes Beispiel für einen typischen Fragebogen, wie ihn unsere Kunden einsetzen, z.B. um Bürgerinnen oder Bürger zu befragen, sieht so aus:
</p><hr>
<form>
<h4>Haben Sie Kinder?</h4>
<input name="kinder" value="Ja" type="radio">Ja
<input name="kinder" value="Nein" type="radio">Nein
<h4>Wie viele Kinder haben Sie?</h4>
<input name="kinderzahl" value="1" type="radio">1
<input name="kinderzahl" value="2" type="radio">2
<input name="kinderzahl" value="3" type="radio">3
<input name="kinderzahl" value="4" type="radio">4
<input name="kinderzahl" value="5" type="radio">5
<input name="kinderzahl" value="mehr" type="radio">mehr
<h4>Welche Einrichtungen besuchen Ihre Kinder (mehrere Antworten sind hier möglich)?</h4>
<input name="kindergarten" type="checkbox">Kindergarten
<input name="schule" type="checkbox">Schule
<input name="sportverein" type="checkbox">Sportverein
<h4>Wie zufrieden sind sie mit dem Schulessen in Bezug auf....</h4>
<b>Preis: </b>
<input name="preis" value="1" type="radio">Sehr zufrieden
<input name="preis" value="2" type="radio">Einigermaßen zufrieden
<input name="preis" value="3" type="radio">Unzufrieden
<input name="preis" value="4" type="radio">Sehr unzufrieden
<br><b>Qualität:</b>
<input name="preis" value="1" type="radio">Sehr zufrieden
<input name="preis" value="2" type="radio">Einigermaßen zufrieden
<input name="preis" value="3" type="radio">Unzufrieden
<input name="preis" value="4" type="radio">Sehr unzufrieden
<h4>In welchem Stadtteil wohnen Sie?</h4>
<input name="stadtteil" value="Mitte" type="radio">Mitte
<input name="stadtteil" value="Friedrichshain-Kreuzberg
" type="radio">Friedrichshain-Kreuzberg
<input name="stadtteil" value="Pankow" type="radio">Pankow
<input name="stadtteil" value="Charlottenburg-Wilmersdorf" type="radio">Charlottenburg-Wilmersdorf
<input name="stadtteil" value="Spandau" type="radio">Spandau
<input name="stadtteil" value="Steglitz-Zehlendorf" type="radio">Steglitz-Zehlendorf
<input name="stadtteil" value="Tempelhof-Schöneberg" type="radio">Tempelhof-Schöneberg
<input name="stadtteil" value="Neukölln" type="radio">Neukölln
<input name="stadtteil" value="Treptow-Köpenick" type="radio">Treptow-Köpenick
<input name="stadtteil" value="Marzahn-Hellersdorf" type="radio">Marzahn-Hellersdorf
<input name="stadtteil" value="Lichtenberg" type="radio">Lichtenberg
<input name="stadtteil" value="Reinickendorf" type="radio">Reinickendorf

</form>
<hr>
<br><br><br>
<p>Ein anderer Fragebögen wäre:</p>
<hr>
<h4>Mit welchen Verkehrsmitteln fahren Sie  zur Arbeit (mehrere Antworten möglich)?</h4>
<input name="Fahrrad" type="checkbox">Fahrrad
<input name="Auto" type="checkbox">Auto
<input name="Bus" type="checkbox">Bus
<input name="zu Fuß" type="checkbox">zu Fuß
<input name="U-Bahn" type="checkbox">U-Bahn
<input name="Regionalzug" type="checkbox">Regionalzug
<input name="keinem von diesen" type="checkbox">keinem von diesen
<h4>Welche Fahrscheinart benutzen Sie hauptsächlich, wenn Sie mit öffentlichen Verkehrsmitteln zur Arbeit fahren?</h4>
<input name="fahrschein" value="Monatskarte" type="radio">Monatskarte
<input name="fahrschein" value="4-Fahrten-Karte" type="radio">4-Fahrten-Karte
<input name="fahrschein" value="Einzelkarte" type="radio">Einzelkarte
<input name="fahrschein" value="fahre schwarz" type="radio">fahre schwarz
<input name="fahrschein" value="keine Angabe" type="radio">keine Angabe
<h4>Wie häufig kommen Sie wegen des ÖPNV zu spät auf Arbeit?</h4>
<input name="zuspaet" value="nie" type="radio">nie
<input name="zuspaet" value="fast nie" type="radio">fast nie
<input name="zuspaet" value="gelegentlich" type="radio">gelegentlich
<input name="zuspaet" value="häufig" type="radio">häufig
<input name="zuspaet" value="ständig" type="radio">ständig
<h4>Nutzen Sie dazu ihr eigenes Auto oder einen Dienstwagen?</h4>
<input name="auto" value="eigenes" type="radio">eigenes Auto
<input name="auto" value="dienst" type="radio">Dienstwagen

<hr>
<br>
<p>
Ihre Aufgabe ist es, eine Java-Anwendung zu erstellen, die diese beiden und ähnliche Fragebögen schrittweise anzeigt, die Antworten aufnimmt und das Ergebnis anzeigt.
</p><p>
Konkret sollen Sie erstellen
</p><p>
</p><ul>
<li>Eine menschenlesbare Datenstruktur (z.B. XML, Properties-Datei, JSON, CSV, eigenes Format etc.) die so einen Fragebogen repräsentiert. Dazu eine kurze Dokumentation der Datenstruktur, damit wir selbst einen anderen Fragebogen damit modellieren können.</li>
<li>Ein Java-Programm, das als Swing-Anwendung so einen Fragebogen Frage-für-Frage anzeigt, jeweils eine Antwort wählen lässt und die nächste sinnvolle Frage anzeigt</li>
<li>Eine wichtige Herausforderung ist, dass immer nur die nächste sinnvolle Frage angezeigt werden soll. Wenn also eine Frage aufgrund einer vorhergehenden Frage unsinnig ist, soll sie auch nicht angezeigt werden (z.B. sollte eine Frage wie: "Welche Sehenswürdigkeiten haben Ihnen in Paris besonders gut gefallen?" nicht angezeigt werden, wenn man bereits vorher auf die Frage "Waren Sie schon mal in Paris?" mit nein geantwortet hat).
</li><li>Die Navigation soll dabei jederzeit auch rückwärts möglich sein und dann sinnvoll weitergehen. Man soll also in jedem Schritt zurückgehen können und sich umentscheiden können, entsprechend passen sich die im Folgenden angezeigten Fragen an</li>
<li>Nach Beantwortung der letzten Frage kommt eine Zusammenfassung, in der alle beantworteten Fragen samt Antworten angezeigt werden</li>
<li>Beim Start der Anwendung soll man als Eingabe eine Datei wählen können</li>
</ul>

<p>Liefern Sie bitte eine ZIP-Datei per E-Mail an jobs@blubbsoft.de, die folgendes enthält </p>
<ul>
<li>Den Quellcode des Programms</li>
<li>Die obigen Fragebögen als Eingabedatei</li>
<li>Die Dokumentation der Datenstruktur</li>
<li>Ein ANT-Build-Skript, um den Quellcode zu compilieren (ant compile) und auszuführen (ant run)</li>
<li>Etwaige Bibliotheken, damit es in einer normalen Java-Umgebung (Ant ist bereits installiert) compiliert und ausgeführt wird.</li>
</ul>
<p>Mehrere Einsendungen sind trotzdem erlaubt. Es wird die Einsendung berücksichtigt, die bei der Prüfung durch uns die aktuellste war.</p>
<p>Setzen Sie sich für diese Aufgabe ein Zeitlimit von drei Stunden und senden Sie ein, was Sie in dieser Zeit schaffen.</p>
