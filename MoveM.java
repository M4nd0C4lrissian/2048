/**
 * Author: Eamon Earl
 * Revised: April 12th, 2021
 */

package src;


/**
 * @brief A collection of methods and variables used to model and implement moves on the board.
 * @details Uses an additional two-dimensional array, doubled, that has a size identical to the board, which is used to indicate whether or not a value in any given cell is the result of a merge of two other cells within the same movement function call. The score is also implemented in this method, as the score is added to during movements, when cells get merged together. The boolean value win is also stored here, as a player wins once they get a cell containing the value of 2048, which would be reflected by a score increase of 2048. 
*/
public class MoveM{ //make moves private again after

    private static int score = 0; //see where its best to reset score
    private static boolean win = false;
    private static boolean[][] doubled = new boolean[Board.MAX_SIZE][Board.MAX_SIZE];

    /**
     * @brief Accesses the current player score.
     * @return The value of the private integer static variable score.
    */
    public static int get_score(){
	return score;
    }

    /**
     * @brief Adds a given integer input to the current score.
     * @details If the value being added to the score is 2048, the boolean variable win is set to true.
     * @param x The value to be added to the score.
    */
    protected static void add_score(int x){
	if (x == 2048){
	    win = true;
        }
	score = score + x;
    }

    /**
     * @brief Indicates whether or not the player has won.
     * @return The boolean value stored in the variable win.
    */
    public static boolean did_win(){
	return win;
    }

    /**
     * @brief Marks or unmarks a given cell in the double matrix.
     * @param i The row index of the cell being marked / unmarked.
     * @param j The column index of the cell being marked / unmarked.
     * @param m A boolean to be written to the cell, indicating whether it is marked (true) or unmarked (false).
    */
    private static void mark(int i, int j, boolean m){
	doubled[i][j] = m;
    }

    /**
     * @brief Indicates whether a given cell is marked or unmarked.
     * @param i The row index of the cell being checked.
     * @param j The column index of the cell being checked.
     * @return A boolean value indicating whether or not the cell is marked.
    */
    private static boolean is_marked(int i, int j){
	return doubled[i][j];
    }

