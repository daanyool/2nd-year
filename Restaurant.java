import java.util.Scanner;
import java.util.ArrayList;

public class Restaurant {
   static Scanner obj = new Scanner(System.in);
   ArrayList<String> prod;
   ArrayList<Double> price;
   ArrayList<String> prodOrder;
   ArrayList<Double> priceOrder;
   ArrayList<Integer> quantity;

   public Restaurant() {
      prod = new ArrayList<>();
      price = new ArrayList<>();
      prodOrder = new ArrayList<>();
      priceOrder = new ArrayList<>();
      quantity = new ArrayList<>();
   }

   public void getMenu() {
      prod.add("C1");
      prod.add("C2");
      prod.add("C3");
      prod.add("R1");
      prod.add("R2");

      price.add(100.00);
      price.add(150.00);
      price.add(200.00);
      price.add(35.00);
      price.add(50.00); 
      
      System.out.println("       M E N U ");
      for(int i = 0; i<prod.size(); i++) {
            System.out.println("[" + (i+1) + "]" + " " + prod.get(i) + " --------- " + "P" + price.get(i));    //Displaying the values inside the Arraylist
            if(i==2)
               System.out.println("    A D D - O N S ");
      }
   }
   
   public void getOrders(int c) {
      System.out.print("Enter Quantity: ");
      int quant = obj.nextInt();
      
      prodOrder.add(prod.get(c-1));
      priceOrder.add(price.get(c-1) * quant);  //Adding values inside the order Arraylist
      quantity.add(quant);
   }
       
   public double getTotalPrice() {      //Total Price multiplied by .018 to convert peso into dollar
      double totalPrice = 0;
      for(int c = 0; c < priceOrder.size(); c++) 
         totalPrice += priceOrder.get(c);
      return totalPrice * 0.018;
   } 
   
   public void display() {
      System.out.println("   JOLLIBEE TOLEDO \n");
      for(int c = 0; c<prodOrder.size(); c++) {
         System.out.printf("%-5s %-10s %s", prodOrder.get(c), quantity.get(c), priceOrder.get(c));
         System.out.println();
      }
    }

   public static void main(String[] args) {
      Restaurant r = new Restaurant();
      byte choice = 0;
      int orderChoice = 0;
            
      System.out.println("  Welcome to Jollibee!");
      r.getMenu();
      
      do {
         do {
            System.out.println("\nSelect menu you want to order: ");
            orderChoice = obj.nextInt();
            
            if(orderChoice<1 || orderChoice>5)
               System.out.println("Invalid Input! Please try again.");
         } while(orderChoice<1 || orderChoice>5);
      
         r.getOrders(orderChoice);
         do {
            System.out.println("Do you want to order more? \n[1] yes \n[2] no");
            choice = obj.nextByte();
         } while(choice!=1 && choice!=2);
      } while(choice==1);
      
      System.out.println();
      r.display();
      System.out.println();
      System.out.printf("%-15s %s %.2f", "Total Price(Php):", "P", r.getTotalPrice() / .018);   //Total Price in Philippine Peso
      System.out.println();
      System.out.printf("%-15s %s %.2f", "Total Price(Dollar):", "$", r.getTotalPrice()); //Total Price in Dollar
      System.out.println("\n  T H A N K  Y O U!");
   }
}