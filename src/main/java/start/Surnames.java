package start;

public enum Surnames {
    SCHUSTER ("Schuster"),
    MAURER ("Maurer"),
    SCHMIDT ("Schmidt"),
    MUELLER ("Müller"),
    MEIER ("Meier"),
    SCHNEIDER ("Schneider"),
    ROEMER ("Römer"),
    MAHLER ("Mahler"),
    HOLZ ("Holz"),
    BAUM ("Baum"),
    SIEGFRIED ("Siegfried"),
    HASSERÖDER ("Hasseröder"),
    HERFORDER ("Herforder"),
    HANNOVER ("Hannover"),
    BÄR ("Bär"),
    JÄGER ("Jäger"),
    BÖTTCHER ("Böttcher"),
    RADEMACHER ("Rademacher"),
    RADEBERGER ("Radeberger");

    private final String name;

    Surnames(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return name;
    }
}
