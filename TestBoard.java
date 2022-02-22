/**
 * Author: Eamon Earl
 * Revised: April 12th, 2021
 * 
 * Description: Testing of the Board.java module using JUnit. 
 */

package src;
import org.junit.*;
import static org.junit.Assert.*;

public class TestBoard
{
 
    @Before
    public void setUp()
    {
	Board.new_game(0);
    }

    @Test
    public void test_write_and_get()
    {
	Board.write_cell(0,1,3);
	Board.write_cell(0,1,5);
        assertTrue(Board.get_val(0,1) == 5);
    }
    @Test
    public void test_write_and_get2()
    {
        assertFalse(Board.get_val(3,3) == 1);
    }
    @Test
    public void test_write_ran()
    {
	Board.write_ran(2,3);
        assertTrue((Board.get_val(2,3) == 2) || (Board.get_val(2,3) == 4));
    }

    @Test
    public void test_is_end()
    {
	assertFalse(Board.is_end());
    }

    @Test
    public void test_check_end()
    {
	int q = 1;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	Board.check_end();
	assertTrue(Board.is_end());
    }
    @Test
    public void test_check_end2()
    {
	Board.check_end();
	assertFalse(Board.is_end());
    }
    @Test
    public void test_check_end3()
    {
	int q = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	Board.check_end();
	assertFalse(Board.is_end());
    }
    @Test
    public void test_find_cell()
    {
	int q = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	int[] cell = Board.find_cell();
	assertTrue(cell[0] == 0 && cell[1] == 0);
    }

    @Test
    public void test_find_cell2()
    {
	int q = 1;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	int[] cell = Board.find_cell();
	assertTrue(cell[0] == -1 && cell[1] == -1);
    }
    @Test
    public void test_find_cell3()
    {
	int[] cell = Board.find_cell();
	assertTrue((cell[0] < Board.MAX_SIZE && cell[0] >= 0) && (cell[1] >= 0 && cell[1] < Board.MAX_SIZE));
    }
    @Test
    public void test_new_game()
    {
	int q = 1;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		Board.write_cell(i,j,q);
		q++;
	    }
	}
	Board.check_end();
	int m = Board.MAX_SIZE*Board.MAX_SIZE;
	Board.new_game(m);
	int[] cell = Board.find_cell();
	assertTrue(cell[0] == -1 && cell[1] == -1 && !(Board.is_end()));
    }
    @Test
    public void test_new_game2()
    {
	Board.new_game(7);
	int count = 0;
	for(int i = 0 ; i < Board.MAX_SIZE ; i++){
	    for(int j = 0 ; j < Board.MAX_SIZE ; j++){
		if(Board.get_val(i,j) != 0){
		    count++;
		}
	    }
	}
	assertTrue(count == 7);
    }
    
}
