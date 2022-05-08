

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    static boolean isOpen = true;


    public Restaurant() {
        EmployeeRepository employees =  new EmployeeRepository();
        MenuRepository menu = new MenuRepository();
        OrderRepository order = new OrderRepository();
    }

    public static void setIsOpen(boolean isOpen) {
        Restaurant.isOpen = isOpen;
    }
}
