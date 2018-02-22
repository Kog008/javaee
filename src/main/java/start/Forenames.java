package start;

/**
 * I just arranged some randomly chosen names to be able in main app to randomly create objects
 * with random names. For better readability in database i added property "name".
 */
public enum Forenames {

    MARCEL ("Marcel"),
    SABINE ("Sabine"),
    SUSI ("Susi"),
    MARCUS ("Marcus"),
    ACHIM ("Achim"),
    MELANIE ("Melani"),
    MANDY ("Mandy"),
    CHANTALL ("Chantall"),
    LOREDANA ("Loredana"),
    INKONTINETIA ("Inkontinentia"),
    SABRINA ("Sabrina"),
    ANNETTE ("Annette"),
    DOMINIQUE ("Dominique"),
    MADAME ("Madame"),
    JULIA ("Julia"),
    XENA ("Xena");

    private final String name;

    Forenames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

