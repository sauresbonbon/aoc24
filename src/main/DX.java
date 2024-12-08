package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class DX implements IDX {
    String input;
    DX(int day) {
        String projectPath = System.getProperty("user.dir");
        String inputPath = String.format("../%s", projectPath);

        try {
            input = Files.readString(Paths.get(inputPath));

        } catch(IOException e) {
            System.out.println("Error bei readInput()");
        }
    }
}
