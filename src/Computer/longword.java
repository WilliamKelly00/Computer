package Computer;
import Interfaces.ILongword;

public class longword implements ILongword{

	private bit[] bitArray = new bit[32];

	public longword(){
		//creates a new longword with an array of bits with length 32
		for(int i = 0; i < 32; i++){
			bitArray[i] = new bit();
		}
	}

	public bit getBit(int i){
		// Get bit i
		return bitArray[i];
	}

	public void setBit(int i, bit value){
		// set bit i's value
		bitArray[i].set(value.getValue());
	}

	public longword and(longword other){
		// and two longwords, returning a third
		longword resultlongword = new longword();
		for(int i = 0; i < 32; i++){
			bit resultBit;
			//does a bitwise and on every bit in the longword
			resultBit = bitArray[i].and(other.getBit(i));
			resultlongword.setBit(i, resultBit);
		}
		return resultlongword;
	}

	public longword or(longword other){
		// or two longwords, returning a third
		longword resultlongword = new longword();
		for(int i = 0; i<32;i++){
			bit resultBit;
			//does a bitwise or on every bit in the longword
			resultBit = bitArray[i].or(other.getBit(i));
			resultlongword.setBit(i, resultBit);
		}
		return resultlongword;
	}

	public longword xor(longword other){
		// xor two longwords, returning a third
		longword resultlongword = new longword();
		for(int i = 0; i<32; i++){
			bit resultBit;
			//does a bitwise xor on every bit in the longword
			resultBit = bitArray[i].xor(other.getBit(i));
			resultlongword.setBit(i, resultBit);
		}
		return resultlongword;
	}

	public longword not(){
		// negate this longword, creating another
		longword resultlongword = new longword();
		for(int i = 0; i < 32; i++){
			bit resultBit;
			//uses the bit's not function on every bit in the longword
			resultBit = bitArray[i].not();
			resultlongword.setBit(i, resultBit);
		}
		return resultlongword;
	}

	public longword leftShift(int amount){
		// rightshift this longword by amount bits, creating a new longword
		longword resultlongword = new longword();
		for(int i = 0; i<32; i++){
			if(!(i + amount >= 32)){
				//checks the bounds and shifts by the specified amount
				resultlongword.setBit(i+amount, bitArray[i]);
			}
		}
		return resultlongword;
	}

	public longword rightShift(int amount){
		// leftshift this longword by amount bits, creating a new longword
		longword resultlongword = new longword();
		for(int i = 31; i>=0; i--){
			if(!(i-amount <0)){
				//checks the bounds and shifts by the specified amount
				resultlongword.setBit(i-amount, bitArray[i]);
			}
		}
		return resultlongword;
	}

	@Override
	public String toString(){
		// returns a comma separated string of 0's and 1's: "0,0,0,0,0 (etcetera)" for example
		String resultString = "";
		for(int i = 31; i>=0; i--){
			resultString = resultString + bitArray[i].toString() + ",";
		}
		return resultString;
	}

	public long getUnsigned(){
		// returns the value of this longword as a long
		long result = 0;
		for(int i = 31; i>= 0; i--){
			if(bitArray[i].getValue() == 1)
				//checks every bit in the longword, if it is a 1, 2 raised to the power of the position is added to the result
				result += Math.pow(2, i);
		}
		return result;
	}


	public int getSigned(){
		// returns the value of this longword as an int
		int result = 0;
		int flag = 0;
		if(bitArray[31].getValue() == 1){
			flag = 1;
			//the leftmost bit is used as the sign, if it is 1 it is negative
		}
		for(int i = 30; i>= 0; i--){
			if(bitArray[i].getValue() == 1)
				result += Math.pow(2, i);
			//checks every bit in the longword, if it is a 1, 2 raised to the power of the position is added to the result
		}
		if(flag == 1)
			result = result * -1;
		return result;  
	}


	public void copy(longword other){
		// copies the values of the bits from another longword into this one
		for(int i = 0; i < 32; i++){
			bitArray[i].set((other.getBit(i)).getValue());
		}
	}

	public void set(int value){
		// set the value of the bits of this longword (used for tests)
		for(int i = 0; i<32; i++){
			bitArray[i].set(value);
		}
	}
}