package org.sgengine.algorithms;

public record TilePosition(int row, int column) {
    TilePosition above() {
        return new TilePosition(row - 1, column);
    }

    TilePosition below() {
        return new TilePosition(row + 1, column);
    }

    TilePosition left() {
        return new TilePosition(row, column - 1);
    }

    TilePosition right() {
        return new TilePosition(row, column + 1);
    }

    TilePosition neighbour(NeighbourDirection direction) {
        return switch (direction) {
            case ABOVE -> above();
            case RIGHT -> right();
            case BELOW -> below();
            case LEFT -> left();
        };
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean isInBounds(MapSize mapSize) {
        return row >= 0
            && column >= 0
            && row < mapSize.rows()
            && column < mapSize.cols();
    }
}
