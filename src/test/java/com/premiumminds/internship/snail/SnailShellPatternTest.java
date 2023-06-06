package com.premiumminds.internship.snail;

import static com.premiumminds.internship.snail.SnailShellPattern.spiralTraversal;
import static org.junit.Assert.assertArrayEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2023.
 */
@RunWith(JUnit4.class)
public class SnailShellPatternTest {

  @Test
  public void ScreenLockinPatternTestFirst3Length2Test()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    assertArrayEquals(result, expected);
  }

  @Test
  public void SmallMatrix()
          throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1, 2 }, { 8, 9 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1, 2, 9, 8 };
    assertArrayEquals(result, expected);
  }

  @Test
  public void ComparingAlgorithms()
          throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = new int[5000][5000];

    for (int i = 0; i < 5000; i++) {
      for (int j = 0; j < 5000; j++) {
        matrix[i][j] = (int)(Math.random()*100);
      }
    }

    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = spiralTraversal(matrix);
    assertArrayEquals(result, expected);
  }

}