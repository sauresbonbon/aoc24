package d1;

import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class D1 {
    public static void main(String[] args) {
        String inputLiv  = "C:\\Users\\nitsc\\Desktop\\aoc2024\\src\\inputTill";
        List<Integer> spalte1 = new Stack<>();
        List<Integer> spalte2 = new Stack<>();
        int distance = 0;
        int similarity = 0;

        try {
            var lines = Files.readAllLines(Paths.get(inputLiv));
            for (String line : lines) {
                var parts = line.split("\\s+");
                spalte1.add(Integer.parseInt(parts[0]));
                spalte2.add(Integer.parseInt(parts[1]));
            }

        } catch(IOException e) {
            System.out.printf("Error");
        }

        spalte2.sort(null);
        spalte1.sort(null);
        Set<Integer> temp = new HashSet<>(spalte1);
        List<Integer> spalte1Unique = new ArrayList<>(temp);

        for (int i = 0; i < spalte1.size(); i++) {

            distance += Math.abs(spalte1.get(i) - spalte2.get(i));
        }

        for(int i = 0; i < spalte1Unique.size(); i++) {
            int count = 0;
            for(int j = 0; j < spalte2.size(); j++) {
                if(spalte1Unique.get(i).equals(spalte2.get(j))) {
                    count++;
                }
            }
            similarity += spalte1Unique.get(i) * count;
        }

        System.out.println(similarity);
        System.out.println(distance);
    }
}
