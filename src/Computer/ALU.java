package Computer;

/**
 * This class is used to represent a typical arithmetic-logic unit
 * @author Billy
 */
public class ALU {

	/**
	 * This method takes in the operation code, and two longwords, then performs the operation described by the opcode
	 * 
	 * @param operation 4 bit representation of the operation
	 * @param a	first longword
	 * @param b	second longword
	 * @return	returns the longword result of the operation
	 */

	public static longword doOp(bit[] operation, longword a, longword b){
		//opCode is taken as a string
		String opCode = "" + operation[3].getValue() + operation[2].getValue() + operation[1].getValue()
				+ operation[0].getValue();

		longword result = new longword(); 

		switch(opCode){
		case "1000": //and
			result = a.and(b);
			break;
		case "1001":  //or
			result = a.or(b);
			break;
		case "1010":  //xor
			result = a.xor(b);
			break;
		case "1011": //Not a or b 
			result = (a.or(b)).not();
			break;
		case "1100":  //left shift
			//if b is negative, it is left shifted -b, or right shifted b
			if(b.getSigned() < 0){
				result = a.rightShift(b.getSigned()*-1);
			}
			else
				//if it is positive it is left shifted b
				result = a.leftShift(b.getSigned());
			break;
		case "1101":  //right shift
			//if b is negative, it is right shifted -b, or left shifted b
			if(b.getSigned() < 0){
				result = a.leftShift(b.getSigned()*-1);
			}
			else
				//if it is positive it is right shifted b
				result = a.rightShift(b.getSigned());
			break;
		case "1110":  //add
			result = rippleAdder.add(a,b);
			break;
		case "1111":  //subtract
			result =  rippleAdder.subtract(a,b);
			break;
			//111 is used as 0111 = 111 when using int
		case "0111":  //multiply
			result = multiplier.multiply(a,b);
			break;

		}
		return result;

	}
}