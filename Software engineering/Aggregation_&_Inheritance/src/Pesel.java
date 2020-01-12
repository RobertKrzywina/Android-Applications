import java.time.LocalDate;

class Pesel {

    private String number;

    public Pesel(String id) {

        this.number = id;
    }

    public void setNumber(String number) {

        this.number = number;
    }

    @Override
    public String toString() {

        return "Pesel{" +
                "number='" + number + '\'' +
                '}';
    }

    public boolean isPeselNumberValid(int age) {

        return isLengthValid() && isSexValid() && isAgeValid(age);
    }

    private boolean isLengthValid() {

        return number.length() == 11;
    }

    private boolean isSexValid() {

        int sexNumber = Character.getNumericValue(number.charAt(9));
        return sexNumber >= 0 && sexNumber < 10;
    }

    private boolean isAgeValid(int age) {

        int thirdDigit = Character.getNumericValue(number.charAt(2));
        int year = Integer.parseInt(number.substring(0, 2));
        switch (thirdDigit) {

            case 0:
            case 1: {
                year += 1900;
                break;
            }
            case 2:
            case 3: {
                year += 2000;
                break;
            }
            case 4:
            case 5: {
                year += 2100;
                break;
            }
            case 6:
            case 7: {
                year += 2200;
                break;
            }
            case 8:
            case 9: {
                year += 1800;
                break;
            }
        }
        int currentYear = LocalDate.now().getYear();
        return currentYear - year == age;
    }
}
