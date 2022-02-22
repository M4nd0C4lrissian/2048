/**
 * Author: Eamon Earl
 * Revised: April 12th, 2021
 */

package src;
import java.lang.Math;

/**
 * @brief A module tasked with displaying all the pertinent information to the user.
 * @details Has two string variables game_name and player_name. The first is final, while the second can be altered via routine access programs.
*/
public class View{

    public static final String game_name = "2048";
    public static String player_name = "unknown";


    /**
     * @brief A method that displays all pertinent information to the user by printing it to the terminal.
     * @details Displays the name of the game, the player name, the current score, and the current board.
    */
    public static void display(){ 
	System.out.println(game_name);
	System.out.println("Player: " + player_name);
	System.out.println("Score: " + String.valueOf(MoveM.get_score()));
	print_board();
    }

    /**
     * @brief Prints the board and all cell values as a MAX_SIZE by MAX_SIZE grid.
     * @details Configured such that the integer val will be centered within the cell if it is represented with an odd number of characters, and will have one extra space in front of them otherwise. This ensures that cell sizes remain constant and the size of the board does not change.
    */
    protected static void print_board(){  
        for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    print_line(3*Board.MAX_SIZE);
	    System.out.println();
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(j == 0){
		    System.out.print("|");
		}
		String val = String.valueOf(Board.get_val(i, j));
		int marg = Math.abs(5 - val.length());
		blanks(marg / 2);
		if(marg % 2  == 1){
		    blanks(1);
		}
		System.out.print(val);
		blanks(marg / 2);
		System.out.print("|");
	    }
	    System.out.println();
	}
	print_line(3*Board.MAX_SIZE);
	System.out.println();
    }

    /**
     * @brief A local function that prints a certain number of blank spaces depending on the given input integer.
     * @param n The number of printed spaces required.
    */
    private static void blanks(int n){
	for(int i = 0 ; i < n ; i++){
	    System.out.print(" ");
	}
    }

    /**
     * @brief A local function that prints "--" a number of times based on the given input.
     * @param n The number of "--" strings printed.
    */
    private static void print_line(int n){
	for(int i = 0 ; i < n ; i++){
	    System.out.print("--");
	}
    }

    /**
     * @brief Sets the player name.
     * @param name The String value to be set as the player name.
    */
    public static void set_Pname(String name){
	player_name = name;
    }

    /**
     * @brief Prints a statement to tell the player that they've won.
    */
    public static void print_win(){
	System.out.println("---------!YOU WIN!---------");
    }

    /**
     * @brief Prints a statement to tell the player that they've lost.
    */
    public static void print_end(){
	System.out.println("---------GAME OVER---------");
    }

}
