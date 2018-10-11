package cs.up.catan.catangamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	GameState firstInstance;
	GameState secondInstance;
	GameState thirdInstance;
	GameState fourthInstance;
	TextView editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button runTest = (Button) findViewById(R.id.runTest);

		runTest.setOnClickListener();
		editText = (TextView) findViewById(R.id.editText);



	}



	@Override
	public String toString() {
		return "";
	}
}
