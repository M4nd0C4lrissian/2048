
/**
 * Author: Eamon Earl
 * Revised: April 12th, 2021
 */

package src; 

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

/**
 * @brief An abstract object that models the 2048 board, with access programs that can both read from and write to said model. 
 * @details This static class represents the board as a two dimensional array of integers, whose dimensions are fixed via the final integer MAX_SIZE. This module also checks for a game over, as the necessary requirements for a 'game over' are entirely dependent on the state of the board. The boolean game_end represents this condition. 
*/
public class Board{

private static boolean game_end;
protected static final int MAX_SIZE = 4;
private static int[][] cells = new int[MAX_SIZE][MAX_SIZE];  

/**
 * @brief Writes an integer value to a given cell in the matrix.
 * @param i The row index of the cell being writen to.
 * @param j The column index of the cell being writen to.
 * @param val The value being written to the cell.
*/
public static void write_cell(int i, int j, int val){ //maybe make these protected
    cells[i][j] = val;
}

/**
 * @brief Writes a random integer value (2 or 4) to a given cell in the matrix.
 * @details Uses the local method gen_num() to generate a 2 or a 4 depending on certain pre-determined weights.
 * @param i The row index of the cell being writen to.
 * @param j The column index of the cell being writen to.
*/
public static void write_ran(int i, int j){
    cells[i][j] = gen_num();
}

/**
 * @brief Accesses an integer value of a given cell in the matrix.
 * @param i The row index of the cell being accessed.
 * @param j The column index of the cell being accessed.
 * @return The integer value stored in the cell.
*/
public static int get_val(int i, int j){
    return cells[i][j];
}

/**
 * @brief Indicates whether or not the game is over (player lost).
 * @return The boolean game_end, that indicates whether or not the player has lost.
*/
public static boolean is_end(){
    return game_end;
}

/**
 * @brief Writes an integer value to an empty (0) cell in the matrix.
 * @details Uses an ArrayList and adds to it arrays of length 2, where the array stores the row and column indexes of a cell index 0 and 1 respectively. The method iterates through the matrix and adds one of these arrays to the ArrayList if the matrix cell being checked is empty. At the end, it uses the imported ThreadLocalRandom method to choose one of these empty cell indices to return at random. If no such empty cell is found, it returns the indices {-1,-1} to represent this.
 * @return A length-two array that contains the indices of an empty cell in the board matrix if one such cell exists. Otherwise it returns the indices of a cell that do not exist in the board matrix.
*/
public static int[] find_cell(){ 
    ArrayList<int[]> L = new ArrayList<int[]>();

    for(int i = 0; i < MAX_SIZE ; i++){
        for(int j = 0 ; j < MAX_SIZE ; j++){
             if(cells[i][j] == 0){
                 L.add(new int[] {i,j});
             }
        }
    }
    if(L.size() == 0){
        return new int[] {-1, -1}; 
    }
    int ind = ThreadLocalRandom.current().nextInt(0, L.size());
    return L.get(ind);
}

/**
 * @brief Resets the board state. 
 * @details Sets all cell values to 0, then uses find_cell and gen_num to insert the starting cell values for a new game. Also resets the game_end boolean to false, as we know that the player hasn't lost when a new game is being started.
 * @param start_cells The number of random cells you want non-zero values written to (standard is 2 for 2048).
*/
protected static void new_game(int start_cells){ 

    for(int i = 0; i < MAX_SIZE ; i++){
        for(int j = 0 ; j < MAX_SIZE ; j++){
            cells[i][j] = 0;
        }
    }

    for(int j = 0 ; j < start_cells ; j++){
        int[] f = find_cell();
        cells[f[0]][f[1]] = gen_num();
    }
    game_end = false;
}

/**
 * @brief Checks whether or not the player has lost, and sets the boolean game_end accordingly.
 * @details This method checks if any valid moves can be made, that is whether or not two adjacent cells have the same value, or if a 0 / empty cell exists in the board. It does so optimally using a 'checkerboard' approach to which cells are checked, ensuring that the same equivalence relation between two adjacent cells is never checked more than once. If one such cell is found, it exits, otherwise, if no valid move is found, the boolean game_end is set to true.
*/
public static void check_end(){  
    int[] m1 = {1,-1,0,0};
    int[] m2 = {0,0,1,-1};
    int x;
    for(int i = 0 ; i < MAX_SIZE ; i++){
	if(i%2 == 0){
	    x = 0;
        }
	else{
	    x = 1;
	}
        for(int j = x ; j < MAX_SIZE ; j = j + 2){
	    for(int k = 0 ; k < m1.length ; k++){
		int c1 = i + m1[k];
		int c2 = j + m2[k];
		if((c1 < 0) || (c1 >= MAX_SIZE) || (c2 < 0) || (c2 >= MAX_SIZE)){
		    continue;
		}

		if((cells[i][j] == 0) || (cells[i][j] == cells[c1][c2])){
		    return;
		}
	    }
	}
    }
    game_end = true;
}

/**
 * @brief A local function that uses predetermined weighting to generate a 2 or a 4 to be spawned into empty cells in the board. 
 * @return The value being written to the cell (2 or 4).
*/
private static int gen_num(){
    double t = 10.0;
    int i = 0;
    double[] weights = new double[] {2.0, 8.0};
    int[] vals = new int[] {4,2};
    for(double ran = Math.random() * t ; i < 2 ; i++){
        ran -= weights[i];
        if(ran <= 0.0) break;
    }
    return vals[i];
}


}


