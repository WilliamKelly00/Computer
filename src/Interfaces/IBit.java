package Interfaces;
import Computer.bit;

/**
 * The bit class has the functionality of a computer bit with added helper functions
 * @author Billy
 */

public interface IBit {

	/**
	 * sets the value of the bit
	 * @param value	value to set the bit
	 */
	void set(int value);

	/**
	 * changes the value from 0 to 1 or 1 to 0
	 */
	void toggle();	

	/**
	 * sets the bit to 1
	 */
	void set();

	/**
	 * sets the bit to 0
	 */
	void clear();

	/**
	 * returns the current value
	 * @return	returns the value of the bit
	 */
	int getValue(); 

	/**
	 * performs and on two bits and returns a new bit set to the result
	 * @param other	Bit that is being compared
	 * @return	returns the result of the and operation
	 */
	bit and(bit other);

	/**
	 * performs or on two bits and returns a new bit set to the result
	 * @param other	Bit that is being compared
	 * @return	returns the result of the or operation
	 */
	bit or(bit other);

	/**
	 * performs xor on two bits and returns a new bit set to the result
	 * @param other	Bit that is being compared
	 * @return	returns the result of the xor operation
	 */
	bit xor(bit other);

	/**
	 * performs not on the existing bit, returning the result as a new bit
	 * @return	returns a new bit with the result
	 */
	bit not();

	@Override
	String toString(); // returns “0” or “1”
}
