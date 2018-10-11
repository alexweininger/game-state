package cs.up.catan.catangamestate;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		runTest = (Button) findViewById(R.id.runTest);
		editText = (EditText) findViewById(R.id.editText);
	}

	public void onClick(View view)
	{
		editText.setText("");

		firstInstance = new GameState();
		secondInstance = new GameState(firstInstance);
		thirdInstance = new GameState();
		fourthInstance = new GameState(thirdInstance);

		secondInstance.toString();
		fourthInstance.toString();
	}

	@Override
	public String toString() {
		editText.append("");
		return "";
	}
}
