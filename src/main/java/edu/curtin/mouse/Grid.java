package edu.curtin.mouse;

import edu.curtin.mouse.exception.GridException;
import edu.curtin.mouse.exception.MoveOutsideException;
import edu.curtin.mouse.exception.MoveToObstacleException;

import java.util.logging.Logger;

public class Grid {
    private Cell[][] array;
    private int mx, my, cx, cy;
    private static Logger logger = Logger.getLogger(Grid.class.getName());

    public final static String UP = "UP";
    public final static String DOWN = "DOWN";
    public final static String LEFT = "LEFT";
    public final static String RIGHT = "RIGHT";
    public final static String EXIT = "EXIT";

    public Grid(int N, int M) {
        array = new Cell[N][M];
    }

    public void set(int x, int y, Cell contents) {
        array[x][y] = contents;
        if (contents instanceof Mouse) {
            mx = x;
            my = y;
        }
        if (contents instanceof Cheese) {
            cx = x;
            cy = y;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]==null) {
                    builder.append(" . ");
                }
                else {
                    builder.append(" " + array[i][j] + " ");
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public void move(String input) throws GridException {
        if (input.equals(UP)) {
            if (mx==0) {
                throw new MoveOutsideException("INVALID MOVE");
            }
            if (array[mx-1][my] instanceof Obstacle) {
                throw new MoveToObstacleException("INVALID");
            }

            array[mx-1][my] = array[mx][my];
            array[mx][my] = null;
            logger.info(String.format("MOUSE MOVED from (%d,%d) to (%d,%d)", mx, my, mx-1, my));
            mx = mx-1;
        }
    }
}
