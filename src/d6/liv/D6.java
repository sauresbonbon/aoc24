package d6.liv;

import main.*;

public class D6 extends DX {
    String[] map = input.split("\\R");
    int rows = map.length;
    int cols = map[0].length();

    Directions direction = Directions.UP;
    int[] position;

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



    @Override
    public void star1() {
        // get guard position
        for(int i = 0; i < map.length; i++) {
            int pos = map[i].indexOf('^');
            if (pos != -1) {
                position = new int[]{i, pos};
                break;
            }
        }



        while(inMap(position, direction)) {
            int[] newPosition = nextPosition(position, direction);
            if (map[newPosition[1]].charAt(newPosition[0]) == '#') {
                direction = direction.nextDirection(direction);
            }
        }

    }

    @Override
    public void star2() {
    }

    @Override
    public void printResult() {
        System.out.println(this.input);
    }
    
}

enum Directions {
    UP(-1,0),
    RIGHT(0,1),
    DOWN(1,0),
    LEFT(0,-1);
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

    public Directions nextDirection(Directions current) {
        int currentPosition = current.ordinal();
        Directions[] allDirections = Directions.values();

        return allDirections[(currentPosition + 1) % 4];
    }
}