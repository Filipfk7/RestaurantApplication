

import java.util.ArrayList;
import java.util.List;

public class Supplier implements IEmployee {

    private String name;
    private String surname;
    int phoneNumber;
    int deliveryTime = 2;
    int id;
    public static List<Integer> tips = new ArrayList<>();

    public Supplier(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public void setTips(int tipValue){
        tips.add(tipValue);
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

    @Override
    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public static void setTips(List<Integer> tips) {
        Supplier.tips = tips;
    }
}
