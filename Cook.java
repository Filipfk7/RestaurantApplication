

public class Cook  implements IEmployee{
    String name;
    String surname;
    int phoneNumber;
    int id;
    double timeForPreparingOneMeal = 0.5;

    public Cook(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;

    }


    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPositionName() {
        return null;
    }

    @Override
    public int getPositionId() {
        return id;
    }

    @Override
    public int getPhoneNumber() {
        return 0;
    }

    @Override
    public String getClientName() {
        return null;
    }

    @Override
    public int getBonus() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
