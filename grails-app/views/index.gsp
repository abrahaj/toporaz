<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to TOPORAZ</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                    <li class="controller">
                        <g:link controller="${c.logicalPropertyName}">&rtrif; ${c.name}</g:link>
                    </li>
                </g:each>
            </ul>
        </li>
    </content>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Toporaz</h1>
            <div >
            <p><img src="http://www.toporaz.de/assets/Block_Rathaus_0001_Layer%204.jpg" alt="Rathaus" height="471" width="1024"></p>
            <p style="font-size: xx-small;">Source: <em>Fachgebiet Informations- und Kommunikationstechnologie in der Architektur, TU Darmstadt</em></p>
            <p>TOPORAZ zielt auf die Konzeption und den Aufbau einer neuartigen virtuelle Forschungsumgebung (VFU) für die objekt- und raumbezogene geisteswissenschaftliche Forschung. Exemplarisch dargestellt wird das topografische Erscheinungsbild eines Stadtviertels im historischen Nürnberg in drei Zeitebenen (Frühe Neuzeit, 1939 und Gegenwart), bestehend aus georeferenzierten 2D-Karten und 3D-Modellen, die als Navigationsstruktur dienen. Der innovative Ansatz liegt in der Koppelung des interaktiven Stadtmodells mit Forschungsliteratur und Quellmaterialien (Text, Bild, Ton), die den 3D-Objekten des Modells mittels Verknüpfungspunkten (Hotspots) direkt zugeordnet werden. Die VFU dient als Plattform für die Zusammenführung von äußerst heterogenen Forschungsdaten. Sie unterstützt übergreifende Forschungsansätze und vernetztes, transdisziplinäres Arbeiten. Zudem dient sie als Open-Access-Publikationsplattform, die einen direkten Zugang zu den Forschungsdaten und -ergebnissen im Sinne von Open Science ermöglicht.</p>
            <p>TOPORAZ wird im Rahmen des Leibniz-Wettbewerbs von der Leibniz-Gemeinschaft über drei Jahre gefördert (2015-2018). Projektpartner sind</p>
            <ul>
                <li>Universität Greifswald<br>Caspar-David-Friedrich-Institut<br>Lehrstuhl Kunstgeschichte (Prof. Weilandt)</li>
                <li>Universität zu Köln<br>Kunsthistorisches Institut<br>Abteilung Architekturgeschichte (Prof. Nußbaum)</li>
                <li>Technische Universität Darmstadt<br>Fachgebiet Informations- und Kommunikationstechnologie in der Architektur (Dr. Grellert)</li>
                <li>FIZ Karlsruhe – Leibniz-Institut für Informationsinfrastruktur<br>IT, Entwicklung und Angewandte Forschung<br>e-Science (Razum)</li>
                <li>Bayerische Staatsbibliothek (assoziierter Partner)</li>
            </ul>
            </div>

        </section>
    </div>

</body>
</html>
