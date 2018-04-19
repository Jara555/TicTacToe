package com.jara.tictactoe;

import java.io.Serializable;

import static com.jara.tictactoe.GameState.PLAYER_ONE;

public class Game implements Serializable{

    final private int BOARD_SIZE = 3;
    private Tile[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;

    /* Creates game board filled with blank tiles */
    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = Tile.BLANK;

        playerOneTurn = true;
    }

    /* Draws cross or circle on tile */
    public Tile draw(int row, int column) {
        // only  if tile is blank
        if (board[row][column] == Tile.BLANK) {
            movesPlayed++;

            // draw cross for player 1
            if (playerOneTurn) {
                board[row][column] = Tile.CROSS;
                playerOneTurn = false;

                return Tile.CROSS;
            }
            // draw circle for player 2
            else {
                board[row][column] = Tile.CIRCLE;
                playerOneTurn = true;

                return Tile.CIRCLE;
            }
        }
        // invaldi if tile is not blank
        else {
            return Tile.INVALID;
        }
    }

    /* Returns the game state */
    public GameState gameState(int row, int column) {
        // only relevant if more than 4 moves played
        if (movesPlayed > BOARD_SIZE + 1) {
            // declare winner and tile of enum type GameState and Tile
            GameState winner;
            Tile tile;

            int n = BOARD_SIZE;

            // initialize winner and tile according to player on turn
            if (!playerOneTurn) {
                winner = GameState.PLAYER_ONE;
                tile = Tile.CROSS;
            }
            else {
                winner = GameState.PLAYER_TWO;
                tile = Tile.CIRCLE;
            }

            // check row
            for (int i = 0; i < n; i++) {
                if (board[row][i] != tile) {
                    break;
                }
                if (i == n - 1) {
                    return winner;
                }
            }

            // check column
            for (int i = 0; i < n; i++) {
                if (board[i][column] != tile) {
                    break;
                }
                if (i == n - 1) {
                    return winner;
                }
            }

            // check diagonal
            for (int i = 0; i < n; i++) {
                if (board[i][i] != tile) {
                    break;
                }
                if (i == n - 1) {
                    return winner;
                }
            }

            // check inverse diagonal
            for (int i = 0; i < n; i++) {
                if (board[i][n - 1 - i] != tile) {
                    break;
                }
                if (i == n - 1) {
                    return winner;
                }
            }
        }

        // if tiles are filled and no winner: game over
        if (movesPlayed == BOARD_SIZE * BOARD_SIZE) {
            return GameState.GAME_OVER;
        }

        // if no winner and no game over game is still in progress
        return GameState.IN_PROGRESS;
    }
}