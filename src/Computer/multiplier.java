package Computer;

/**
 * This class is used to multiply two longwords using shift add multiplication
 * @author Billy
 *
 */
public class multiplier{

	/**
	 * Function used to multiply two longwords
	 * @param a	first longword
	 * @param b	second longword
	 * @return	returns the longword result of the operation
	 */
	public static longword multiply (longword a, longword b){

		longword result = new longword();
		//copy of a and b so values are not changed
		longword acopy = new longword();
		longword bcopy = new longword();
		acopy.copy(a);
		bcopy.copy(b);
		result.set(0);
		//negative flag
		int nFlag = 0;
		//bits used to set parts of longword
		bit zerobit = new bit();
		bit onebit = new bit();
		onebit.set(1);

		//if a is negative then the flag is incremented
		if(acopy.getSigned() < 0){
			acopy.setBit(31,zerobit);
			nFlag++;
		}
		//if b is negative then the flag is incremented
		if(bcopy.getSigned() < 0){
			bcopy.setBit(31,zerobit);
			nFlag++;
		}


		//algorithm for shift add multiplier
		while(bcopy.getUnsigned() !=0){
			//if the last bit is 1 then result = result + a
			if(bcopy.getBit(0).getValue() !=0){
				result = rippleAdder.add(result,acopy);
			}
			//a is then left shifted by one
			acopy = acopy.leftShift(1);
			//b is right shifted by one
			bcopy = bcopy.rightShift(1);
			//when b is 0 the loop exits
		}

		//if only one of the values is negative the answer is negative
		if(nFlag == 1){
			//sets the sign bit to 1
			result.setBit(31,onebit);
		}
		return result;
	}
}