    /**
     * @brief Unmarks every cell in the matrix.
    */
    private static void unmark(){
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		doubled[i][j] = false;
	    } 
	}
    }

    /**
     * @brief Moves filled cells upwards.
     * @details Moves cells in the given direction by allowing them to merge with adjacent cells that have the same value, or by taking the place of empty (0) cells, and pushng the cells as far in the given direction as they're able to go. This mutates the doubled matrix so that any cell that was the direct result of a merge cannot be merged into another cell within the same move call. After the moving process is ended, if any cells shifted successfully, an empty cell will be searched for. If found, a random value (2 or 4) will be written to that cell. If no free cell is found, the board is checked for the possibility of a lost game. The doubled array is fully ummarked after every move call.
    */
    protected static void move_up(){
	boolean moved = false;
        for(int i = 1 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		int q = i;
		while(q > 0){	
		    int above = Board.get_val(q-1,j);
		    int cell = Board.get_val(q,j); 
		    if(cell == 0){
			q--;
			continue;
		    }
		    if(!(is_marked(q-1,j)) && !(is_marked(q,j)) && (above == 0 || above == cell)){
			moved = true;
		        Board.write_cell(q-1, j, above + cell);
		        Board.write_cell(q, j, 0);
			mark(q,j,false);
			if(above != 0){
			    add_score(above + cell);
			    mark(q-1, j, true);
			    //break;
			}
		    }
 		    else{
			break;
		    }
		    q--;
		}
		
	    }
	}
	unmark();
        new_val(moved);
    }

    /**
     * @brief Moves filled cells downwards.
     * @details Moves cells in the given direction by allowing them to merge with adjacent cells that have the same value, or by taking the place of empty (0) cells, and pushng the cells as far in the given direction as they're able to go. This mutates the doubled matrix so that any cell that was the direct result of a merge cannot be merged into another cell within the same move call. After the moving process is ended, if any cells shifted successfully, an empty cell will be searched for. If found, a random value (2 or 4) will be written to that cell. If no free cell is found, the board is checked for the possibility of a lost game. The doubled array is fully ummarked after every move call.
    */
    protected static void move_down(){ 
	boolean moved = false;
        for(int i = Board.MAX_SIZE - 2 ; i >= 0 ; i--){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		int q = i; 
		while(q < Board.MAX_SIZE - 1){
		    int below = Board.get_val(q+1,j);
		    int cell = Board.get_val(q,j);
		    if(cell == 0){
			q++;
			continue;
		    }
		    if(!(is_marked(q+1, j)) && !(is_marked(q,j)) && (below == 0 || below == cell)){
			moved = true;
		        Board.write_cell(q+1, j, below + cell);
		        Board.write_cell(q, j, 0);
			mark(q,j,false);
			if(below != 0){
			    add_score(below + cell);
			    mark(q+1,j,true);
			    //break;
			}
		    } 
		    else{
			break;
		    }
		    q++;
		}
	    }
	}
	unmark();
	new_val(moved);
    }

    /**
     * @brief Moves filled cells right.
     * @details Moves cells in the given direction by allowing them to merge with adjacent cells that have the same value, or by taking the place of empty (0) cells, and pushng the cells as far in the given direction as they're able to go. This mutates the doubled matrix so that any cell that was the direct result of a merge cannot be merged into another cell within the same move call. After the moving process is ended, if any cells shifted successfully, an empty cell will be searched for. If found, a random value (2 or 4) will be written to that cell. If no free cell is found, the board is checked for the possibility of a lost game. The doubled array is fully ummarked after every move call.
    */
    protected static void move_right(){ 
	boolean moved = false;
        for(int i = Board.MAX_SIZE - 2 ; i >= 0 ; i--){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		int q = i; 
		while(q < Board.MAX_SIZE - 1){
		    int right = Board.get_val(j,q+1);
		    int cell = Board.get_val(j,q);
		    if(cell == 0){
			q++;
			continue;
		    }
		    if(!(is_marked(j, q+1)) && !(is_marked(j,q)) && (right == 0 || right == cell)){
			moved = true;
		        Board.write_cell(j, q+1, right + cell);
		        Board.write_cell(j, q, 0);
			mark(j,q,false);
			if(right != 0){
			    add_score(right + cell);
			    mark(j, q+1, true);
			    //break;
			}
		    } 
		    else{
			break;
		    }
		    q++;
		}
	    }
	}
	unmark();
	new_val(moved);
    }

    /**
     * @brief Moves filled cells left.
     * @details Moves cells in the given direction by allowing them to merge with adjacent cells that have the same value, or by taking the place of empty (0) cells, and pushng the cells as far in the given direction as they're able to go. This mutates the doubled matrix so that any cell that was the direct result of a merge cannot be merged into another cell within the same move call. After the moving process is ended, if any cells shifted successfully, an empty cell will be searched for. If found, a random value (2 or 4) will be written to that cell. If no free cell is found, the board is checked for the possibility of a lost game. The doubled array is fully ummarked after every move call.
    */
    protected static void move_left(){
	boolean moved = false;
	for(int i = 1 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		int q = i; 
		while(q > 0){
		    int left = Board.get_val(j,q-1);
		    int cell = Board.get_val(j,q);
		    if(cell == 0){
			q--;
			continue;
		    }
		    if(!(is_marked(j, q-1)) && !(is_marked(j,q)) && (left == 0 || left == cell)){
			if(cell != 0){
			    moved = true;
			}
		        Board.write_cell(j, q-1, left + cell);
		        Board.write_cell(j, q, 0);
			mark(j,q,false);
			if(left != 0){
			    add_score(left + cell);
			    mark(j,q-1,true);
			    //break;
			}
		    } 
		    else{
			break;
		    }
		    q--;
		}
	    }
	}
	unmark();
        new_val(moved);
    }

    /**
     * @brief A local function that attempts to find an empty cell to write to. If none are found, it checks for whether or not the player has lost. Only writes to the board if a cell was actually moved by the move function call.
    */
    protected static void new_val(boolean moved){
	int[] found = Board.find_cell(); 
	if(found[0] < 0){
	    Board.check_end(); 
        }				
	else if(moved){
	    Board.write_ran(found[0], found[1]);
        }
    }
}
