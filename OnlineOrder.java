

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class OnlineOrder implements IOrder{
    int orderId;
    public Map<Integer,Integer> numberOfItemsById = new HashMap<Integer, Integer>();
    int discount = 0;
    boolean isLate = false;
    double sumUpTotalPrice;
    LocalTime completedDateTime;
    final int priority = 2;

    String address;

    public OnlineOrder() {
       int newId = OrderRepository.completedOrders.size() + OrderRepository.pendingOrders.size()+1;
       this.orderId = newId;
    }

    public OnlineOrder(int orderId, String address) {
        this.orderId = orderId;
        this.address = address;
    }

    public OnlineOrder(int orderId, String address, Map<Integer, Integer> orderMenuItems) {
        this.orderId = orderId;
        this.address = address;
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
    public double getPreparationTime() {
        double totalTimeInMinutes = 0;
        for (int numberOfItems : numberOfItemsById.values()){
            totalTimeInMinutes = numberOfItems*Kitchen.timeForMealPreparation;
        }
        return totalTimeInMinutes;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setNumberOfItemsById(Map<Integer, Integer> numberOfItemsById) {
        this.numberOfItemsById = numberOfItemsById;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
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

    public int getPriority() {
        return priority;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int getTableNumber() {
        return 0;
    }

    @Override
    public String getOrderType() {
        return "Online";
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOrderLate(OnlineOrder order) {
        isLate = true;
        return false;
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

}
