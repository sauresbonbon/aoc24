package d5.liv;

import main.IDX;
import main.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class D5 implements IDX {
    String inputPath  = "C:\\Users\\nitsc\\Desktop\\aoc2024\\src\\d5\\liv\\input";
    String input;
    List<Tuple<Integer, Integer>> rules;
    List<List<Integer>> updates ;
    List<List<Integer>> incorrectUpdates = new ArrayList<>();

    int sum = 0;
    int incorrectSum = 0;
    @Override
    public void readInput() {
        try {
            input = Files.readString(Paths.get(inputPath));
        } catch(IOException e) {
            System.out.println("Error bei readInput()");
        }

        String[] splittedInput = input.split("\\R{2}");

        var rulesStringList = Arrays.asList(splittedInput[0].split("\\R"));
        var updatesStringList = Arrays.asList(splittedInput[1].split("\\R"));

        rules = rulesStringList.stream().map(rule -> {
            var splittedRule = rule.split("\\|");
            int first = Integer.parseInt(splittedRule[0]);
            int second = Integer.parseInt(splittedRule[1]);

            return new Tuple<>(first, second);
        }).collect(Collectors.toList());

        updates = updatesStringList.stream().map(update -> {
            var splittedUpdate = update.split(",");
            return Arrays.stream(splittedUpdate).map(element -> Integer.parseInt(element)).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }

    @Override
    public void star1() {
        for (List<Integer> update : updates) {
            boolean updateValid = true;

            for (Tuple<Integer, Integer> rule : rules) {
                // update: [75,47,61,53,29]
                // rule: [47,53]
                for (int i = 0; i < update.size(); i++) {
                    for (int j = i+1; j < update.size(); j++) {
                        if (update.get(i) == rule.getSecond() && update.get(j) == rule.getFirst()) {
                            updateValid = false;
                            break;
                        }
                    }
                }
            }

            if (updateValid) {
                sum += update.get(update.size()/2);
            }
        }
    }

    @Override
    public void star2() {
        for (List<Integer> update : updates) {
            boolean updateValid = true;

            for (Tuple<Integer, Integer> rule : rules) {
                // update: [75,47,61,53,29]
                // rule: [47,53]
                for (int i = 0; i < update.size(); i++) {
                    for (int j = i+1; j < update.size(); j++) {
                        if (update.get(i) == rule.getSecond() && update.get(j) == rule.getFirst()) {
                            updateValid = false;
                            break;
                        }
                    }
                }
            }
            if (!updateValid) {
                incorrectUpdates.add(update);
            }
        }

        while(!sortList());

        for (List<Integer> update : incorrectUpdates) {
            incorrectSum += update.get(update.size()/2);
        }
    }

    boolean sortList() {
        for (List<Integer> update : incorrectUpdates) {
            for (Tuple<Integer,Integer> rule : rules) {
                for (int i = 0; i < update.size(); i++) {
                    for (int j = i+1; j < update.size(); j++) {
                        if (update.get(i) == rule.getSecond() && update.get(j) == rule.getFirst()) {
                            update.set(i, rule.getFirst());
                            update.set(j, rule.getSecond());
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void printResult() {
        System.out.println(String.format("Sum: %s", sum));
        System.out.println(String.format("Incorrect Sum: %s", incorrectSum));
    }
}
