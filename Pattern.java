public class Pattern {
   public static void main(String[] args) {
      for(int a = 0; a<4; a++) {
         if(a<=1) {
            System.out.printf("%-20s %s", "x", "x");
            System.out.println();
         }
         
         if(a==2) {
             System.out.printf("%-4s %s", "x", " ");
             for(int b = 0; b<10; b++) {
                System.out.print("x");
             }
             System.out.printf("%-4s %s", " ", "x");
              System.out.println();
             
             for(int c = 0; c<=4; c++) 
                System.out.print("x");
             System.out.print(" ");
             System.out.print("x");
             for(int d = 0; d<8; d++)
                System.out.print(" ");
             System.out.print("x");
             System.out.print(" ");
             for(int e = 0; e<=4; e++)
                System.out.print("x");  
             System.out.println();
         }
         
         if(a==3) {
            for(int f = 0; f<2; f++) {
               System.out.printf("x");
               for(int g = 0; g<3; g++)
                  System.out.print(" ");
               System.out.print("x");
               System.out.print(" ");
               System.out.print("x");
               for(int h = 0; h<8; h++)
                  System.out.print(" ");
               System.out.print("x");
               System.out.print(" ");
               System.out.print("x");
               for(int i = 0; i<3; i++)
                  System.out.print(" ");
               System.out.print("x");
               System.out.println();
            }
         }
      }
   }
}