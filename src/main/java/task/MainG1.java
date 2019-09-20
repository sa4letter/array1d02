package task;

import java.util.*;

public class MainG1 {
  private static int[][] sMatrix;
  private static int[] pathMatrix;

  private static void findAndPutNextAbove(int base, int current, int except) {
    for (int k = current; k < sMatrix[current].length; k++) {
      //System.out.println("kA="+k);
      if (sMatrix[current][k] == 1 && k != except) {
        pathMatrix[k] = 1;
        System.out.println(String.format("Above findAndPutNext(%d, %d, %d); ->%s", base, k, k, Arrays.toString(sMatrix[k])));
        findAndPutNextAbove(base, k, k);
      }

//      System.out.println("kB="+k);
//      if(k-1 >= 0 && k != except && sMatrix[current][k-1] == 1){
//        pathMatrix[k-1] = 1;
//        System.out.println(String.format("Below findAndPutNext(%d, %d, %d); ->%s", base, k-1, k, Arrays.toString(sMatrix[k-1])));
//        findAndPutNextAbove(base, k-1, k);
//      }
    }
  }

  private static void findAndPutNextBelow(int base, int cCol, int except) {
    for (int k = cCol; k < sMatrix[cCol].length; k++) {
      if (sMatrix[cCol][k] == 1) {
        if(k>0 && cCol>0 && sMatrix[k][cCol-1] == 1){
          System.out.println(String.format("Below findAndPutNext(%d, %d, %d); ->%s", base, k-1, k, Arrays.toString(sMatrix[k-1])));
        }
        //pathMatrix[k-1] = 1;
        System.out.println("++");//Below findAndPutNext(%d, %d, %d); ->%s", cRow, k-1, k, Arrays.toString(sMatrix[k-1])));
        //findAndPutNextBelow(cRow, k-1, k);
      }

    }
  }

  private static boolean canWin(int leap, int[] game) {
    // Return true if you can win the game; otherwise, return false.
    System.out.println("Original: " + Arrays.toString(game) + " leap = "+ leap);
    // fast check
    if(leap >= game.length) return true;

    for (int i = 0, j = 0; i < game.length; i++) {
      if (game[i] == 1) j++;
      else j = 0;

      if ((leap == 0 && j > 0) || (leap > 0 && j > leap - 1)) return false;
    }

    pathMatrix = new int[game.length];
    sMatrix = new int[game.length][game.length];

    for (int i = 0; i < game.length; i++) {
      if (game[i] == 0) {
        //I - move forward/backward
        if (i + 1 < game.length && game[i + 1] == 0) {
          sMatrix[i][i + 1] = 1; //row
          sMatrix[i + 1][i] = 1; //column
        }

        //III - leap
        if (i + leap < game.length && game[i + leap] == 0 && leap > 0) {
          sMatrix[i][i + leap] = 1; //row
          sMatrix[i + leap][i] = 1; //column
        }
      }
    }

    System.out.println("sMatrix");
    for (int i = 0; i < game.length; i++) {
      System.out.println("i=" + i + "->" + Arrays.toString(sMatrix[i]));
    }

    findAndPutNextAbove(0, 0, 0);
    System.out.println("pathMatrix: "+Arrays.toString(pathMatrix));

    findAndPutNextBelow(0, 0, 0);
    System.out.println("pathMatrix: "+Arrays.toString(pathMatrix));

    for(int z=pathMatrix.length-1; z>=0; z--){

      if(z<pathMatrix.length-leap) return false;
      if(pathMatrix[z]==1) return true;
    }

    return false;
    // else return false;

  }

  public static void main(String[] args) {
               // 0  1  2  3  4  5  6  7  8  9 10  11 12
    int[] game = //{0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0};
                 //{0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0};
                 {0, 1, 0, 0, 1, 0, 1, 1, 0}; //3 backward
                 //{0, 0, 0, 0, 0};
    int leap = 3;
    System.out.println("Res=" + canWin(leap, game));
  }
}
