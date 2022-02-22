/**
 * Author: Eamon Earl
 * Revised: April 12th, 2021
 * 
 * Description: Testing of the MoveM.java module using JUnit. 
 */

package src;
import org.junit.*;

import static org.junit.Assert.*;

public class TestMoveM
{
 
    @Before
    public void setUp()
    {
	Board.new_game(0);
    }

    @After
    public void tearDown()
    {
    MoveM.add_score(-MoveM.get_score());
    }
    /*
    @Test
    public void test_did_win()
    {
	assertFalse(MoveM.did_win());  //cannot run this test and last test at same time due to junit non sequential testing and I do not have a method to write to the win boolean for obvious reasons (but I know this is correct due to prior tests without last one)
    }
    */
    @Test
    public void test_get_score()
    {
        assertTrue(MoveM.get_score() == 0);
    }

    @Test
    public void test_get_score2()
    {
        assertFalse(MoveM.get_score() == 32);
    }

    @Test
    public void test_add_score()
    {
	MoveM.add_score(1024);
        assertTrue(MoveM.get_score() == 1024);
    }

    @Test
    public void test_move_up()
    {
	int sc = MoveM.get_score();
	Board.write_cell(0,0,2);
	Board.write_cell(0,1,4);
	Board.write_cell(1,0,4);
	MoveM.move_up();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(count == 3 && sc == MoveM.get_score());
    }
    @Test
    public void test_move_up2()
    {
	Board.write_cell(0,0,8);
	Board.write_cell(1,0,8);
	Board.write_cell(2,0,8);
	Board.write_cell(3,0,8);
	Board.write_cell(3,3,2);
	MoveM.move_up();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(Board.get_val(0,0) == 16 && Board.get_val(1,0) == 16 && MoveM.get_score() == 32 && count == 4 && Board.get_val(0,3) == 2);
    }

    @Test
    public void test_move_up3()
    {
	int q = 1;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	MoveM.move_up();
	assertTrue(Board.is_end());
    }

     @Test
    public void test_move_down()
    {
	int sc = MoveM.get_score();
	Board.write_cell(3,0,2);
	Board.write_cell(3,1,4);
	Board.write_cell(2,0,4);
	MoveM.move_down();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(count == 3 && sc == MoveM.get_score());
    }
    @Test
    public void test_move_down2()
    {
	Board.write_cell(0,0,8);
	Board.write_cell(1,0,8);
	Board.write_cell(2,0,8);
	Board.write_cell(3,0,8);
	Board.write_cell(0,3,2);
	MoveM.move_down();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(Board.get_val(3,0) == 16 && Board.get_val(2,0) == 16 && MoveM.get_score() == 32 && count == 4 && Board.get_val(3,3) == 2);
    }

    @Test
    public void test_move_down3()
    {
	int q = 1;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	MoveM.move_down();
	assertTrue(Board.is_end());
    }

   @Test
    public void test_move_right()
    {
	int sc = MoveM.get_score();
	Board.write_cell(0,3,2);
	Board.write_cell(1,3,4);
	Board.write_cell(0,2,4);
	MoveM.move_right();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(count == 3 && sc == MoveM.get_score());
    }
    @Test
    public void test_move_right2()
    {
	Board.write_cell(0,0,8);
	Board.write_cell(0,1,8);
	Board.write_cell(0,2,8);
	Board.write_cell(0,3,8);
	Board.write_cell(3,0,2);
	MoveM.move_right();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(Board.get_val(0,3) == 16 && Board.get_val(0,2) == 16 && MoveM.get_score() == 32 && count == 4 && Board.get_val(3,3) == 2);
    }

    @Test
    public void test_move_right3()
    {
	int q = 1;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	MoveM.move_right();
	assertTrue(Board.is_end());
    }

    @Test
    public void test_move_left()
    {
	int sc = MoveM.get_score();
	Board.write_cell(0,0,2);
	Board.write_cell(1,0,4);
	Board.write_cell(0,1,4);
	MoveM.move_left();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(count == 3 && sc == MoveM.get_score());
    }
    @Test
    public void test_move_left2()
    {
	Board.write_cell(0,0,8);
	Board.write_cell(0,1,8);
	Board.write_cell(0,2,8);
	Board.write_cell(0,3,8);
	Board.write_cell(3,3,2);
	MoveM.move_left();
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(Board.get_val(0,0) == 16 && Board.get_val(0,1) == 16 && MoveM.get_score() == 32 && count == 4 && Board.get_val(3,0) == 2);
    }

    @Test
    public void test_move_left3()
    {
	int q = 1;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	MoveM.move_left();
	assertTrue(Board.is_end());
    }

    
    @Test
    public void test_winning_move()
    {
	Board.write_cell(0,0,1024);
	Board.write_cell(0,1,1024);
	MoveM.move_right();
	assertTrue(MoveM.did_win());
    }
    

}
