package cs.up.catan.catangamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	GameState firstInstance;
	GameState secondInstance;
	GameState thirdInstance;
	GameState fourthInstance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		firstInstance = new GameState();

		thirdInstance = new GameState();

	}

	@Override
	public String toString() {
		return "";
	}
}
