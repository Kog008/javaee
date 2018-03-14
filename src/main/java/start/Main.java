package start;

import static infrastructure.CustomerRepository.persistRandomCustomers;

public class Main {

    /**
     * In projects current state main method is not runnable, because hibernate dependencies are toggled to
     * provided.
     *
     * Use main for exercise purposes, or like in this case for randomly generated customer objects.
     *
     * @param args      runtime parameters; have to be set in run configuration under program arguments
     */
    public static void main(String[] args) {

        persistRandomCustomers(100);

    }
}
