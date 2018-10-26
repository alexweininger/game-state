package cs.up.catan.catangamestate;
/**
 * @author: Alex Weininger, Andrew Lang, Daniel Borg, Niraj Mali
 * @version: October 10th, 2018
 * https://github.com/alexweininger/game-state
 **/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button runTest;
    EditText editText;
    DevelopmentCard developmentCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTest = (Button) findViewById(R.id.runTest);
        editText = (EditText) findViewById(R.id.editText);

        editText.setSingleLine(false);


        runTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameState firstInstance = new GameState();
                GameState secondInstance = new GameState(firstInstance);

                editText.setText("");
                editText.append("--- First Instance --- \n\n");
                editText.append(firstInstance.toString());

                editText.append("\n--- First Instance --- \n\n");
                firstInstance.rollDice(0, editText);
                firstInstance.tradePort(0,"Wood", "Ore", editText);
                firstInstance.tradeBank(0, "Sheep","Brick", editText);
                firstInstance.buildSettlement(0, 0, editText);
                firstInstance.buildCity(0, 5, editText);
                firstInstance.buildRoad(0, 9, 0, editText);
                firstInstance.robberDiscard(true, editText);
                //firstInstance.buyDevCard(true, editText, 0);
                firstInstance.useDevCard(true, editText, 0);
                firstInstance.endTurn(true, editText);

                editText.append("\n");
                editText.append("\n--- First Instance --- \n\n");
                editText.append(firstInstance.toString());

                editText.append("\n");

                GameState thirdInstance = new GameState();
                GameState fourthInstance = new GameState(thirdInstance);

                editText.append("\n");
                editText.append("\n--- Second Instance --- \n\n");
                editText.append(secondInstance.toString());
                editText.append("\n\n");
                editText.append("\n--- Fourth Instance --- \n\n");
                editText.append(fourthInstance.toString());
            }
        });

    }


    @Override
    public String toString() {
        editText.append(developmentCards.toString() + "\n");
        StringBuilder sb = new StringBuilder("");
        return sb.toString();
    }
}
