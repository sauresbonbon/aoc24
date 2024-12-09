package d6.liv;

import main.*;
import org.w3c.dom.html.HTMLImageElement;

public class D6 extends DX {
    String[] map = input.split("\\R");
    int rows = map.length;
    int cols = map[0].length();

    Directions guardDirection = Directions.UP;
    int[] guardPosition;

    int visited = 0; //ist am Anfang auf 1, da der Startpunkt des Guards hinzugerechnet werden muss
    int loopHoles = 0;

    boolean inMap(int[] position, Directions direction) {
        int[] newPosition = nextPosition(position, direction);
        if (newPosition[0] < 0 || newPosition[0] >= cols || newPosition[1] < 0 || newPosition[1] >= rows) {
            return false;
        }
        return true;
    }

    int[] nextPosition(int[] position, Directions direction) {
        return new int[] {position[0] + direction.getX(), position[1] + direction.getY()};
    }

    void printMap() {
        for (String row : map) {
            System.out.println(row);
        }
        System.out.println("\n");
    }

    void setPoint(int[] position, char c) {
        StringBuilder sb = new StringBuilder(map[position[1]]);
        sb.setCharAt(position[0], c);
        map[position[1]] = sb.toString();
    }

    @Override
    public void star1() {
        // get guard position
        for(int i = 0; i < map.length; i++) {
            int pos = map[i].indexOf('^');
            if (pos != -1) {
                guardPosition = new int[]{pos, i};
                setPoint(guardPosition, 'x');
                break;
            }
        }


        while(inMap(guardPosition, guardDirection)) {
            int[] newPosition = nextPosition(guardPosition, guardDirection);
            if (map[newPosition[1]].charAt(newPosition[0]) == '#') {
                guardDirection = guardDirection.nextDirection();
                continue;
            }

            guardPosition = newPosition;
            setPoint(newPosition, 'x');

        }

        for (String row : map) {
            for(char cell : row.toCharArray()) {
                if (cell == 'x') {
                    visited++;
                }
            }
        }
    }

    void resetMap() {
        map = input.split("\\R"); // map reset
    }

    boolean timeOutWalk() {
        // get guard position
        for(int i = 0; i < map.length; i++) {
            int pos = map[i].indexOf('^');
            if (pos != -1) {
                guardPosition = new int[]{pos, i};
                break;
            }
        }

        boolean timeout = false;
        long startTime = System.currentTimeMillis();

        while(inMap(guardPosition, guardDirection) && !timeout) {
            timeout = System.currentTimeMillis() - startTime > 500;

            int[] newPosition = nextPosition(guardPosition, guardDirection);
            if (map[newPosition[1]].charAt(newPosition[0]) == '#') {
                guardDirection = guardDirection.nextDirection();
                continue;
            }

            guardPosition = newPosition;

        }
        return timeout;
    }

    @Override
    public void star2() {
        resetMap();
        for(int row = 0; row < map.length; row++) {
            for(int col = 0; col < map[row].length(); col++) {
                if(map[row].charAt(col) != '#' && map[row].charAt(col) != '^') {
                    setPoint(new int[] {col, row}, 'x');
                    boolean timeout = timeOutWalk();
                    if (timeout)
                        loopHoles++;
                }
                resetMap();
            }
        }
    }

    @Override
    public void printResult() {
        System.out.printf("Besuchte Punkte: %d\n", visited);

        System.out.printf("Loopholes: %d", loopHoles);
    }
}

enum Directions {
    UP(0,-1),
    RIGHT(1,0),
    DOWN(0,1),
    LEFT(-1,0);
    private final int x, y;

    Directions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Directions nextDirection() {
        int currentPosition = this.ordinal();
        Directions[] allDirections = Directions.values();

        return allDirections[(currentPosition + 1) % 4];
    }
}