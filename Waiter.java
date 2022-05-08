

import java.util.ArrayList;
import java.util.List;

public class Waiter implements IEmployee{

    String name;
    String surname;
    int phoneNumber;
    int id;

    public Waiter(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    public static List<Integer> tips = new ArrayList();

    public int setTip(int tipValue) {
        tips.add(tipValue);

        return tipValue;
    }
//
//    public int getTip(int tipValue){
//
//    }

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

    @Override
    public String getSurname() {
        return surname;
    }
}
