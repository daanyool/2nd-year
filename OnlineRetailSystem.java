import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Random;

class User {
   static Scanner obj = new Scanner(System.in);
   protected int userId;
   protected String username;
   protected String email;
   
   public User(int userId, String username, String email) {
      this.userId = userId;
      this.username = username;
      this.email = email;
   }
   
   public void login() {
      System.out.print("User ID: ");
      userId = obj.nextInt();
      obj.nextLine();
      
      System.out.print("Username: ");
      username = obj.nextLine();
      
      System.out.print("Email: ");
      email = obj.nextLine();
      
      System.out.println();
 
      System.out.println("User logged in: " + username);
   }
   
   public void logout() {
      System.out.println("User logged out.");
   }
}

class Customer extends User {
   static Scanner obj = new Scanner(System.in);
   private int custId;
   private String address;
   private LocalDate orderDate;
   private Order orders;
   
   public Customer(int userId, String username, String email, int custId, String address) {
      super(userId, username, email);
      this.custId = custId;
      this.address = address;
      orders = new Order();
      orderDate = LocalDate.now();
   }
        
   public void placeOrder() {
      double total = 0.0;
      boolean confirm = true;
      String choice = "";
      
      do {
         orders.addProductToOrder();
         do {
            System.out.println("Do you want to add more product?(y/n) ");
            choice = obj.nextLine();
         } while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
      } while(choice.equalsIgnoreCase("y"));
           
      orders.calculateTotalAmount(total);
      orders.confirmOrder(confirm); 
   }
   
   public void viewOrderHistory() {
      double total = 0.0;
      
      System.out.printf("%-50s %s", " ", "O R D E R   R E C E I P T");
      System.out.println("\n");
      System.out.printf("%s %-90s %s %s", "Order ID", orders.getOrderId(), "Date:", orderDate);
      System.out.println("\n");
      System.out.printf("%-10s %-20s %-73s %s", "No.", "Product ID", "Product Name", "Price");
      System.out.println();
      for(int a = 0; a < orders.prodIds.size(); a++) {
         System.out.printf("%-10s %-20s %-73s %s", a+1, orders.prodIds.get(a), orders.prodNames.get(a), orders.prices.get(a));
         System.out.println();
      }
      System.out.println();
      System.out.printf("%-106s %.2f", "Total: ", + orders.calculateTotalAmount(total));
      System.out.println("\n");
      System.out.printf("%-50s %s", " ", "T H A N K   Y O U ! ");
      System.out.println("\n");
   }
}

class Admin extends User {
    static Scanner obj = new Scanner(System.in);
    private int adminId;
    private String dept;
    private Product products;
    
    public Admin(int userId, String username, String email, int adminId, String dept) {
        super(userId, username, email);
        this.adminId = adminId;
        this.dept = dept;
        products = new Product();
    }
    
    public void productMenu() {
      System.out.printf("%-65s %s", " ", "I N V E N T O R Y");
      System.out.println();
      System.out.printf("%-30s %-30s %-30s %-30s %s\t", "Number", "Product ID", "Product Name", "Price", "Stock");
      System.out.println();
      for(int a = 0; a < products.prodId.size(); a++) {
         System.out.printf("%-30s %-30s %-30s %-30s %s\t", a+1, products.prodId.get(a), products.prodName.get(a), products.price.get(a), products.stockQuant.get(a));
         System.out.println();
      }
    }
    
    public void addProduct() {
         System.out.print("Product ID: ");
         int prodId = obj.nextInt();
         products.prodId.add(prodId);
         obj.nextLine();
              
         System.out.print("Product Name: ");
         String name = obj.nextLine();
         products.prodName.add(name);
         
         System.out.print("Product Price: ");            
         double price = obj.nextDouble();
         products.price.add(price);
         
         System.out.print("Stock Quantity: ");
         int stockQuant = obj.nextInt();
         products.stockQuant.add(stockQuant); 
              
         System.out.println("Product added.");   
         System.out.println();       
     }
                   
    public void removeProduct() {
       System.out.print("Enter product you want to remove: ");
       byte choice = obj.nextByte();
       products.prodId.remove(choice-1);
       products.prodName.remove(choice-1);
       products.price.remove(choice-1);
       products.stockQuant.remove(choice-1);
       
       System.out.println("Product removed.");
    }
        
    public void manageInventory() {
      int count = 0, newStock = 0;
      double newPrice = 0.0;
       
      productMenu();
      System.out.println("[1] Update Price");
      System.out.println("[2] Update Stock");
      byte manageChoice = obj.nextByte();
      
      switch(manageChoice) {
         case 1:
            products.updatePrice(count, newPrice);
            break;
         case 2:
            products.updateStock(count, newStock);
            break;
         default: System.out.println("Invalid");
      } 
    }
}

class Product {
    static Scanner obj = new Scanner(System.in);
    static ArrayList<Integer> prodId;
    static ArrayList<String> prodName;
    static ArrayList<Double> price;
    static ArrayList<Integer> stockQuant;
    
    public Product() {
      prodId = new ArrayList<Integer>();
      prodName = new ArrayList<String>();
      price = new ArrayList<Double>();
      stockQuant = new ArrayList<Integer>();
    }
                
    public void updatePrice(int count, double newPrice) {
        System.out.print("Product No.: ");
        count = obj.nextInt();
        System.out.println();
        System.out.println("Enter New Price: ");
        newPrice = obj.nextDouble();
        
        System.out.println("Price updated successfully!");
        price.set(count-1, newPrice);
    }
    
