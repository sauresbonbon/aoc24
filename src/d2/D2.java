package d2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import main.IDX;

public class D2 implements IDX {
    String input  = "C:\\Users\\nitsc\\Desktop\\aoc2024\\src\\d2\\inputTill";
    List<List<Integer>> reports = new ArrayList<>();
    int safeReportsStar1 = 0;
    int safeReportsStar2 = 0;

    public void readInput() {
        try {
            var lines = Files.readAllLines(Paths.get(input));
            for (String line : lines) {
                var parts = line.split("\\s+");
                reports.add(Arrays.stream(parts).map(Integer::parseInt).collect(Collectors.toList()));
            }

        } catch(IOException e) {
            System.out.printf("Error bei readInput()");
        }
    }

    @Override
    public void star1() {
        outerloop:
        for (List<Integer> report: reports) {
            if (report.size() < 2) continue;
            if(report.get(0) == report.get(1)) continue;

            if (report.get(0) < report.get(1)) {
                for (int i = 0; i < report.size() - 1; i++) {
                    int difference = report.get(i + 1) - report.get(i);

                    if (difference > 3 || difference < 1) {
                        continue outerloop;
                    }
                }
            }

            // descending
            else if (report.get(0) > report.get(1)) {
                for (int i = 0; i < report.size() - 1; i++) {
                    int difference = report.get(i) - report.get(i+1);

                    if (difference > 3 || difference < 1) {
                        continue outerloop;
                    }
                }

            }

            safeReportsStar1++;
        }
    }

    @Override
    public void star2() {
        outerloop:
        for (List<Integer> report: reports) {
            boolean tolerance = true;

            if (report.size() < 2) continue;
            if(report.get(0) == report.get(1)) {
                report.removeFirst();
                tolerance = false;
                if (report.size() >= 3 && report.get(1) == report.get(2))
                    continue ;
            }

            if (report.size() < 2) continue;

            if (report.get(0) < report.get(1)) {
                for (int i = 0; i < report.size() - 1; i++) {
                    int difference = report.get(i + 1) - report.get(i);

                    if (difference > 3 || difference < 1) {
                        if (tolerance) {
                            tolerance = false;
                        } else {
                            continue outerloop;
                        }
                    }
                }
            }

            // descending
            else if (report.get(0) > report.get(1)) {
                for (int i = 0; i < report.size() - 1; i++) {
                    int difference = report.get(i) - report.get(i+1);

                    if (difference > 3 || difference < 1) {
                        if (tolerance) {
                            tolerance = false;
                        } else {
                            continue outerloop;
                        }                    }
                }

            }

            safeReportsStar2++;
        }
    }

    @Override
    public void printResult() {
        System.out.printf("Safe reports: %d\n", safeReportsStar1);
        System.out.printf("Safe reports with tolerance: %d", safeReportsStar2);
    }

}
