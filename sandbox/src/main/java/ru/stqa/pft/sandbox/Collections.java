package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){

//    String[] langs = {"Java", "C#", "Python", "PHP"};
//
//    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
//
//
//    for (int i = 0; i < languages.size(); i++){
//      System.out.println("I want to learn " + languages.get(i));
//    }
//
//    System.out.println("----------------------");
//
//    for (String l : languages){
//      System.out.println("I want to learn " + l);
//    }

    // Bubble sort
    int[] numbers = {5, 13, 57, 14, 27, -10, 18, 1};
    boolean needIteration;

    do{
      needIteration = false;
      for (int i=1; i<numbers.length; i++){
        if (numbers[i-1] > numbers[i]){
          numbers[i-1] = numbers[i-1] + numbers[i];
          numbers[i] = numbers[i-1] - numbers[i];
          numbers[i-1] = numbers[i-1] - numbers[i];
          needIteration = true;
        }
      }
    } while (needIteration);

    for (int a : numbers){
      System.out.print(a + " ");
    }

    System.out.println(" ");

    // Insertion sort
    int[] arr = {25, 13, 30, 1, 16, 14, 10, 3};

    for (int i = 0; i < arr.length; i++){
      int element = arr[i];
      int j = i;
      while (j>0 && arr[j-1] > element){
        arr[j] = arr[j-1];
        j--;
      }
       arr[j] = element;
    }

    for (int num : arr){
      System.out.print(num + " ");
    }

    System.out.println(" ");

    // Move all zeros to the end
    int[] a = {1, 2, 0, 0, 3, 0, 4, 0, 5, 6, 0, 0, 0, 7, 0, 0, 8, 9, 0, 10};

    for (int num : a){
      System.out.print(num + " ");
    }

    System.out.println(" ");

    int index = 0;
    int limit = a.length;

    while (index < limit){
      if (a[index] == 0){
        for (int j = index; j<limit-1; j++){
          a[j] = a[j+1];
        }
        limit--;
        a[limit] = 0;
      } else index++;
    }

    for (int num : a){
      System.out.print(num + " ");
    }

    System.out.println(" ");
  }
}
