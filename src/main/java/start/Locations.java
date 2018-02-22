package start;

public enum Locations {
    PRAG ("Prag"),
    KÖLN ("Köln"),
    NÜRNBERG ("Nürnberg"),
    AUGSBURG ("Augsburg"),
    MAGDEBURG ("Magdeburg"),
    DANZIG ("Danzig"),
    LÜBECK ("Lübeck"),
    BRESLAU ("Breslau"),
    REGENSBURG ("Regensburg"),
    WIEN ("Wien"),
    STRASSBURG ("Straßburg"),
    BREMEN ("Bremen"),
    BERLIN ("Berlin"),
    MÜNCHEN ("München"),
    DRESDEN ("Dresden"),
    MAINZ ("Mainz"),
    HANNOVER ("Hannover"),
    BRÜSSEL ("Brüssel"),
    GENT ("Gent"),
    WARSCHAU ("Warschau"),
    PARIS ("Paris"),
    LEIPZIG ("Leipzig");

    private final String name;

    Locations(String name) {
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
