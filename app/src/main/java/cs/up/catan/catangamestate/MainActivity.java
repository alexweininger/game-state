package cs.up.catan.catangamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	GameState firstInstance;
	GameState secondInstance;
	GameState thirdInstance;
	GameState fourthInstance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button runTest = (Button) findViewById(R.id.runTest);

		firstInstance = new GameState();
		secondInstance = new GameState(firstInstance);
		thirdInstance = new GameState();
		fourthInstance = new GameState(thirdInstance);

		secondInstance.toString();
		fourthInstance.toString();


	}

	@Override
	public String toString() {
		return "";
	}
}
