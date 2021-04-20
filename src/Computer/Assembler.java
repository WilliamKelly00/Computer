package Computer;

/**
 * This class is used to convert string instructions into their bit representations
 * @author Billy
 *
 */
public class Assembler{

	/**
	 * function that accepts a string containing the register number and returns the number stored inside the string
	 * @param regString	string containing the register number
	 * @return	register number stored inside the string
	 */
	public static String getRegNum(String regString) {
		String regNum;
		if(regString.length() > 2){
			regNum = String.valueOf(regString.charAt(1)) + String.valueOf(regString.charAt(2));
		}
		else
			regNum = String.valueOf(regString.charAt(1)); 

		return regNum;
	}

	/**
	 * method takes in a integer to be converted to binary, and another integer marking the required length for the binary string
	 * @param num	integer to be converted to binary
	 * @param boundry	length of the binary string 
	 * @return returns a string containing the binary result
	 */
	public static String getBinary(int num, int boundry) {

		String bString = Integer.toBinaryString(num);
		while(bString.length() < boundry) {
			bString = "0" + bString;
		}

		//using a string builder to reverse the binary string
		StringBuilder BuilderbString = new StringBuilder(bString);
		BuilderbString = BuilderbString.reverse();

		//places a space in the string every 4 spaces, if the length required is odd the least significant bits are not formatted in sets of four because the string was reversed
		bString = (BuilderbString.toString()).replaceAll("....(?!$)", "$0 ");

		//the formatted string is then reversed
		StringBuilder BuilderResult = new StringBuilder(bString);
		BuilderResult = BuilderResult.reverse();

		return BuilderResult.toString();

	}


	/**
	 * Main method to parse the assembly to binary
	 * @param strarray string to be parsed
	 * @return returns the binary representation of the command
	 */
	public static String[] assemble(String[] strarray){

		//creates a identically sized result string array to hold the parsed instructions
		String[] result = new String[strarray.length];


		for(int i = 0; i< strarray.length; i++) {

			//String array to hold the current string being parsed
			String[] currentString = strarray[i].split("\\s+");

			//if the first string contains branch then the opCode is changed
			//a if statement is used to see if the first string contains branch, as there is not one exact branch statement
			if((currentString[0].toLowerCase()).contains("branch")) {
				result[i] = "0101";
				//the rest of the result string is created using what is contained in the branch statement
				if((currentString[0].toLowerCase()).contains("greater"))
					result[i] += " " + "1";
				else
					result[i] += " " + "0";
				if((currentString[0].toLowerCase()).contains("equal")) 
					result[i] += "1";
				else
					result[i] += "0";
				if(currentString[1].contains("-")) {
					result[i] += "1";
				}
				else
					result[i] += "0";

				result[i] += getBinary(Math.abs(Integer.parseInt(currentString[1])),9);


			}


			//Switching on the OpCode
			switch(currentString[0].toLowerCase()){
			case "push":
				result[i] = "0110 0000 0000";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				break;
			case "pop":
				result[i] = "0110 0100 0000";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				break;
			case "call":
				result[i] = "0110 10";
				//The address is converted into binary, formatted then attached to the opCode and command code
				result[i] += getBinary(Integer.parseInt(currentString[1]),10);
				break;
			case "return":
				result[i] = "0110 1100 0000 0000";
				break;

			case "move":
				//sets the opCode and places it into the instructions matching spot in the result array
				result[i] = "0001";
				//decodes the register
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])), 4);

				//decodes the number and adds the sign bit
				if(Integer.parseInt(currentString[2]) > 0)
					result[i] += " " + "0" + getBinary(Math.abs(Integer.parseInt(currentString[2])), 7);
				else
					result[i] += " " + "1" + getBinary(Math.abs(Integer.parseInt(currentString[2])), 7);

				break;
			case "halt":
				//sets instruction to zero
				result[i] = "0000 0000 0000 0000";
				break;
			case "interrupt":
				result[i] = "0010";
				//used to see which type of interrupt was called
				if(currentString[1].charAt(0) == '1' ) {
					result[i] += " 0000 0000 0001";
				}
				else
					result[i] += " 0000 0000 0000";
				break;

			case "jump":
				result[i] = "0011";
				result[i] += " " + getBinary(Integer.parseInt(currentString[1]),12);
				break;

			case "compare":
				result[i] = "0100";
				result[i] += " 0000";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				break;

			case "add":
				//decodes opCode and the three registers
				result[i] = "1110";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);

				break;
			case "subtract":
				result[i] = "1111";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			case "multiply":
				result[i] = "0111";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			case "and":
				result[i] = "1000";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			case "or":
				result[i] = "1001";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			case "xor":
				result[i] = "1010";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			case "not":
				result[i] = "1011";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			case "leftshift":
				result[i] = "1100";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			case "rightshift":
				result[i] = "1101";
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[1])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[2])),4);
				result[i] += " " + getBinary(Integer.parseInt(getRegNum(currentString[3])),4);
				break;
			}
		}
		return result;


	}
}