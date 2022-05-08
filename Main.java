import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
   public static List<Integer> orderIds = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
       Restaurant restaurant = new Restaurant();

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the command: ");

        for (int i = 0; true ; i++){
            String userInput = sc.next();

            if(userInput.equals("stop")){
                sc.close();
                break;
            }else{
                ConsoleController.executeAction(userInput, sc);
            }
        }
    }
}
