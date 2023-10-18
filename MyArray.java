import java.util.Scanner;

public class MyArray {
   private static int[] arr;
   private int size, idx, val, count;
   private boolean bool;
   
   public MyArray(int s) {
      size = s;
      arr = new int[size];
      idx = -1;
      val = 0;
      count = 0; 
   }
   
   public boolean isEmpty() {
      return count==0;
   }
   
   public boolean isFull() {
      return count==size;
   }
   
   public int addElement(int val) {
      this.val = val;
      if(!isFull()) {
         arr[++idx] = val;
         count++;
      }
      return arr[idx];
   }
   
   public void view() {   
      System.out.print("arr[] = {");
      for(int e: arr)
         System.out.print(e + "\t");
      System.out.println("}");
   }
   
   public void removeElement(int idx) {
      this.idx = idx;
      arr[idx] = 0;
      count--;
   }
   
   public void searchElement(int val) {
      this.val = val;
      int find = 0;
      for(idx = 0; idx<size; idx++) {
         if(val==arr[idx]) {
            System.out.println(val + " is in index " + idx);
         }
         else 
            find++;
      }
      if(find==size)
         System.out.println("Element not found!");
   }
   
   public void sortAscending() {
      int temp;
      if (!isEmpty()) {
         for (idx = 0; idx < size; idx++) {
            for (int x = 1; x < size; x++) { 
               if (arr[x-1] > arr[x]) {
                  temp = arr[x-1];
                  arr[x-1] = arr[x];
                  arr[x] = temp;
               }
            }
         }

         System.out.println("\nSuccessfully sorted ascendingly!");
      }

      else { 
         System.out.println("The array is empty!"); 
      }
   }

   public void sortDescending() {
      int temp;
      if (!isEmpty()) {
         for (idx = 0; idx < size; idx++) {
            for (int x = 1; x < size; x++) { 
               if (arr[x-1] < arr[x]) {
                  temp = arr[x-1];
                  arr[x-1] = arr[x];
                  arr[x] = temp;
               }
            }
         }

         System.out.println("\nSuccessfully sorted descendingly!");
      }

      else { 
         System.out.println("The array is empty!"); 
      }
   }
   
   public void edit(int idx, int val) {
      this.val = val;
      this.idx = idx;
      arr[idx] = val;
   }
      
   public void exit() {
      System.out.println("Exit");
   }
   
   public static void main(String[] args) {
      Scanner obj = new Scanner(System.in);
      byte choice = 0, addChoice = 0, sortChoice = 0;
      int size = 0;
      boolean bool = true;
         
      while(bool==true) {
         try {
            System.out.println(" --- ARRAY MENU --- ");
            System.out.print("Array size: "); 
            size = obj.nextInt(); 
            bool = false;
         } 
         catch(Exception e) {
            System.out.println("Invalid Input.");
            obj.next();      
         }
      }       
      MyArray a = new MyArray(size);
      
      do {
         bool = true;      
         while(bool==true) {
            try {
               System.out.println("[1]Add");
               System.out.println("[2]View");
               System.out.println("[3]Remove");
               System.out.println("[4]Search");
               System.out.println("[5]Sort");
               System.out.println("[6]Edit");
               System.out.println("[7]Exit");
               choice = obj.nextByte();
               bool = false;
            }
            catch(Exception e) {
               System.out.println("Invalid Input");
               obj.next();
            }
         }
      
            switch(choice) {
               case 1:
                  do {
                     bool = true;
                     while(bool==true) {
                        try {
                           System.out.print("add element: ");
                           int val = obj.nextInt();
                           bool = false;
                           a.addElement(val);
                        }
                        catch(Exception e) {
                           System.out.println("Invalid Input");
                           obj.next();
                        }
                     }
                        do {
                           bool = true;
                           while(bool==true) {
                              try {
                                 System.out.println("Do you want to add more element? \n[1] yes \n[2] no: ");
                                 addChoice = obj.nextByte();
                                 bool = false;
                              }
                              catch(Exception e) {
                                 System.out.println("Invalid Input");
                                 obj.next();                                
                              }
                           }
                        } while(addChoice!=1 && addChoice!=2);
                        if(a.isFull())
                           System.out.println("Array is full!");
                           bool = false;
                  } while(addChoice==1 && !a.isFull());
                  break;
               case 2:
                  a.view();
                  break;
               case 3:
                  bool = true;
                  int i =  0;
                  while(bool==true) {
                     try {
                        do {
                           System.out.print("Enter index: ");
                           i = obj.nextInt();
                           
                           if(i>=size) 
                              System.out.println("Out of bounds. Please try again");
                           else {
                              a.removeElement(i);
                              bool = false;
                           }
                        } while(i>=size);
                     }
                     catch(Exception e) {
                        System.out.println("Invalid Input");
                        obj.next();
                     }
                  }
                  break;
               case 4:
                  bool = true;
                  while(bool==true) {
                     try {
                        System.out.print("Value to search: ");
                        int v = obj.nextInt();
                        a.searchElement(v);
                        bool = false;
                     }
                     catch(Exception e) {
                        System.out.println("Invalid Input");
                        obj.next();
                     }
                  }
                  break;
               case 5:
                  bool = true;
                  while(bool==true) {
                     try {
                        while(sortChoice!=1 || sortChoice!=2) {
                           System.out.print("[1] Ascending \n[2] Descending");
                           sortChoice = obj.nextByte();
               
                           if(sortChoice==1) {
                              a.sortAscending();
                              break;
                           }
                           else if(sortChoice==2) {
                              a.sortDescending();
                              break;
                           }
                           else
                              System.out.println("Invalid");
                        }
                        bool = false;
                     }
                     catch(Exception e) {
                        System.out.println("Invalid Input");
                        obj.next();
                     }
                  }
                  break;
               case 6:
                  bool = true;
                  i = 0;
                  while(bool==true) {
                     try {
                        do {
                           System.out.print("Enter index: ");
                           i = obj.nextInt();
                           
                           if(i>=size)
                              System.out.println("Out of bounds");
                        } while(i>=size);
                        System.out.print("Enter value: ");
                        int v = obj.nextInt();
                        bool = false;
                        a.edit(i, v);
                     }
                     catch(Exception e) {
                        System.out.println("Invalid Input");
                        obj.next();
                     }
                  }
                  break;
               case 7:
                  a.exit();
                  break;
            } 
         } while(choice!=7);
     }
}
