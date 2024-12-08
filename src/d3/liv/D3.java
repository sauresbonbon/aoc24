package d3.liv;

import main.IDX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;

public class D3 implements IDX {
    String inputPath  = "C:\\Users\\nitsc\\Desktop\\aoc2024\\src\\d3\\d4.d5.liv\\input";
    String input;
    long res = 0;
    long res2 = 0;
    @Override
    public void readInput() {
        try {
            this.input = Files.readString(Paths.get(inputPath));

        } catch(IOException e) {
            System.out.println("Error bei readInput()");
        }
    }

    @Override
    public void star1() {
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        res = 0;

        while(matcher.find()) {
            res += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
        }

    }

    @Override
    public void star2() {
        boolean flag = true;
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        res2 = 0;

        while(matcher.find()) {
            if (matcher.group(0).equals("do()")) {
                flag = true;
            }
            else if (matcher.group(0).equals("don't()")) {
                flag = false;
            }
            else {
                if(flag) res2 += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println(String.format("Result1: %s", res));
        System.out.println(String.format("Result2: %s", res2));
    }
}
