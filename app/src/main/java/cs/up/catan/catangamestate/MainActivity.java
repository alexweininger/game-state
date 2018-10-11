package cs.up.catan.catangamestate;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	GameState firstInstance;
	GameState secondInstance;
	GameState thirdInstance;
	GameState fourthInstance;
	Button runTest;
	EditText editText;
	DevelopmentCards developmentCards;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		runTest = (Button) findViewById(R.id.runTest);
		editText = (EditText) findViewById(R.id.editText);

		firstInstance = new GameState();
		secondInstance = new GameState(firstInstance);
		thirdInstance = new GameState();
		fourthInstance = new GameState(thirdInstance);

		runTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				editText.setText("");

				editText.append(secondInstance.toString());
				editText.append(fourthInstance.toString());
			}
		});

		developmentCards = new DevelopmentCards();
	}


	@Override
	public String toString() {
		editText.append(developmentCards.toString());
		return "";
	}
}
