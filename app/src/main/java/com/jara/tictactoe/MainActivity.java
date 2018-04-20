/****************************************************************************
 * MainActivity.java
 *
 * appstudio mprog
 * Jara Linders
 * 20-04-2018
 *
 * This program implements the TicTacToe app for android phones. Together
 * with the Game class this program entails all methods and functions for
 * a two player game.
 ***************************************************************************/

package com.jara.tictactoe;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // create class variable with tile id's
    protected int[] tileIds = new int[]{R.id.b00, R.id.b01, R.id.b02, R.id.b10,
            R.id.b11, R.id.b12, R.id.b20, R.id.b21, R.id.b22};

    // declare the game of type Game
    protected Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText("");
    }

    /* Manages tiles and checks game state */
    @SuppressLint("SetTextI18n")
    public void tileClicked(View view) {
        TextView text = (TextView) findViewById(R.id.textView);

        // only execute when winner text is empty
        if (text.getText() == "") {
            // initialize button and get its coordinates
            Button b = (Button) view;
            int[] coordinates = getCoordinates(b.getId());

            // retrieve tile/draw status and set button text
            Tile tile = game.draw(coordinates[0], coordinates[1]);
            switch (tile) {
                case CROSS:
                    b.setText("X");
                    break;
                case CIRCLE:
                    b.setText("O");
                    break;
                case INVALID:
                    break;
            }

            // retrieve game status and set winner text
            GameState gamestate = game.gameState(coordinates[0], coordinates[1]);
            switch (gamestate) {
                case PLAYER_ONE:
                    text.setText("Player 1 wins!");
                    break;
                case PLAYER_TWO:
                    text.setText("Player 2 wins!");
                    break;
                case GAME_OVER:
                    text.setText("Game Over...");
                    break;
            }

            // if game is finished show play again button and winner text
            if (gamestate != GameState.IN_PROGRESS) {
                Button reset = (Button) findViewById(R.id.Reset);
                reset.setText("Play again");
            }
        }
    }

    /* Resets the game and layout */
    public void resetClicked(View view) {
        // empty game
        game = new Game();

        // if winner text is present make it empty again
        TextView text = (TextView) findViewById(R.id.textView);
        if (text.getText() != "") {
            Button reset = (Button) view;
            reset.setText("Reset");
            text.setText("");
        }

        // empty all button texts
        for (int id : tileIds) {
            Button b = (Button) findViewById(id);
            b.setText("");
        }
    }

    /* Stores information in bundle */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // iterate over button id's
        for (int id : tileIds) {
            // retrieve button texts and store in bundle
            Button b = (Button) findViewById(id);
            outState.putString(Integer.toString(id), (String) b.getText());
        }

        // retrieve winner text and store in bundle
        TextView text = (TextView) findViewById(R.id.textView);
        outState.putString(Integer.toString(R.id.textView), (String) text.getText());

        // retrieve text reset button and store in bundle
        Button reset = (Button) findViewById(R.id.Reset);
        outState.putString(Integer.toString(R.id.Reset), (String) reset.getText());

        // store game in bundle
        outState.putSerializable("game", game);
    }

    /* Restores information out of bundle */
    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // iterate over button id's
        for (int id : tileIds) {
            // retrieve button texts and restore
            Button b = (Button) findViewById(id);
            b.setText(inState.getString(Integer.toString(id)));
        }

        // retrieve winner text and restore
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(inState.getString(Integer.toString(R.id.textView)));

        // retrieve text reset button and restore
        Button reset = (Button) findViewById(R.id.Reset);
        reset.setText(inState.getString(Integer.toString(R.id.Reset)));

        // restore game info
        game = (Game) inState.getSerializable("game");
    }

    /* Returns coordinates of tiles based on input id */
    public int[] getCoordinates(int id) {
        switch (id) {
            case R.id.b00:
                return new int[]{0, 0};
            case R.id.b01:
                return new int[]{0, 1};
            case R.id.b02:
                return new int[]{0, 2};
            case R.id.b10:
                return new int[]{1, 0};
            case R.id.b11:
                return new int[]{1, 1};
            case R.id.b12:
                return new int[]{1, 2};
            case R.id.b20:
                return new int[]{2, 0};
            case R.id.b21:
                return new int[]{2, 1};
            case R.id.b22:
                return new int[]{2, 2};
            default:
                return new int[]{-1, -1};
        }
    }
}