import java.time.LocalDate;
import java.util.Random;

class Admin extends User {

    private int secretNumber;

    public Admin(String name, String surname, String address, Pesel pesel, LocalDate birthday, Sex sex) {

        super(name, surname, address, pesel, birthday, sex);
        this.secretNumber = generateSecretNumber();
    }

    private int generateSecretNumber() {

        return new Random().nextInt(10) + 1;
    }

    public int getSecretNumber() {

        return secretNumber;
    }
}
