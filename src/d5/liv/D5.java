package d5.liv;

import main.IDX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class D5 implements IDX {
    String inputPath  = "C:\\Users\\nitsc\\Desktop\\aoc2024\\src\\d5\\liv\\input";
    List<String> input;
    @Override
    public void readInput() {
        try {
            input = Files.readAllLines(Paths.get(inputPath));
        } catch(IOException e) {
            System.out.println("Error bei readInput()");
        }
    }

    @Override
    public void star1() {

    }

    @Override
    public void star2() {

    }

    @Override
    public void printResult() {

    }
}
