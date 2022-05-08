

import java.time.LocalTime;
import java.util.Map;

public interface IOrder {
    void addMenuItemToOrder(Integer mealId, Integer numberOfItems);
    int getOrderId();
    Map<Integer,Integer> getNumberOfItemsById();
    double getTotalPrice();
    void setTotalPrice();
    void setIsLate();
    void setCompletedTime(LocalTime time);
    LocalTime getCompletedTime();
    double getPreparationTime();

    String getAddress();
    int getTableNumber();

    String getOrderType();
}


