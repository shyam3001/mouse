package edu.curtin.mouse;

import edu.curtin.mouse.exception.GridException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MouseInMaze {
    private static Logger logger = Logger.getLogger(MouseInMaze.class.getName());

    public static void main(String[] args) {
        try {
            File file = new File("maze.txt");
            Scanner scanner = new Scanner(file);

            int N = scanner.nextInt();
            int M = scanner.nextInt();
            scanner.nextLine();
            logger.info(String.format("Grid Size N=%d, M=%d", N,M));
            Grid grid = new Grid(N, M);

            int obs = scanner.nextInt();
            scanner.nextLine();
            logger.info(String.format("# of Obstacles %d", obs));

            for (int i = 0; i < obs; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                scanner.nextLine();
                logger.info(String.format("Obstacle %d position: x=%d, x=%d",i,x,y));
                grid.set(x, y, new Obstacle());
            }

            int mx = scanner.nextInt();
            int my = scanner.nextInt();
            scanner.nextLine();
            logger.info(String.format("Mouse Position x=%d, y=%d", mx, my));
            grid.set(mx, my, new Mouse());

            int cx = scanner.nextInt();
            int cy = scanner.nextInt();
            scanner.close();
            logger.info(String.format("Cheese Position x=%d, y=%d", cx, cy));
            grid.set(cx, cy, new Cheese());

            System.out.println(grid);

            scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.next().trim().toUpperCase();
                if (input.equals(Grid.EXIT)) {
                    break;
                }
                grid.move(input);
                System.out.println("------------------");
                System.out.println(grid);
            }
            scanner.close();

        } catch(FileNotFoundException e) {
            System.out.println("File maze.txt is missing");
        }
        catch(GridException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }
}
