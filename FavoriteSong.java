public class FavoriteSong {
   public static void main(String[] args) {
     String[] lyrics = {"It's not the flowers",
                        "Wrapped in fancy papers",
                        "It's not the ring",
                        "I wear around my finger",
                        "There's nothing in all the world I need",
                        "When I have you here beside me, here beside me",
                        "So you could give me wings to fly",
                        "Catch me if I fall", 
                        "Pull the stars down from the sky",
                        "So I could wish on them all",
                        "I couldn't ask for more",
                        "Your love is the greatest gift of all"};
      try {
         for(String l: lyrics) {
            System.out.println(l);
            System.out.println();
            Thread.sleep(3000);
         }
      } catch(InterruptedException e) {
            e.printStackTrace();
        }
   }
}