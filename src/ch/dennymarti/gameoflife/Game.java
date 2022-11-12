package ch.dennymarti.gameoflife;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        World world = new World(8, 8);
        for (int generation = 1; generation < 9; generation++) {
            System.out.println("Generation: " + generation);
            world.showWorld();
            world.nextGeneration();
            Thread.sleep(1000);
        }
    }
}
