/**
 * LAB 3 -  Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import edu.cuny.csi.csc330.util.Randomizer;

public class LottoQuickPicker {
	
	// constants  specific to current game - BUT NOT ALL GAMES 
	public final static int DEFAULT_GAME_COUNT = 1; 
	private final static String GAME_NAME = "Lotto"; 
	private final static int SELECTION_POOL_SIZE = 59; 
	private final static int SELECTION_COUNT = 6; 

	private int[][] gameArr;

	public LottoQuickPicker() {
		init(DEFAULT_GAME_COUNT); 
	}
	
	public LottoQuickPicker(int games) {
		init(games); 
	}
  

	private void init(int games) {
		
		/**
		 * 
		 * Now what ... START FROM HERE 
		 * What additional methods do you need?
		 * What additional data properties/members do you need? 
		 */
		
		gameArr = new int [games][SELECTION_COUNT];
		int numPick = 0;
		
		for (int i = 0; i < gameArr.length; i++) {
			
			// Number of games
			for (int j = 0; j < gameArr[i].length; j++) {
				// Lotto selections
			
				numPick = Randomizer.generateInt(1,SELECTION_POOL_SIZE);
				if (checkDuplicates(gameArr[i], numPick) == false) {
					gameArr[i][j] = numPick; 
				}
				else i--;
				
			}
			
			Arrays.sort(gameArr[i]);
		}

	}
	
	private static boolean checkDuplicates(int[] numArr, int num) {
		for (int i = 0; i < numArr.length; i ++) {
			if(numArr[i] == num )
				return true;
		}
		return false;
	}
	

	/**
	 * 
	 */
	public void displayTicket() {
		
		/**
		 * display heading 
		 * 
		 * for i in gameCount 
		 * 		generate selectionCount number of unique random selections in ascending order 
		 * 
		 * display footer 
		 */
		
		// display ticket heading 
		displayHeading(); 
		
		
		/**
		 * Display selected numbers 
		 */

		for(int i = 0; i < gameArr.length; i ++) {
			System.out.printf("(%2d) ", i+1);
			for (int j = 0; j < gameArr[i].length; j++) {
				System.out.printf("%02d ", gameArr[i][j]);
			}
			System.out.println("");
		}
		
		// display ticket footer 
		displayFooter(); 
		
		return;
	}
	
	protected void displayHeading() {
		
		Date lottoDate = new Date();
		
		System.out.println("------------------------------------");
		System.out.println("---------------" + GAME_NAME.toUpperCase() + "----------------");
		System.out.println("--- " + lottoDate.toString() + " ---\n");
		
	}
	
	protected void displayFooter() {
		
		System.out.println();
		System.out.printf("%-10s % ,10d \n", "Odds of Winning: 1 in", calculateOdds());
		System.out.println("------- (c) S.I. Corner Deli -------");
		System.out.println("------------------------------------");
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	private BigInteger calculateOdds() {
 
		BigInteger top = factorial(SELECTION_POOL_SIZE);
		BigInteger leftBottom = factorial(SELECTION_COUNT);
		BigInteger rightBottom = factorial((SELECTION_POOL_SIZE - SELECTION_COUNT));
		BigInteger bottom = leftBottom.multiply(rightBottom);
		BigInteger odds = top.divide(bottom);
		
		return odds;
		
	}
  
	// Implement factorial method
	
	private BigInteger factorial(int num) {
		
		BigInteger fact = new BigInteger ("1");
		
		for (int i = 2; i <= num; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		return fact;
		
	}
	

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = DEFAULT_GAME_COUNT; 
		
		if(args.length > 0) {  // if user provided an arg, assume it to be a game count
			numberOfGames = Integer.parseInt(args[0]);  // [0] is the 1st element!
		}
		
		LottoQuickPicker lotto = new LottoQuickPicker(numberOfGames);
		// now what 
		 
		lotto.displayTicket(); 
		

	}

}
