package cs.up.catan.catangamestate;

import java.util.HashMap;

public class GameState {
	int turn  = 0;
	int turnCount = 0;

	// HashMap to store player scored in the form of <String, Integer>.  String will be a unique id.
	HashMap<String, Integer> playerScores = new HashMap<String, Integer>();
	
}
