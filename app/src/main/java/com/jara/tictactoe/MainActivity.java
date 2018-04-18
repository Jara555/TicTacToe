package com.jara.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected int[] tileIds = new int[]{R.id.b00, R.id.b01, R.id.b02, R.id.b10,
            R.id.b11, R.id.b12, R.id.b20, R.id.b21, R.id.b22};

    protected Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void tileClicked(View view) {

        int id = view.getId();

        Button b = (Button)findViewById(id);

        int[] coordinates = getCoordinates(id);

        Tile tile = game.draw(coordinates[0], coordinates[1]);

        switch (tile) {
            case CROSS:
                b.setText("X");
                break;
            case CIRCLE:
                b.setText("O");
                break;
            case INVALID:
                b.setText("-");
                break;
        }
    }

    public void resetClicked(View view) {
        game = new Game();

        for (int id : tileIds) {
            Button b = (Button)findViewById(id);
            b.setText("");
        }
    }


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