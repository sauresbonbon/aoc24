package d4.liv;

import main.IDX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class D4 implements IDX {
    String inputPath  = "C:\\Users\\nitsc\\Desktop\\aoc2024\\src\\d4\\d5.liv\\input";
    List<String> input;
    int horizontal, vertical, diagonal = 0;
    int hits1 = 0;
    int hits2 = 0;

    public void readInput() {
        try {
            input = Files.readAllLines(Paths.get(inputPath));
        } catch(IOException e) {
            System.out.println("Error bei readInput()");
        }
    }

    List<String> invertMatrix(List<String> matrix) {
        // Die Anzahl der Spalten in der Matrix
        int numColumns = matrix.get(0).length();

        // Liste, die die invertierte Matrix enthält
        List<String> invertedMatrix = new ArrayList<>();

        // Für jede Spalte in der ursprünglichen Matrix
        for (int col = 0; col < numColumns; col++) {
            StringBuilder sb = new StringBuilder();
            // Für jede Zeile (jeder String) in der Matrix, füge das Zeichen an der Spaltenposition hinzu
            for (String row : matrix) {
                sb.append(row.charAt(col));
            }
            invertedMatrix.add(sb.toString());
        }

        return invertedMatrix;
    }

    @Override
    public void star1() {
        // Horizontal
        for (var line : input) {
            for (int i = 0; i < line.length()-3; i++) {
                String checkedString = line.substring(i, i + 4);
                if(checkedString.equals("XMAS") || checkedString.equals("SAMX")) {
//                    System.out.println(checkedString + "line: " + input.get(i) + "|| horizontal");
                    horizontal++;
                }
            }
        }
        
        // Vertical
        for (var line : invertMatrix(input)) {
            for (int i = 0; i < line.length()-3; i++) {
                String checkedString = line.substring(i, i + 4);
                if(checkedString.equals("XMAS") || checkedString.equals("SAMX")) {
//                    System.out.println(checkedString + "line: " + invertMatrix(input).get(i)+ "|| vertikal");
                    vertical++;
                }
            }
        }

        // Diagonal

        for (int y = 0; y < input.size()-3; y++) {
            for (int x = 0; x < (input.get(y).length() - 3); x++) {
                StringBuilder sb = new StringBuilder();
                sb.append(input.get(y).charAt(x));
                sb.append(input.get(y+1).charAt(x+1));
                sb.append(input.get(y+2).charAt(x+2));
                sb.append(input.get(y+3).charAt(x+3));
                String s = sb.toString();
                if (s.equals("XMAS") || s.equals("SAMX")) {
//                    System.out.println(s + "line: " + input.get(y)+ "|| diagonal");
                    diagonal++;
                }
            }
        }

        // Diagonal "Inverted"
        for (int y = input.size()-1; y >= 3; y--) {
            for (int x = 0; x < input.get(y).length()-3; x++) {
                StringBuilder sb = new StringBuilder();
                sb.append(input.get(y).charAt(x));
                sb.append(input.get(y-1).charAt(x+1));
                sb.append(input.get(y-2).charAt(x+2));
                sb.append(input.get(y-3).charAt(x+3));
                String s = sb.toString();
                if (s.equals("XMAS") || s.equals("SAMX")) {
//                    System.out.println(s + "line: " + input.get(y) + "|| diagonal.inverted");
                    diagonal++;
                }
            }
        }

        hits1 = horizontal + vertical + diagonal;
    }

    @Override
    public void star2() {
        for (int y = 0; y < (input.size() - 2); y++) {
            for (int x = 0; x < (input.get(y).length()-2); x++) {
                StringBuilder sb = new StringBuilder();
                //M1
                sb.append(input.get(y).charAt(x));
                //M2
                sb.append(input.get(y+2).charAt(x));
                //A
                sb.append(input.get(y+1).charAt(x+1));
                //S1
                sb.append(input.get(y).charAt(x+2));
                //S2
                sb.append(input.get(y+2).charAt(x+2));

                String s = sb.toString();

                if (s.equals("MMASS") || s.equals("SSAMM") || s.equals("SMASM") || s.equals("MSAMS")) {
                    hits2++;
                }

            }
        }



    }

    @Override
    public void printResult() {
        System.out.printf("XMAS occurs %d times!\n", hits1);
        System.out.printf("X-MAS occurs %d times!", hits2);
    }
}
