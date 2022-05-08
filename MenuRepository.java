

import java.util.HashMap;
import java.util.Map;

public class MenuRepository {
    public static int lastMenuItemId = 0;
    public static final Map<Integer, MenuItem> meals = new HashMap<Integer, MenuItem>();

    public MenuRepository() {
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Pizza", "You had never eaten that kind of pizza", 25.50, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Burger", "The best meat in the world", 19.50, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Spaghetti", "You will love it", 22.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Kebab", "Kebab same like Turkish", 12.00, true));

        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Greek Salad", "Delicious vegetables", 11.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Salad with meat", "Fitted and meated", 13.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Gyros Salad", "Like on Polish eighteenth birthday parties", 13.50, true));

        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Garlic bagguete", "Garlic bagguete with butter, will be great pattern", 6.50, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Soup with cream served cold", "Perfect flavour in warm days", 7.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Cellery chips", "If you want be more skinny", 5.00, true));

        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Cola", "Fizzy drink", 3.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Fanta", "Fizzy drink", 3.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Sprite", "Fizzy drink", 3.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Juice", "100% apple juice", 3.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Beer", "Yeah, that's beer o'clock (6% of alcohol)", 4.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Tea", "Black tea", 2.00, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Coffee", "Cappuccino", 2.50, true));


        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Ice cream", "Old day ice cream", 2.50, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "Apple pie", "Delicious apple pie with royal apples", 2.50, true));
        meals.put(++lastMenuItemId, new MenuItem(lastMenuItemId, "A piece of meringue cake", "Some fruits and amazing flavour", 2.50, true));
    }

    public static void addMenuItem(MenuItem menuItem) {
        menuItem.setId(++lastMenuItemId);
        meals.put(lastMenuItemId, menuItem);
    }


    public static void removeMenuItem(int menuItemId){
        meals.remove(menuItemId);
    }


    public static void setAvailability(int menuItemId, boolean isAvailable){
        meals.get(menuItemId).setAvaiable(isAvailable);
    }
    public static void showMenu(){
        System.out.println();
        for (MenuItem item: meals.values()) {
            if(item.getAvailable())
            System.out.println(item.getId() + " " + item.getName() + " ("+ item.getDescription()+")"+ ".................." + " Price: " +item.getPrice() + " PLN");

        }
    }


    public static MenuItem getMenuItem(int menuItemId){
        return meals.get(menuItemId);
    }
}
