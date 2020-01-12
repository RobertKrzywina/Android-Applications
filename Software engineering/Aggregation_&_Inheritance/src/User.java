import java.time.LocalDate;
import java.time.Period;

class User {

    private String name;
    private String surname;
    private String address;
    private Pesel pesel;
    private LocalDate birthday;
    private Sex sex;
    private int age;

    public User(String name, String surname, String address, Pesel pesel, LocalDate birthday, Sex sex) {

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.pesel = pesel;
        this.birthday = birthday;
        this.sex = sex;
        this.age = generateAge();
    }

    private int generateAge() {

        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public int getAge() {

        return age;
    }

    public Pesel getPesel() {

        return pesel;
    }

    public Sex getSex() {

        return sex;
    }

    @Override
    public String toString() {

        return getClass() + "{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", pesel=" + pesel +
                ", birthday=" + birthday +
                ", sex='" + sex.name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show() {

        System.out.println(toString());
    }
}
