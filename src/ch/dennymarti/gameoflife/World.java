package ch.dennymarti.gameoflife;

public class World {

    private final String RED_COLOR = "\u001B[31m";
    private final String GREEN_COLOR = "\u001B[32m";
    private final String RESET_COLOR = "\u001B[0m";

    private int width;
    private int height;

    private boolean[][] cells;

    public World(int width, int height) {
        this.width = width;
        this.height = height;

        cells = new boolean[width][height];

        setCell(3, 3, true);
        setCell(4, 3, true);
        setCell(5, 3, true);
    }

    public boolean getCell(int cellX, int cellY) {
        return cells[cellX][cellY];
    }

    public void setCell(int cellX, int cellY, boolean condition) {
        cells[cellX][cellY] = condition;
    }

    public String toString(int cellX, int cellY) {
        if (getCell(cellX, cellY)) {
            return GREEN_COLOR + "*" + RESET_COLOR;
        }
        return RED_COLOR + "*" + RESET_COLOR;
    }

    public int getNeighbours(int cellX, int cellY) {
        int neigbours = 0;

        int minOffsetX = Math.max(0, cellX - 1);
        int maxOffsetX = Math.min(width - 1, cellX + 1);
        int minOffsetY = Math.max(0, cellY - 1);
        int maxOffsetY = Math.min(height - 1, cellY + 1);

        for (int offsetX = minOffsetX; offsetX <= maxOffsetX; offsetX++) {
            for (int offsetY = minOffsetY; offsetY <= maxOffsetY; offsetY++) {
                if (cells[offsetX][offsetY]) {
                    neigbours++;
                }
            }
        }
        return neigbours;
    }

    public void nextGeneration() {
        for (int cellX = 0; cellX < width; cellX++) {
            for (int cellY = 0; cellY < height; cellY++) {
                int aliveCells = getNeighbours(cellX, cellY);

                if (getCell(cellX, cellY)) {
                    // Jede lebendige Zelle die weniger als 2 Nachbaren hat stirbt an Einsamkeit
                    // Jede lebendige Zelle die mehr als 3 Nachbaren hat stirbt an Überbevölkerung
                    if (aliveCells < 2 || aliveCells > 3) {
                        setCell(cellX, cellY, false);
                    } else {
                        // Jede lebendige Zelle mit 2 oder 3 Nachbaren fühlt sich wohl und lebt weiter
                        setCell(cellX, cellY, true);
                    }
                    // Jede tote Zelle mit genau drei lebendigen Nachbaren wird wieder zum leben erweckt
                } else {
                    if (aliveCells == 3) {
                        setCell(cellX, cellY, true);
                    } else {
                        setCell(cellX, cellY, false);
                    }
                }
            }
        }
    }
    public void showWorld() {
        for (int cellY = 0; cellY < height; cellY++) {
            for (int cellX = 0; cellX < width; cellX++) {
                System.out.print(toString(cellX, cellY) + "  ");
            }
            System.out.println();
        }
    }
}
