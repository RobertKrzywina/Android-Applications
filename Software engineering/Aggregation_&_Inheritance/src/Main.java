import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Arrays.asList(
                new User("John", "Doe", "22-100 Chelm", new Pesel("70010118910"), LocalDate.of(1970, Month.JANUARY, 1), Sex.MALE),
                new Worker("Tim", "A", "22-101 London", new Pesel("97010187412"), LocalDate.of(1997, Month.JANUARY, 1), Sex.MALE),
                new Guest("Kate", "B", "22-102 Paris", new Pesel("99010217108"), LocalDate.of(1999, Month.JANUARY, 2), Sex.FEMALE),
                new Admin("Sara", "C", "22-103 Tokyo", new Pesel("01210302946"), LocalDate.of(2001, Month.JANUARY, 3), Sex.FEMALE))
                .forEach(u -> {
                            u.show();
                            System.out.println("Is pesel number valid? -> " + u.getPesel().isPeselNumberValid(u.getSex(), u.getAge()));
                            if (u instanceof Worker) {
                                System.out.print("Worker etat -> ");
                                ((Worker) u).showEtat();
                            } else if (u instanceof Guest) {
                                System.out.println("Guest pesel -> " + u.getPesel());
                            } else if (u instanceof Admin) {
                                System.out.println("Admin secret number -> " + ((Admin) u).getSecretNumber());
                            }
                            System.out.println();
                        }
                );
    }
}
