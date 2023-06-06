package com.premiumminds.internship.snail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

  public static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    return executor.submit(() -> {
      long init = System.currentTimeMillis();
      int[] result = new int[matrix.length*matrix.length];

      if(matrix.length != 0 && matrix.length == matrix[0].length) {
        int size = matrix[0].length;

        int[] lengths = getLengths(size);

        int x = 0;
        int y = 0;

        int directionIndex = 0;

        int next = 0;
        for (int length : lengths) {

          int[] currentDirection = DIRECTIONS[directionIndex % DIRECTIONS.length];

          int newX = x;
          int newY = y;

          for (int j = 0; j < length; j++) {
            result[next++] = matrix[newY][newX];

            newX += currentDirection[0];
            newY += currentDirection[1];
          }

          //For the next iteration we change the beginning of the next line
          if (currentDirection[0] > 0) {
            x += length - 1;
            y += 1;
          } else if (currentDirection[0] < 0) {
            x -= length - 1;
            y -= 1;
          } else if (currentDirection[1] > 0) {
            x -= 1;
            y += length - 1;
          } else {
            x += 1;
            y -= length - 1;
          }

          directionIndex++;
        }

      } else {
        result = new int[0];
      }
      System.out.println("(getSnailShell) This iteration took : "+(System.currentTimeMillis()-init)+" milliseconds");

      return result;
    });
  }

  /**
   * This function calculates the lengths of the parts of the snail
   * @param length the length of one side of a square matrix
   * @return an array of the lengths of every part of the snail,
   * for example, if the snail is a 3x3 matrix 'length' must be 3
   * and the result is [3, 2, 2, 1, 1].
   */
  public static int[] getLengths(int length) {
    int[] lengths = new int[length*2-1];
    for(int i=0; length > 0; i++, length--) {
      lengths[i] = length;

      if(i != 0)
        lengths[++i] = length;
    }
    return lengths;
  }

  /**
   * another algorithm that I found online, just to compare
   * @param matrix a square matrix
   * @return an array of a matrix unrolled
   */
  public static int[] spiralTraversal(int[][] matrix) {
    long init = System.currentTimeMillis();
    int[] res = new int[matrix.length*matrix.length];
    if (matrix.length == 0) {
      return res;
    }

    int next = 0;

    int rowBegin = 0;
    int rowEnd = matrix.length - 1;
    int colBegin = 0;
    int colEnd = matrix[0].length - 1;

    while (rowBegin <= rowEnd && colBegin <= colEnd) {
      // Traverse right
      for (int i = colBegin; i <= colEnd; i++) {
        res[next++] = matrix[rowBegin][i];
      }
      rowBegin++;

      // Traverse down
      for (int i = rowBegin; i <= rowEnd; i++) {
        res[next++] = matrix[i][colEnd];
      }
      colEnd--;

      // Traverse left
      if (rowBegin <= rowEnd) {
        for (int i = colEnd; i >= colBegin; i--) {
          res[next++] = matrix[rowEnd][i];
        }
        rowEnd--;
      }

      // Traverse up
      if (colBegin <= colEnd) {
        for (int i = rowEnd; i >= rowBegin; i--) {
          res[next++] = matrix[i][colBegin];
        }
        colBegin++;
      }
    }

    System.out.println("(spiralTraversal) This iteration took : "+(System.currentTimeMillis()-init)+" milliseconds");
    return res;
  }
}
