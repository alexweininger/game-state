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
    DevelopmentCards developmentCards;

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

                editText.setText("--- First Instance --- \n");
                firstInstance.rollDice(true, editText);
                firstInstance.tradePort(true, editText);
                firstInstance.tradeBank(true, editText);
                firstInstance.buildSettlement(true, editText);
                firstInstance.buildCity(true, editText);
                firstInstance.buildRoad(true, editText);
                firstInstance.buyDevCard(true, editText);
                firstInstance.useDevCard(true, editText);
                firstInstance.robberDiscard(true, editText);
                firstInstance.robberMove(true, editText);
                firstInstance.robberSteal(true, editText);

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

        developmentCards = new DevelopmentCards();
    }


    @Override
    public String toString() {
        editText.append(developmentCards.toString() + "\n");
        StringBuilder sb = new StringBuilder("");
        return sb.toString();
    }
}
