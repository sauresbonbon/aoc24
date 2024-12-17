package d7.liv;

import main.DX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D7 extends DX {
    String[] equations = input.split("\\R");
    int result = 0;

    List<String> generateCombinations(List<Integer> operands) {
        int n_operators = operands.size() - 1;
        int n_combinations = (int)(Math.pow(2, n_operators));
        List<String> res = new ArrayList<>();

        for(int i = 0; i < n_combinations; i++) {
            String binaryString = Integer.toBinaryString(i);
            String prefix = "0".repeat(n_operators - binaryString.length());
            String finalString = prefix + binaryString;
            String finalfinalString = finalString.replace('0', '+').replace('1', '*');
            res.add(finalfinalString);
        }
        return res;
    }


    @Override
    public void star1() {
        for(String equation : equations) {
            String[] splitted = equation.split(": ");
            //"zahl: zahl zahl zahl" ---> {{zahl}, { zahl zahl zahl zahl}}

            int res = Integer.parseInt(splitted[0]);
            List<Integer> operands = Arrays.stream(splitted[1].split(" ")).map(Integer::parseInt).toList();
            List<String> combinations = generateCombinations(operands); // {"+++", "++*", "+*+", ...}

            System.out.println(combinations);
            

//            for(String combination : combinations) {
//                int temp = operands.get(0);
//                    for (int i = 1; i < combination.toCharArray().length; i++) {
//                        char c = combination.toCharArray()[i];
//
//                        if(c == '+') temp = temp + operands.get(i);
//                        if(c == '*') temp = temp * operands.get(i);
//                    }
//                    System.out.println(res );
//                    System.out.println(temp +  "\n");
//                        if (temp == res) result += temp;
//                }
            }
        }


    @Override
    public void star2() {

    }

    @Override
    public void printResult() {
        System.out.printf(STR."Total calibration result: \{result}");
    }
}
