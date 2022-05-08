import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeRepository {
   static List <IEmployee> employees = new ArrayList<IEmployee>();

    public EmployeeRepository(){
        Cook cook1 = new Cook( 1,"Bartek", "Kowalski");
        Cook cook2 = new Cook(2,"Adam","Miałczyński");
        Waiter waiter1 = new Waiter(3,"Angelika", "Różańska");
        Waiter waiter2 = new Waiter(4,"Alicja", "Kot");
        Supplier supplier1 = new Supplier(5,"Stefan", "Bachleda");
        Supplier supplier2 = new Supplier(6,"Adam", "Małysz");

        employees.add(cook1);
        employees.add(cook2);
        employees.add(waiter1);
        employees.add(waiter2);
        employees.add(supplier1);
        employees.add(supplier2);

    }
    public static void showEmployees(){
        for (int i = 0; i < employees.size() ; i++) {
        System.out.println(employees.get(i).getName() + " " + employees.get(i).getSurname() +" Id: "+employees.get(i).getPositionId());
            System.out.println();
        }

    }
    public static IEmployee getEmployee(int id){
        List<IEmployee> filteredEmployees = employees.stream().filter(e -> e.getPositionId() == id).collect(Collectors.toList());

        return filteredEmployees.get(0);
    }

    public static void addEmployee(IEmployee employee){
        employees.add(employee);
    }

    public static void removeEmployee(int employeeId){
        employees.remove(--employeeId);

    }
}
