package com.puzzle;
import static com.puzzle.Utility.findBlank;
import static com.puzzle.Utility.heuristic;
import static com.puzzle.Utility.printAstate;
import static com.puzzle.Utility.recursivePrint;
import static com.puzzle.Utility.same;
import static com.puzzle.Utility.validMove;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;



/**
 * A* Algo
 * @author Victor
 *
 */
public class AStarSearching {
	
	private static Nod startingNode;
	private static Nod destinationNode;
	private static PriorityQueue<Nod> openList;
	private static HashMap<Nod,Integer> closedList;
	private static boolean found;
	
	//A* algorithm
	public static void AStarSolver() {
		int i, j;
		found = false;
		closedList = new HashMap<>();
		openList = new PriorityQueue<Nod>(new Comparator<Nod>() {

			@Override
			public int compare(Nod o1, Nod o2) {
				return o1.getF() - o2.getF();
			}
		});

		openList.add(startingNode);

		if (same(startingNode.getBlackBoard(), destinationNode.getBlackBoard())) {
			printAstate(startingNode.getBlackBoard());
			return;
		}

		while (!openList.isEmpty()) {

			Nod peekNod = openList.poll(); // peek the node with the minimum f
		
			Point blankPos = findBlank(peekNod.getBlackBoard()); 
			i = blankPos.getI();
			j = blankPos.getJ();

			/* N */
			if (found) {
				return;
			}
			validateDirection(blankPos, i - 1, j, peekNod);

			if (found) {
				return;
			}
			/* S */
			validateDirection(blankPos, i + 1, j, peekNod);
			if (found) {
				return;
			}
			/* E */
			validateDirection(blankPos, i, j + 1, peekNod);
			if (found) {
				return;
			}
			/* V */
			validateDirection(blankPos, i, j - 1, peekNod);

			 closedList.put(peekNod, peekNod.getF());

		}
	}

	/**
	 * 
	 * @param blank: blank position
	 * @param newXPOS
	 * @param newYPOS
	 * @param peekNod
	 */
	static void validateDirection(Point blank, int newXPOS, int newYPOS, Nod peekNod) {
		int fNew, gNew, hNew;
		if (validMove(newXPOS, newYPOS)) {
			/* Obtain the blackBoard */
			boolean testerForClosed = false;
			Nod newNod = new Nod(peekNod.getBlackBoardCopy());
			newNod.changeCharcter(blank.getI(), blank.getJ(), newXPOS, newYPOS);

			if (same(newNod.getBlackBoard(), destinationNode.getBlackBoard())) {
				newNod.setParent(peekNod);
				recursivePrint(newNod, startingNode);
				found = true;
				return;// found
			} else {
				hNew = heuristic(newNod.getBlackBoard(), destinationNode.getBlackBoard());
				gNew = peekNod.getG() + 1;
				fNew = hNew + gNew;
				if (!closedList.isEmpty()) {
					if (closedList.containsKey(newNod) && (closedList.get(newNod) < fNew))
						testerForClosed = true;

				}
				if (testerForClosed == false) {
					boolean testedForOpened = false;
					//closedList.put(newNod, fNew); update the close list with the better value -- not sure
					if (!openList.isEmpty()) {
						for (Nod nod : openList) {
							if (same(nod.getBlackBoard(), newNod.getBlackBoard()) && (nod.getF() < fNew))
								testedForOpened = true;
						}
					}
					if (testedForOpened == false) {
						newNod.setF(fNew);
						newNod.setG(gNew);
						newNod.setHeuristic(hNew);
						newNod.setParent(peekNod);
						openList.add(newNod);

					}
				}

			}
		}

	}
	
	
	public static void main(String args[]) {
		
		Character [][] destinationPoint = {
				{'1','2','3'},
				{'8',' ','4'},
				{'7','6','5'}
		};
		
		Character [][] startingPoint = {
				{'2','8','3'},
				{'1','6','4'},
				{'7',' ','5'}
		};
		
		//Init starting and destination node
		startingNode = new Nod();
		startingNode.setBlackBoard(startingPoint);;
		startingNode.setParent(null);
		startingNode.setHeuristic(0);
		startingNode.setF(0);
		startingNode.setG(0);
		destinationNode = new Nod(destinationPoint);
		destinationNode.setHeuristic(0);
		
		long start = System.currentTimeMillis();
		AStarSolver(); 
		System.out.println("\nTime: " + (System.currentTimeMillis() - start));
	}

}
