package task;

public class Main{
   public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        for(int i = 0, j = 0, t = 0; i<game.length; ){

        
        return true;
    }

   public static void main(String[] args){
                  //0  1  2  3  4  5  6  7  8  9
     int [] game = {0, 1, 1, 0, 0, 0, 1, 1, 1, 0};
     int leap = 4; 
     System.out.println("Res="+canWin(leap, game));
   }
}