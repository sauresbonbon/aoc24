package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class DX implements IDX {
    String input;
    DX(int day) {
        String projectPath = System.getProperty("user.dir");
        String inputPath = STR."\{projectPath}\\src\\d\{day}\\d5.liv\\input";

        try {
            input = Files.readString(Paths.get(inputPath));

        } catch(IOException e) {
            System.out.println("Error bei readInput()");
        }
    }
}
