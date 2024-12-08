package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class DX implements IDX {
    public String input;
    public DX() {
        String className = this.getClass().getSimpleName();
        int day = Integer.parseInt(className.replaceAll("\\D", ""));

        String packageName = this.getClass().getPackage().getName();
        String user = packageName.split("\\.")[1];

        String projectPath = System.getProperty("user.dir");
        String inputPath = String.format("%s/src/d%s/%s/input", projectPath, day, user);

        try {
            input = Files.readString(Paths.get(inputPath));

        } catch(IOException e) {
            System.out.println("Error bei readInput()");
        }
    }
}
