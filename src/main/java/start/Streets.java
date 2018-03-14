package start;

public enum Streets {
    BADSTRASSE ("Badstraße"),
    TURMSTRASSE ("Turmstraße"),
    SÜDBAHNHOF ("Südbahnhof"),
    CHAUSSEESTRASSE ("Chausseestraße"),
    ELISENSTRASSE ("Elisenstraße"),
    POSTSTRASSE ("Poststraße"),
    SEESTRASSE ("Seestraße"),
    ELEKTRIZITÄTSWERK ("Elektrizitätswerk"),
    HAFENSTRASSE ("Hafenstraße"),
    NEUE_STRASSE ("Neue Straße"),
    WESTBAHNHOF ("Westbahnhof"),
    MÜNCHNER_STRASSE ("Müncher Straße"),
    WIENER_STRASSE ("Wiener Straße"),
    BERLINER_STRASSE ("Berliner Straße"),
    THEATERSTRASSE ("Theaterstraße"),
    MUSEUMSSTRASSE ("Museumsstraße"),
    OPERNPLATZ ("Operplatz"),
    NORDBAHNHOF ("Nordbahnhof"),
    LESSINGSTRASSE ("Lessingstraße"),
    SCHILLERSTRASSE ("Schillerstraße"),
    WASSERWERK ("Wasserwerk"),
    GOETHESTRASSE ("Goethestraße"),
    RATHAUSPLATZ ("Rathausplatz"),
    HAUPTSTRASSE ("Hauptstraße"),
    BAHNHOFSTRASSE ("Bahnhofstraße"),
    HAUPTBAHNHOF ("Hauptbahnhof"),
    PARKSTRASSE ("Parstraße"),
    SCHLOSSALLEE ("Schlossallee");

    private final String name;

    Streets(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
