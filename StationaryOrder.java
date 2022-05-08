

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class StationaryOrder implements IOrder {
    int orderId;
    public Map<Integer,Integer> numberOfItemsById = new HashMap<Integer, Integer>();
    boolean isLate = false;
    double discount = 0.8;
    final int priority = 1;
    double sumUpTotalPrice;
    LocalTime completedDateTime;

    int tableNumber;

    public StationaryOrder() {
        int newId = OrderRepository.completedOrders.size() + OrderRepository.pendingOrders.size()+1;
        this.orderId = newId;
    }

    public StationaryOrder(int orderId, int tableNumber) {
        this.orderId = orderId;
        this.tableNumber = tableNumber;
    }


    public StationaryOrder(int orderId, int tableNumber, Map<Integer, Integer> orderMenuItems) {
        this.orderId = orderId;
        this.tableNumber = tableNumber;
        this.numberOfItemsById  = orderMenuItems;
        setTotalPrice();
    }

    @Override
    public void addMenuItemToOrder(Integer mealId, Integer numberOfItems) {
        if (Restaurant.isOpen && MenuRepository.meals.get(mealId).getAvailable()){
            numberOfItemsById.put(mealId, numberOfItems);
        }else {
            System.out.println("Sorry restaurant is closed, you can't make an order");
        }
    }


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setNumberOfItemsById(Map<Integer, Integer> numberOfItemsById) {
        this.numberOfItemsById = numberOfItemsById;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getPriority() {
        return priority;
    }

    public double getSumUpTotalPrice() {
        return sumUpTotalPrice;
    }

    public void setSumUpTotalPrice(double sumUpTotalPrice) {
        this.sumUpTotalPrice = sumUpTotalPrice;
    }

    public LocalTime getCompletedDateTime() {
        return completedDateTime;
    }

    public void setCompletedDateTime(LocalTime completedDateTime) {
        this.completedDateTime = completedDateTime;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    @Override
    public int getOrderId() {
        return orderId;
    }

    @Override
    public Map<Integer, Integer> getNumberOfItemsById() {
        return numberOfItemsById;
    }

    @Override
    public double getTotalPrice() {
        return sumUpTotalPrice;
    }

    @Override
    public void setTotalPrice() {
        double totalPrice = 0;
        for (int menuItemId: numberOfItemsById.keySet()) {
            double itemPrice = MenuRepository.getMenuItem(menuItemId).getPrice();
            int numberOfItems = numberOfItemsById.get(menuItemId);

            totalPrice += itemPrice*numberOfItems;
        }
        if (isLate){
            //probability
            if(Math.random()*2==1){
                sumUpTotalPrice = totalPrice*discount;
            }else{
                sumUpTotalPrice = 0;
            }

        }else {
            sumUpTotalPrice= totalPrice;
        }
    }

    @Override
    public void setIsLate() {
        isLate = true;
    }
    @Override
    public void setCompletedTime(LocalTime time) {
        completedDateTime = time;
    }

    @Override
    public LocalTime getCompletedTime() {
        return completedDateTime;
    }

    @Override
    public double getPreparationTime() {
        double totalTimeInMinutes = 0;
        for (int numberOfItems : numberOfItemsById.values()){
            totalTimeInMinutes = numberOfItems*Kitchen.timeForMealPreparation;
        }
        return totalTimeInMinutes;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getOrderType() {
        return "Stationary";
    }
}
