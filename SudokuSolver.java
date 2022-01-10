public class SudokuSolver {
	
	private static final int DIMENSION = 9;

	public static void main(String[] args) {
		int[][] board = {
				{7, 0, 2, 0, 5, 0, 6, 0, 0},
		        {0, 0, 0, 0, 0, 3, 0, 0, 0},
		        {1, 0, 0, 0, 0, 9, 5, 0, 0},
		        {8, 0, 0, 0, 0, 0, 0, 9, 0},
		        {0, 4, 3, 0, 0, 0, 7, 5, 0},
		        {0, 9, 0, 0, 0, 0, 0, 0, 8},
		        {0, 0, 9, 7, 0, 0, 0, 0, 5},
		        {0, 0, 0, 2, 0, 0, 0, 0, 0},
		        {0, 0, 7, 0, 4, 0, 2, 0, 3} 		   			
		};
		
		if(solveBoard(board) == true) {
			System.out.println("Board solved successfully!\n");
			printBoard(board);
			
		} else {
			System.out.println("Board is unsolvable. Please try again with a valid board.");
		}
	}
	
	private static void printBoard(int[][] board) {
		for(int i=0; i<DIMENSION; ++i) {
			if(i != 0 && (i % 3) == 0) {
				System.out.println("---------------------");
			}
			
			for(int j=0; j<DIMENSION; ++j) {
				if(j != 0 && (j % 3) == 0) {
					System.out.print("| ");
				}
				
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static boolean solveBoard(int[][] board) {
		for(int row=0; row<DIMENSION; ++row) {
			for(int column=0; column<DIMENSION; ++column) {
				
				if (board[row][column] == 0) {
					for(int numCheck=1; numCheck<=DIMENSION; ++numCheck) {
						
						if(checkBoard(board, numCheck, row, column)) {
							board[row][column] = numCheck;
							
							if (solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0;
							}
							
						}
					}
					return false; //When board is unsolvable or one part of recursion fails.
				}
			}
		}
		return true;
	}
	
	private static boolean checkBoard(int[][] board, int num, int row, int column) {
		return !rowCheck(board, num, row) && !columnCheck(board, num, column) &&
				!squareCheck(board, num, row, column);
	}
	
	private static boolean rowCheck(int[][] board, int num, int row) {
		for(int i=0; i<DIMENSION; ++i) {
			if(board[row][i] == num) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean columnCheck(int[][] board, int num, int column) {
		for(int i=0; i<DIMENSION; ++i) {
			if(board[i][column] == num) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean squareCheck(int[][] board, int num, int row, int column) {
		int squareRow = row - (row % 3);
		int squareColumn = column  - (column % 3);
		
		for (int i = squareRow; i<squareRow + 3; ++i) {
			for(int j=squareColumn; j<squareColumn + 3; ++j) {
				if(board[i][j] == num) {
					return true;
				}
			}
		}
		return false;
	}	
}
