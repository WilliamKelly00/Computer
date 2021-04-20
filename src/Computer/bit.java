package Computer;
import Interfaces.IBit;


public class bit implements IBit{
	private int value;

	public bit(){
		//bit is initialized to 0
		value = 0;
	}

	public void set(){
		//sets value of bit to 1
		value = 1;
	}

	public void set(int value) {
		//sets the value of the bit
		if(!(this.value > 1 || this.value < 0))
			this.value=value;

	}

	public void toggle() {
		//If the value of the bit is 0, it is set to 1, if the value of the bit is 1, it is set to zero.
		if(value == 0)
			value = 1;
		else
			value = 0;
	}

	public void clear() {
		//sets the bit to zero
		value = 0;
	}

	public int getValue() {
		//returns the value of the bit
		return value;
	}

	public bit and(bit other) {
		// performs and on two bits and returns a new bit set to the result.
		bit resultbit = new bit();
		if(value == other.getValue() && value == 1) {
			//If the values of the two bits are the same the result bit is set to 1.
			resultbit.toggle();
		}
		//otherwise the bit is returned with a value of 0.
		return resultbit;
	}  
	public bit or(bit other) { 
		// performs or on two bits and returns a new bit set to the result
		bit resultbit = new bit();
		if(value == 1 || other.getValue() == 1) {
			//if either bits have a value of 1 the result bit is set to 1
			resultbit.toggle();
		}
		return resultbit;
	}  
	public bit xor(bit other) { 
		// performs xor on two bits and returns a new bit set to the result
		bit resultbit = new bit();
		if(!(value == other.getValue())) {
			//if the values are not the same the result bit is set to 1.
			resultbit.toggle();
		}
		return resultbit;

	}  
	public bit not() { 
		// performs not on the existing bit, returning the result as a new bit
		bit resultbit = new bit();
		if(value == 0)
			//checks if the value of the current bit is 0, if it is then the result bit is set to 1
			resultbit.toggle();
		//if the bit has a value of 1, the result bit is returned with a value of 0
		return resultbit;

	}

	@Override
	public String toString() {
		String result = "" + value;
		return result;

	}
}