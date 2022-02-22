/**
 * Author: Eamon Earl
 * Revised: April 12th, 2021
 */

package src;
import java.util.Scanner;

public class Controller{


    public static void main(String[] args){
	    Scanner read = new Scanner(System.in);

	    System.out.println();
	    System.out.println("Exit at any time by typing 'exit' when prompted for a move.");
	    System.out.println();

    	System.out.println("Enter name: ");
		String p_name = read.nextLine();
		View.set_Pname(p_name);

		System.out.println();
		System.out.println();
		System.out.println();

        Board.new_game(2);
	//MoveM.unmark();
		View.display();

	while(!(Board.is_end()) && !(MoveM.did_win())){

	    System.out.print("Make move(up, down, left, right): ");
	    String move = read.nextLine();

	    System.out.println();
	    System.out.println();
	    System.out.println();

	    String ex = "exit";
	    if(move.equalsIgnoreCase(ex)){
			return;
	    }
	    if(String.valueOf(Moves.up).equalsIgnoreCase(move)){
			MoveM.move_up();
	    }

	    else if(String.valueOf(Moves.down).equalsIgnoreCase(move)){
			MoveM.move_down();
	    }

	    else if(String.valueOf(Moves.left).equalsIgnoreCase(move)){
			MoveM.move_left();
	    }

	    else if(String.valueOf(Moves.right).equalsIgnoreCase(move)){
			MoveM.move_right();
	    }

	    else{
			System.out.println("Invalid input. Please input one of the four directional commands as written.");
		continue;
	    }
	    View.display();
	}
	if(MoveM.did_win()){
	    View.print_win();
	}
	else{
	    View.print_end();
	}
	//System.out.println("Would you like to play again? (Y for yes, any other key for no)");
	//String again = read.nextLine();
	//if(again.charAt(0) == new Character('Y'){
	  //  main(new String[] {"a", "b", "c"});
	//}
	
    }



}
