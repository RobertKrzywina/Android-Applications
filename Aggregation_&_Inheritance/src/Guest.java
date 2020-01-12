import java.time.LocalDate;

class Guest extends User {

    public Guest(String name, String surname, String address, Pesel pesel, LocalDate birthday, Sex sex) {

        super(name, surname, address, pesel, birthday, sex);
    }

    @Override
    public Pesel getPesel() {

        Pesel pesel = super.getPesel();
        pesel.setNumber("0");
        return pesel;
    }
}
