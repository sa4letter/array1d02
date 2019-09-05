package task;

import java.util.*;

public class MainG1 {
  private static int[][] sMatrix;
  private static int[][] pathMatrix;

  private static void findAndPutNext(int base, int current) {
    for (int k = 0; k < sMatrix[current].length; k++) {
      if (k != base && sMatrix[current][k] == 1) {
      //if (sMatrix[current][k] == 1) {
        //pathMatrix[base][k] = 1;
        pathMatrix[base][k] = 1;
        System.out.println(String.format("findAndPutNext(%d, %d); ->%s", base, k, Arrays.toString(sMatrix[k])));
        findAndPutNext(base, k);
      }
    }
  }

  private static boolean canWin(int leap, int[] game) {
    // Return true if you can win the game; otherwise, return false.

    // fast check
    for (int i = 0, j = 0; i < game.length; i++) {
      if (game[i] == 1) j++;
      else j = 0;

      if ((leap == 0 && j > 0) || (leap > 0 && j > leap - 1)) return false;
    }

    pathMatrix = new int[game.length][game.length];
    sMatrix = new int[game.length][game.length];

    for (int i = 0; i < game.length; i++) {
      if (game[i] == 0) {
        if (i + 1 < game.length && game[i + 1] == 0) {
          sMatrix[i][i + 1] = 1;
          sMatrix[i + 1][i] = 1;
        }
        if (i + leap < game.length && game[i + leap] == 0) {
          sMatrix[i][i + leap] = 1;
          sMatrix[i + leap][i] = 1;
        }
      }
    }

    System.out.println("sMatrix");
    for (int i = 0; i < game.length; i++) {
      System.out.println("i=" + i + "->" + Arrays.toString(sMatrix[i]));
    }

    //for (int row = 0; row < game.length; row++) {
      findAndPutNext(0, 0);
    //}

    System.out.println("pathMatrix");
    for (int j = 0; j < game.length; j++) {
      System.out.println("j=" + j + "->" + Arrays.toString(pathMatrix[j]));
    }
    return true;
    // else return false;

  }

  public static void main(String[] args) {
               // 0  1  2  3  4  5  6  7  8  9 10  11 12
    int[] game = {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0}; // {0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0};
    int leap = 3;
    System.out.println("Res=" + canWin(leap, game));
  }
}
