package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainG1 {
  private static int[][] sMatrix;
  private static int[] pathMatrix;


  private static void findAndPutNext(int current, int except) {

    for (int k = 0; k < sMatrix[current].length; k++) {
      if (sMatrix[current][k] == 1 && pathMatrix[k] == 0) {
        pathMatrix[k] = 1;
        findAndPutNext(k, current);
      }
    }
  }

  private static boolean canWin(int leap, int[] game) {
    // Return true if you can win the game; otherwise, return false.
    //System.out.println("Original: " + Arrays.toString(game));// + " leap = "+ leap);
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
        }
      }
    }

    findAndPutNext(0, 0);

    for(int z=pathMatrix.length-1; z>=0; z--){
      if(z<pathMatrix.length-(leap==0?1:leap)) return false;
      if(pathMatrix[z]==1) return true;
    }

    return false;
  }

  public static void main(String[] args) {

    Pattern parameters = Pattern.compile("^(\\d{1,4}),(\\d{1,4})$");
    String file = "tc3.data";
    Path inFile = Paths.get(file);

    try(BufferedReader reader = Files.newBufferedReader(inFile, Charset.forName("UTF-8"))
    ){
      String row;
      int currentLeap;
      int arraySize;

      while((row = reader.readLine())!= null){
        Matcher matcher = parameters.matcher(row);
        if(matcher.find()){
          currentLeap = Integer.parseInt(matcher.group(2));
          arraySize = Integer.parseInt(matcher.group(1));

          int[] currentArray = new int[arraySize];
          if((row = reader.readLine())!= null){
            int i = 0;
            for(String s : row.split(",")){
              currentArray[i++]=Integer.parseInt(s);
            }
          }else{
            break;
          }

          System.out.println((canWin(currentLeap, currentArray)?"YES":"NO")/*+" "+matcher.group(0)*/);
        }
      }

    }catch(IOException ioe){
      System.err.format("Try to work with file %s\n", ioe);
    }

                 // 0  1  2  3  4  5  6  7  8  9 10  11 12
    int[] game = //{0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0};
                 //{0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0};
                 //{0, 1, 0, 0, 1, 0, 1, 1, 0}; //3 backward
                 //{0, 1, 1, 0, 0, 1, 0, 0, 0, 1};// 3 backward
                 //{0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0};//5
                 //{0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0};//5
                 //{0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0};//5
                 //{0, 0, 0, 0, 0}; //3
                 //TC3
                 //{0, 0, 0, 0, 0, 1, 0, 1, 0};//41
                 //TC4
                 {0, 0}; //2,0
    int leap = 0;//11;
    //System.out.println("Res=" + canWin(leap, game));
  }
}
