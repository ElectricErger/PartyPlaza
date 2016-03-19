package client;

import java.io.File;

import boardPlay.Board;

public class PartyPlaza {

	public static void main(String[] args) {
		Board mainBoard=new Board("Party Plaza", new File("Assets/MapDesc.txt"));
		mainBoard.run();
	}

}
