package com.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utility {

	public static final int COL = 3;
	public static final int LARGENUM = 9999;

	public static boolean validMove(int x, int y) {
		if (x < 0 || x >= COL)
			return false;
		if (y < 0 || y >= COL)
			return false;

		return true;
	}

	// check if we reach the final State (the wanted state)
	public static boolean same(Character[][] aState, Character[][] aFinalState) {

		for (int i = 0; i < COL; i++)
			for (int j = 0; j < COL; j++)
				if (aState[i][j] != aFinalState[i][j])
					return false;
		return true;
	}

	//compute heuristic
	public static int heuristic(Character[][] state, Character finalState[][]) {
		int num = 0;

		for (int i = 0; i < COL; i++)
			for (int j = 0; j < COL; j++)
				if (state[i][j] != finalState[i][j])
					num++;

		return num;
	}
	
	//print the board
	public static void printAstate(Character[][] state) {
		StringBuilder matrix = new StringBuilder();
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < COL; j++) {
				matrix.append(String.format("%c ", state[i][j]));
			}
			matrix.append("\n");
		}
		System.out.print(matrix.toString());
		System.out.print("\n");
	}
	
	public static Point findBlank(Character[][] matrix) {
		Point point = new Point();
		for(int i = 0 ;i<COL;i++) {
			for(int j = 0 ;j<COL;j++) {
				if(matrix[i][j]==' ') {
					point.setI(i);
					point.setJ(j);
				}
			}
		}
		
		return point;
	}
	
	public static void recursivePrint(Nod nod,Nod startingPoint) {
		List <Nod> reversedList = new ArrayList<>();
		while(nod != null) {
			reversedList.add(nod);
			nod=nod.getParent();
		}
		Collections.reverse(reversedList);
		for(Nod it:reversedList) {
			printAstate(it.getBlackBoard());
		}
		
	}


}