    public void updateStock(int count, int newQuant) {
        System.out.print("Product No.: ");
        count = obj.nextInt();
        System.out.println();
        System.out.println("Enter New Stock Quantity: ");
        newQuant = obj.nextInt();

        System.out.println("Stock updated successfully!");
        stockQuant.set(count-1, newQuant);
    }
}

class Order {
   private Random rand = new Random();
   private int orderId = rand.nextInt(1000000);
   private double totalAmount;
   private Product products = new Product();
   static ArrayList<Integer> prodIds = new ArrayList<Integer>();
   static ArrayList<String> prodNames = new ArrayList<String>();
   static ArrayList<Double> prices = new ArrayList<Double>();
   static Scanner obj = new Scanner(System.in);
   
   public int getOrderId() {
      return orderId;
   }
        
   public void addProductToOrder() {
      int minusStock = 0, numProd = 0;
      double totalPrice = 0.0;
            
      System.out.print("Choose products you want to order: ");
      byte custChoice = obj.nextByte();
      
      do {
         System.out.print("Enter number of products: ");
         numProd = obj.nextInt();
         
         if(products.price.get(custChoice-1) > numProd)
           minusStock = products.stockQuant.get(custChoice-1) - numProd;
         else
            System.out.println("Not enough stock! Please try again.");
       } while(products.stockQuant.get(custChoice-1) < numProd);
      
      prodIds.add(products.prodId.get(custChoice-1));
      prodNames.add(products.prodName.get(custChoice-1));   
      prices.add(products.price.get(custChoice-1) * numProd);
      
      System.out.println();
      System.out.println("Products added to order Successfully!");
   } 
   
   public double calculateTotalAmount(double totalAmount) {
      for(int i = 0; i < prodIds.size(); i++) 
         totalAmount += prices.get(i);
      return totalAmount;
   }
   
   public boolean confirmOrder(boolean confirm) {
      String input;
      obj.nextLine();
      do {
         System.out.print("Are you sure you want to confirm your order?(y/n) ");
         input = obj.nextLine();
         
         if(input.equalsIgnoreCase("y"))
            confirm = true;
         else
            confirm = false;
       } while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
      return confirm;
   }
}
      
public class OnlineRetailSystem { 
   static Scanner obj = new Scanner(System.in);

   public static void main(String[] args) {
      int userId = 0, custId = 0, adminId = 0, count = 1;
      String username = "", email = "", address = "", dept = "";
      Customer c = new Customer(userId, username, email, custId, address);
      Admin a = new Admin(userId, username, email, adminId, dept);
      Order o = new Order();
      Product p = new Product();
      String yesOrNo = "";
      boolean bool = true;
      
      System.out.println("---------------------------------------------->  O N L I N E   R E T A I L   S Y S T E M  <----------------------------------------------");
      System.out.println();
      while(true) {
      c.login();
      
      System.out.println("\t[1] Customer");
      System.out.println("\t[2] Admin");
      byte choice = obj.nextByte();
      
      switch(choice) {
         case 1:
            
            System.out.print("Customer ID: ");
            custId = obj.nextInt();
            obj.nextLine();
            
            System.out.print("Address: ");
            address = obj.nextLine();
            
            do {
               bool = true;
               System.out.println("\t[1] Place Order");
               System.out.println("\t[2] View Order History");
               System.out.println("\t[3] Log out");
               byte cusChoice = obj.nextByte();
            
               switch(cusChoice) {
                  case 1:
                     a.productMenu();
                     c.placeOrder();
                     break;
                  case 2:
                     c.viewOrderHistory();
                     break;
                  case 3:
                     c.logout();
                     bool = false;
                     break;
                  default: System.out.println("Invalid");
               } 
            }while(bool==true);
            break;
         case 2:
            System.out.print("Admin ID: ");
            adminId = obj.nextInt();
            obj.nextLine();
            
            System.out.print("Department: ");
            dept = obj.nextLine();
            
            do {
               bool = true;
               System.out.println("\t[1] Add Product");
               System.out.println("\t[2] Remove Product");
               System.out.println("\t[3] Manage Inventory");
               System.out.println("\t[4] Log out");
               byte adChoice1 = obj.nextByte();
            
               switch(adChoice1) {
                  case 1:
                     do {
                        a.addProduct();
                        System.out.println();
                        System.out.print("Do you want to add more products?(y/n) ");
                        do {
                           yesOrNo = obj.nextLine();
                        } while(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n"));
                     } while(yesOrNo.equalsIgnoreCase("y"));
                     a.productMenu();
                     System.out.println();
                     break;
                  case 2:
                     do {
                        a.removeProduct();
                        System.out.println();
                        System.out.print("Do you want to remove another product?(y/n) ");
                        do {
                           yesOrNo = obj.nextLine();
                        } while(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n"));
                     } while(yesOrNo.equalsIgnoreCase("y"));
                     a.productMenu();
                     System.out.println();
                     break;
                  case 3:
                    do {
                        a.manageInventory();
                        System.out.println();
                        System.out.print("Do you want to update another price and stock?(y/n) ");
                        do {
                           yesOrNo = obj.nextLine();
                        } while(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n"));
                     } while(yesOrNo.equalsIgnoreCase("y"));
                     a.productMenu();
                     System.out.println();
                     break;
                  case 4:
                     a.logout();
                     bool = false;
                     break;
                }
              } while(bool==true);
            break;
            default: System.out.println("Invalid");
         }
      }            
   } 
 } 
   