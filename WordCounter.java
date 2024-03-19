// Name: RanJeet Pise


package Task2;

import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the sentence to count the words");
        String words = sc.nextLine();

        String[] space = words.split(" ");

        int count = space.length;

        System.out.println("In Your Sentense the number of words is: "+count);

        sc.close();
    }
}

