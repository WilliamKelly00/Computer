package Computer;

/**
 * This class is used to add or subtract two longwords using ripple addition
 * @author Billy
 *
 */
public class rippleAdder{

	/**
	 * function to add two longwords results in a new longword
	 * @param a	first longword
	 * @param b	second longword
	 * @return	returns the longword containing the result
	 */
	public static longword add(longword a, longword b){
		longword resultlw = new longword();
		bit carry = new bit();
		bit sum = new bit();
		bit x = new bit();
		bit y = new bit();
		//used to make the equation easier to read
		bit xorbit = new bit();
		bit andbit = new bit();
		//loops through the longword calculating the sum and carry out of every bit
		for(int i = 0; i<32; i++){

			x = a.getBit(i);
			y = b.getBit(i);

			xorbit = x.xor(y);
			andbit = x.and(y);
			//The sum for this bit is calculated by using xor on the bits as well as the carry in
			//sum = (x XOR y) XOR carryIn
			sum = xorbit.xor(carry);
			//The carry out for this bit is determined by using x AND y OR (x xor y AND Carry In)
			//carry out = x AND y OR CarryIn(x XOR y)
			carry = andbit.or(xorbit.and(carry));

			resultlw.setBit(i,sum);
		}
		return resultlw;
	}

	/**
	 * function to subtract two longwords results in a new longword
	 * @param a	first longword
	 * @param b	second longword
	 * @return	returns the longword containing the result
	 */
	public static longword subtract(longword a, longword b){
		longword resultlw = new longword();
		bit borrow = new bit();
		bit diff = new bit();
		bit x = new bit();
		bit y = new bit();
		//used to determine if the result is negative or positive
		int flag = 0;
		//used to make the equation easier to read
		bit xorbit = new bit();

		//loops through every bit in the longword, determining the difference, and what must be borrowed
		for(int i = 0; i<32; i++){
			x = a.getBit(i);
			y = b.getBit(i);

			//if the subtrahend is greater than the minuend the flag that the difference is negative is set
			//The longwords being subtracted are then flipped : a - b = (b - a) * -1
			if(b.getUnsigned() > a.getUnsigned()){
				x = b.getBit(i);
				y = a.getBit(i);
				flag = 1;
			}

			xorbit = x.xor(y);

			// The difference is calculated through using x XOR y XOR Borrow
			diff = xorbit.xor(borrow);

			//The borrow is determined through the equation (not x AND y) OR ((not x XOR y) AND borrow))
			borrow = ((x.not()).and(y)).or((xorbit.not()).and(borrow));

			resultlw.setBit(i,diff);

		}
		if(flag == 1){
			//If the flag has been set to 1 the sign bit (31) is changed to 1
			bit negSign = new bit();
			negSign.set(1);
			resultlw.setBit(31, negSign);
		}
		return resultlw;
	}

}
