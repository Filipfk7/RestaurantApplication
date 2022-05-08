import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleController {
    public static void executeAction(String userInput, Scanner sc) throws IOException {
        if (userInput.equals("Show_menu")) {
            System.out.println("Dear guest these items are available:");
            MenuRepository.showMenu();
            System.out.println("* This menu show only available meals");

        } else if (userInput.equals(("Show_pending_orders"))) {
            OrderRepository.showPendingOrders();
        } else if (userInput.equals("Add_to_menu")) {

            System.out.println("Provide Name");
            String newName = sc.next();

            System.out.println("Provide short description");
            String newDescription = sc.next();

            System.out.println("Provide price");
            double newPrice = sc.nextDouble();

            System.out.println("Is this item available? (true/false)");
            boolean newIsAvailable = sc.nextBoolean();

            MenuItem newMenuItem = new MenuItem(newName, newDescription, newPrice, newIsAvailable);
            MenuRepository.addMenuItem(newMenuItem);

        } else if (userInput.equals("Remove_from_menu")) {
            System.out.println("Provide menu item id to remove");
            String parameter = sc.next();
            MenuRepository.removeMenuItem(Integer.valueOf(parameter));
            System.out.println("Item has been removed");

        } else if (userInput.equals("Set_unavailable_item")) {
            System.out.println("Provide item id to make it not available");

            String parameter = sc.next();

            MenuRepository.setAvailability(Integer.valueOf(parameter), false);
            System.out.println("Item marked as not available");

        }else if (userInput.equals("Set_available_item")) {
            System.out.println("Provide item id to make it available");

            String parameter = sc.next();

            MenuRepository.setAvailability(Integer.valueOf(parameter), true);
            System.out.println("Item marked as available");

        } else if (userInput.equals("SaveMenu")) {

            FileWriter writer = new FileWriter("menu.txt");
            System.out.println("Saving menu. Please... wait");
            for(MenuItem item: MenuRepository.meals.values()) {
                writer.write(item.getName() + " " + item.getDescription() + System.lineSeparator());
            }
            writer.close();
            System.out.println("Menu Saved. Please find the file menu.txt");


        } else if (userInput.equals("LoadMenu")) {
            //TBD
        } else if (userInput.equals("Make_order")) {
            System.out.println("Stationary or Online");
            String type = sc.next();
            int numberOfOrders = OrderRepository.pendingOrders.size() + OrderRepository.completedOrders.size();
           IOrder newOrder;
            if(type.equals("Online")){
                System.out.println("Provide address details: ");
                String address = sc.next();
                newOrder = new OnlineOrder(numberOfOrders+1, address);
            }else{
                System.out.println("Which place do you want to seat");
                int tableNumber = sc.nextInt();
                newOrder = new StationaryOrder(numberOfOrders+1, tableNumber);
            }
            String yOrN ="";
            int mealId;
            int howMany;
            do {
                System.out.println("Provide menu item id");
                mealId = sc.nextInt();
                System.out.println("How many?");
                howMany = sc.nextInt();
                System.out.println("Do you want other meal? (yes/no)");
                yOrN = sc.next();
                newOrder.addMenuItemToOrder(mealId, howMany);
            }while (yOrN.equals("yes"));
            double  waitingTime = OrderRepository.getTimeFromAllPendingItems()+newOrder.getPreparationTime();

           if (waitingTime>15){
               newOrder.setIsLate();
           }

           newOrder.setTotalPrice();
           newOrder.setCompletedTime(LocalTime.now().plusMinutes((long)(waitingTime)));

           OrderRepository.addPendingOrder(newOrder);
            System.out.println("Your order has beend added to pending orders. To check it please use Show_pending_orders command");
        }
        else if (userInput.equals("Make_random_order")){
            int numberOfMenuItems = (int)(Math.random()*10+1);
            IOrder newRandomOrder;

            if(numberOfMenuItems%2==0){
                newRandomOrder = new OnlineOrder();
            }else {
                newRandomOrder = new StationaryOrder();
            }

            for (int i = 0; i < numberOfMenuItems; i++) {
                int menuItemId = (int)(Math.random()*9+1);
                int numberOfItems = (int)(Math.random()*5+1);
                newRandomOrder.addMenuItemToOrder(menuItemId,numberOfItems);
            }

            newRandomOrder.setTotalPrice();
            OrderRepository.pendingOrders.add(newRandomOrder);

            System.out.println("Random order has been created. Details: ");
            System.out.println("Order Id : "+newRandomOrder.getOrderId());
            System.out.println("    Items in this order: ");
            for (int menuItemId: newRandomOrder.getNumberOfItemsById().keySet()) {
                System.out.println("    - "+MenuRepository.getMenuItem(menuItemId).getName() + " x" + newRandomOrder.getNumberOfItemsById().get(menuItemId));
            }

        }
        else if (userInput.equals("Show_completed_orders")){
            OrderRepository.showCompletedOrders();
        }
        else if (userInput.equals("Sum_up_revenue")){
            System.out.println("Revenue: " + OrderRepository.sumUpRevenue());
        }
        else if (userInput.equals("Hire_new_employee")){

            System.out.println("Provide employee id: ");
            int empId = sc.nextInt();

            System.out.println("Provide employee name");
            String empName = sc.next();

            System.out.println("Provide employee surname");
            String empSurname = sc.next();

            System.out.println("Provide employee role (Waiter/Cook/Supplier)");
            String empRole = sc.next();

            if(empRole.equals("Waiter")){
                Waiter waiter = new Waiter( empId,empName, empSurname);
                EmployeeRepository.addEmployee(waiter);
            }else if(empRole.equals("Cook")){
                Cook cook = new Cook( empId,empName, empSurname);
                EmployeeRepository.addEmployee(cook);
            }else if(empRole.equals("Supplier")){
                Supplier supplier = new Supplier( empId,empName, empSurname);
                EmployeeRepository.addEmployee(supplier);
            }

            System.out.println("Employee has been added");
        }
        else if (userInput.equals("Remove_employee")){
            System.out.println("Provide menu employee id to remove");
            String parameter = sc.next();
            EmployeeRepository.removeEmployee(Integer.valueOf(parameter));
            System.out.println("Employee has been fired");

        }
        else if (userInput.equals("Start_day")){
            Restaurant.setIsOpen(true);

        }
        else if (userInput.equals("End_day")){
            Restaurant.setIsOpen(false);
        }
        else if (userInput.equals("Show_all_employees")){
            EmployeeRepository.showEmployees();

        }
        else if (userInput.equals("Get_employee_info")){
            System.out.println("Provide employee number");

            String parameter = sc.next();

            IEmployee emp = EmployeeRepository.getEmployee(Integer.valueOf(parameter));
            System.out.println(emp.getName() + " "+emp.getSurname());
        }
    }
}
