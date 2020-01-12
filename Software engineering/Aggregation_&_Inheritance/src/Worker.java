import java.time.LocalDate;

class Worker extends User {

    public Worker(String name, String surname, String address, Pesel pesel, LocalDate birthday, Sex sex) {

        super(name, surname, address, pesel, birthday, sex);
    }

    public void showEtat() {

        System.out.println((int) (Math.random() * ((1.0 - 0.1) + 0)) + 0.1);
    }
}
