

import java.time.LocalTime;
import java.util.*;

public class OrderRepository {
    public static Map<Date, Double> dailyRevenue = new HashMap<Date, Double>();
    public static List<IOrder> pendingOrders = new ArrayList<>();
    public static List<IOrder> completedOrders = new ArrayList<>();


    public static void addPendingOrder(IOrder order){
        pendingOrders.add(order);
    }
    public static void addCompletedOrders(IOrder order){
        pendingOrders.remove(order);
        completedOrders.add(order);
    }

    public OrderRepository(){
        Map<Integer,Integer> orderItemsById = new HashMap<Integer, Integer>();
        orderItemsById.put(1, 1);
        orderItemsById.put(2, 2);
        orderItemsById.put(3, 1);
        orderItemsById.put(4, 2);


        Map<Integer,Integer> orderItemsById2 = new HashMap<Integer, Integer>();
        orderItemsById2.put(3, 1);
        orderItemsById2.put(6, 3);

        Map<Integer,Integer> orderItemsById3 = new HashMap<Integer, Integer>();
        orderItemsById3.put(7,1);
        Map<Integer,Integer> orderItemsById4 = new HashMap<Integer, Integer>();
        orderItemsById4.putAll(orderItemsById);
        orderItemsById4.remove(3);

        OnlineOrder onlineOrder1 = new OnlineOrder(1, "Blacharska 1, Mokotow", orderItemsById);
        OnlineOrder onlineOrder2 = new OnlineOrder(2, "Kochanowskiego 45, Zoliborz",orderItemsById2);
        OnlineOrder onlineOrder3 = new OnlineOrder(3, "Aleja Lotnikow 12, Mokotow", orderItemsById3);
        OnlineOrder onlineOrder4 = new OnlineOrder(4, "Plac Wilsona 22/33, Zoliborz",orderItemsById4);
        OnlineOrder onlineOrder5 = new OnlineOrder(5, "Jana Pawla 2 32, Srodmiescie ",orderItemsById);

        StationaryOrder stationaryOrder1 = new StationaryOrder(6, 1, orderItemsById4);
        StationaryOrder stationaryOrder2 = new StationaryOrder(7,2,  orderItemsById2);
        StationaryOrder stationaryOrder3 = new StationaryOrder(8, 3,  orderItemsById3);
        StationaryOrder stationaryOrder4 = new StationaryOrder(9, 4,  orderItemsById4);
        StationaryOrder stationaryOrder5 = new StationaryOrder(10, 5,  orderItemsById2);

        completedOrders.add(onlineOrder1);
        completedOrders.add(onlineOrder2);
        completedOrders.add(onlineOrder3);
        completedOrders.add(onlineOrder4);
        completedOrders.add(onlineOrder5);
        completedOrders.add(stationaryOrder1);
        completedOrders.add(stationaryOrder2);
        completedOrders.add(stationaryOrder3);
        completedOrders.add(stationaryOrder4);
        completedOrders.add(stationaryOrder5);

        sumUpRevenue();
    }
    public static void showPendingOrders(){
//        updateCompletedOrders();
        for (int i = 0; i <pendingOrders.size() ; i++) {
            IOrder pendingOrder = pendingOrders.get(i);
            System.out.println(pendingOrder.getOrderType()+ " Order number:" +pendingOrder.getOrderId() + "  (Total price: "+pendingOrder.getTotalPrice() +")");
            if(pendingOrder.getOrderType().equals("Stationary")){
                System.out.println("Table number: " + pendingOrder.getTableNumber());
            }else{
                System.out.println("Delivery address: " + pendingOrder.getAddress());
            }
            System.out.println("    Items in this order: ");
            for (int menuItemId: pendingOrder.getNumberOfItemsById().keySet()) {
                System.out.println("    - "+MenuRepository.getMenuItem(menuItemId).getName()+ " x" + pendingOrder.getNumberOfItemsById().get(menuItemId));
            }
        }
    }
    public static void showCompletedOrders(){
//        updateCompletedOrders();

        for (int i = 0; i <completedOrders.size() ; i++) {
            IOrder completedOrder = completedOrders.get(i);
            System.out.println(completedOrder.getOrderType()+ " Order number:" + completedOrder.getOrderId()+ "  (Total price: "+completedOrder.getTotalPrice()+")");

            if(completedOrder.getOrderType().equals("Stationary")){
                System.out.println("Table number: " + completedOrder.getTableNumber());

            }else{
                System.out.println("Delivery address: " + completedOrder.getAddress());
            }
            System.out.println("    Items in this order: ");
            for (int menuItemId: completedOrder.getNumberOfItemsById().keySet()) {
                System.out.println("    - "+MenuRepository.getMenuItem(menuItemId).getName()+ " x" + completedOrder.getNumberOfItemsById().get(menuItemId));
            }
        }

    }

    public static double getTimeFromAllPendingItems(){
       // updateCompletedOrders();
        //time in minutes
        double pendingTime = 0;
        int numberOfTotalItems = 0;
        for (IOrder iOrder:pendingOrders) {
            for (int number: iOrder.getNumberOfItemsById().values()) {
                numberOfTotalItems += number;
            }
        }
        pendingTime = numberOfTotalItems*Kitchen.timeForMealPreparation;

        return pendingTime;
    }

    public static void updateCompletedOrders(){
        for (IOrder order : pendingOrders) {
            if(order.getCompletedTime().isBefore(LocalTime.now())){
                addCompletedOrders(order);
            }
        }
    }

    public static double sumUpRevenue(){
        double revenue  = 0;
        for (IOrder order :completedOrders) {
            revenue += order.getTotalPrice();
        }

        for (IOrder order :pendingOrders) {
            revenue += order.getTotalPrice();
        }
        Date today = Calendar.getInstance().getTime();
        dailyRevenue.put(today, revenue);
        return  revenue;
    }
}
