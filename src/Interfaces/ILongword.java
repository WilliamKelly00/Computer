package Interfaces;
import Computer.bit;
import Computer.longword;

/**
 * The longword is a collection of 32 bits with additional helper functions
 */
public interface ILongword {
	/**
	 * Get bit i
	 * @param i	number bit
	 * @return	the bit at index i
	 */
	bit getBit(int i);

	/**
	 * set bit i's value
	 * @param i	index of the bit
	 * @param value	bit containing the value
	 */
	void setBit(int i, bit value);

	/**
	 * and two longwords, returning a third
	 * @param other The other longword used for the operation
	 * @return the longword result of the operation
	 */
	longword and(longword other); 

	/**
	 * or two longwords, returning a third
	 * @param other The other longword used for the operation
	 * @return the longword result of the operation
	 */
	longword or(longword other); // or two longwords, returning a third

	/**
	 * xor two longwords, returning a third
	 * @param other The other longword used for the operation
	 * @return the longword result of the operation
	 */
	longword xor(longword other);// xor two longwords, returning a third

	/**
	 * negate this longword, creating another
	 * @return the longword result of the operation
	 */
	longword not();

	/**
	 * rightshift this longword by amount bits, creating a new longword
	 * @param amount amount for the longword to be shifted
	 * @return the longword result of the shift
	 */
	longword rightShift(int amount);

	/**
	 * leftshift this longword by amount bits, creating a new longword
	 * @param amount amount for the longword to be shifted
	 * @return the longword result of the shift
	 */
	longword leftShift(int amount);

	@Override
	/**
	 * returns a comma separated string of 0's and 1's
	 * @return returns a comma separated string of 0's and 1's
	 */
	String toString();

	/**
	 * the value of this longword as a long
	 * @return returns the value of this longword as a long
	 */
	long getUnsigned();

	/**
	 * returns the value of this longword as an int
	 * @return returns the value of this longword as an int
	 */
	int getSigned();

	/**
	 * copies the values of the bits from another longword into this one
	 * @param other longword that is being copied
	 */
	void copy(longword other);

	/**
	 * set the value of the bits of this longword
	 * @param value	set value of the longword
	 */
	void set(int value);
}
