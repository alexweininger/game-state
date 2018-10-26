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

                editText.setText("\n");
                editText.append("--- First Instance --- \n");
                editText.append(firstInstance.toString());

                editText.append("--- First Instance --- \n");
                firstInstance.rollDice(1, editText);
                firstInstance.tradePort(1, "ore", "wood", editText);
                firstInstance.tradeBank(1, "ore", "wood", editText);
                firstInstance.buildSettlement(1, 2, editText);
                firstInstance.buildCity(1, 7, editText);
                firstInstance.buildRoad(1, 2, 2, editText);
                firstInstance.buyDevCard(1, editText);
                firstInstance.useDevCard(true, editText, 0);
                firstInstance.robberDiscard(true, editText);
                firstInstance.robberMove(true, editText,0,0);
                firstInstance.robberSteal(true, editText,0,0);

                editText.append("\n");
                editText.append("--- First Instance --- \n");
                editText.append(firstInstance.toString());

                editText.append("\n");

                GameState thirdInstance = new GameState();
                GameState fourthInstance = new GameState(thirdInstance);

                editText.append("\n");
                editText.append("--- Second Instance --- \n");
                editText.append(secondInstance.toString());
                editText.append("\n\n");
                editText.append("--- Fourth Instance --- \n");
                editText.append(fourthInstance.toString());
            }
        });

        // developmentCards = new DevelopmentCard(); // TODO wtf is this @DB
    }


    @Override
    public String toString() {
        editText.append(developmentCards.toString() + "\n");
        StringBuilder sb = new StringBuilder("");
        return sb.toString();
    }
}
