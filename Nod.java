package com.puzzle;

import java.util.Arrays;

/**
 * 
 * @author Victor
 * information about a nod
 */

//Each node has a BlackBoard
public class Nod {
	private Integer heuristic,g,f;
	private Character blackBoard[][];
	
	public Nod(Character[][] blackBoard) {
		this.blackBoard = blackBoard;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(blackBoard);
		result = prime * result + ((f == null) ? 0 : f.hashCode());
		result = prime * result + ((g == null) ? 0 : g.hashCode());
		result = prime * result + ((heuristic == null) ? 0 : heuristic.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nod other = (Nod) obj;
		if (!Arrays.deepEquals(blackBoard, other.blackBoard))
			return false;
		return true;
	}

	private Nod parent;
	
	public Integer getG() {
		return g;
	}

	public void setG(Integer g) {
		this.g = g;
	}

	public Integer getF() {
		return f;
	}

	public void setF(Integer f) {
		this.f = f;
	}

	public Nod() {
		f = 0;
		g = 0;
		parent = null;
		heuristic = 0;
	}
	


	public Integer getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(Integer heuristic) {
		this.heuristic = heuristic;
	}

	public Character[][] getBlackBoardCopy() {
		Character [][] blackBoard= new Character[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				blackBoard[i][j] = this.blackBoard[i][j];
		return blackBoard;
	}
	
	public Character[][] getBlackBoard(){
		return this.blackBoard;
	}

	public void setBlackBoard(Character[][] blackBoard) {
		this.blackBoard = blackBoard;
	}

	public Nod getParent() {
		return parent;
	}

	public void setParent(Nod parent) {
		this.parent = parent;
	}
	
	public void changeCharcter(int xPos,int yPos,int xNPos,int yNPos) {
		Character aux = this.blackBoard[xPos][yPos];
		this.blackBoard[xPos][yPos] = this.blackBoard[xNPos][yNPos];
		this.blackBoard[xNPos][yNPos] = aux;
	}
	
	
	



}
