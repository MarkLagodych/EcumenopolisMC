/*
Tile type IDs can only be ints in the range [0..9]
*/

package org.sgengine.algorithms;

import java.util.ArrayList;

record Position(int x, int y) {
}

public class WFC2D {
    final int N_TILE_TYPES = 10;

    /**
     * <code>horizontalAdjacencies[leftTileType][rightTileType]</code>
     * contains the number of times the leftTileType comes to the left
     * of rightTileType.
     */
    int[][] horizontalAdjacencies;

    /**
     * <code>verticalAdjacencies[topTileType][bottomTileType]</code>
     * contains the number of times the topTileType comes at the top
     * of bottomTileType.
     */
    int[][] verticalAdjacencies;

    public WFC2D(int[][] sample) {
        verticalAdjacencies = new int[N_TILE_TYPES][N_TILE_TYPES];
        horizontalAdjacencies = new int[N_TILE_TYPES][N_TILE_TYPES];
        computeAdjacencies(sample);
    }

    private void computeAdjacencies(int[][] sample) {
        final int SAMPLE_HEIGHT = sample.length;
        final int SAMPLE_WIDTH = sample[0].length;

        for (int row = 0; row < SAMPLE_HEIGHT - 1; row++) {
            for (int col = 0; col < SAMPLE_WIDTH - 1; col++) {
                int tile = sample[row][col];
                int rightTile = sample[row][col + 1];
                int bottomTile = sample[row + 1][col];

                horizontalAdjacencies[tile][rightTile]++;
                verticalAdjacencies[tile][bottomTile]++;
            }
        }
    }

    public int[][] generate(int width, int height) {
        var collapsed = new int[height][width];

        var discovered = new ArrayList<Position>();

        discovered.add(new Position(0, 0));

        return collapsed;
    }

    private void addNeighbours(ArrayList<Position> neighbours, Position p, int width, int height) {
        if (p.x() > 0)
            neighbours.add(new Position(p.x() - 1, p.y()));

        if (p.y() > 0)
            neighbours.add(new Position(p.x(), p.y() - 1));

        if (p.x() < width - 1)
            neighbours.add(new Position(p.x() + 1, p.y()));

        if (p.y() < height - 1)
            neighbours.add(new Position(p.x(), p.y() + 1));
    }
}
