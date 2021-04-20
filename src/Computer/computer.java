package Computer;

/**
 * Class used to represent a computer
 * @author Billy
 *
 */
public class computer{

	/**
	 * contains a bit to determine if it's running, the memory of the computer, a longword array for  registers, and boolean flags
	 */
	private bit running = new bit();
	private memory memory = new memory();
	private longword PC = new longword();
	private longword currentInstruction = new longword();

	private longword[] registers = new longword[16];
	private longword op1 = new longword();
	private longword op2 = new longword();

	private longword SP = new longword();

	//copies currentInsturction so it is not changed
	private longword CICopy = new longword();

	private longword result = new longword();

	//flags used transitioning from execute into store
	boolean jumpFlag = true;
	boolean branchFlag = true;
	boolean ALUFlag;

	//bits to hold the condition codes for branch, as well as the sign of the branch value
	private bit conditionCode0 = new bit();
	private bit conditionCode1 = new bit();
	private bit branchSign = new bit();

	//longword to hold the address from the branch instruction
	private longword branchAddress = new longword();
	//a bit array to pass into the ALU
	private bit[] opCode = new bit[4];


	/**
	 * Initializes variables, and runs the computer
	 */
	public void run(){
		//initialization of variables
		for(int i = 0; i< 16; i++){
			registers[i] = new longword();
		}
		bit oneBit = new bit();
		oneBit.set(1);

		//initializes the Stack pointer to 1020
		for(int i = 2; i<10; i++) {
			SP.setBit(i,oneBit);
		}

		running.set(1);
		while(running.getValue() != 0){
			fetch();
			decode();
			execute();
			store();
		}
	}

	/**
	 * fetches the next instruction
	 */
	public void fetch(){
		bit oneBit = new bit();
		oneBit.set(1);

		longword size2 = new longword();
		size2.setBit(1,oneBit);

		currentInstruction.copy(memory.read(PC));
		PC.copy(rippleAdder.add(PC,size2));
	}

	/**
	 * Decodes the instruction longword
	 */
	public void decode(){
		bit oneBit = new bit();
		oneBit.set(1);

		//implementation of decode using shifting
		longword mask = new longword();
		for(int i = 0; i<4; i++){
			mask.setBit(i,oneBit);
		}
		CICopy.copy(currentInstruction);

		//the register determined by the value of mask AND CICopy is copied into op1
		op1.copy(registers[(int)(mask.and(CICopy.rightShift(8))).getUnsigned()]);

		//to get second register shift 4 right then and
		CICopy.copy(currentInstruction);
		//the 2nd register determined by the value of mask AND CICopy is copied into op2
		op2.copy(registers[(int)(mask.and(CICopy.rightShift(4))).getUnsigned()]);
	}


	/**
	 * Executes the instruction stored in the longword
	 */
	public void execute(){
		//move code = 0001
		bit[] moveCode = new bit[4];
		//interrupt code = 0010
		bit[] interruptCode = new bit[4];
		//halt code = 0000
		bit[] haltCode = new bit[4];
		//jump code = 0011
		bit[] jumpCode = new bit[4];
		//cmp code  = 0100
		bit[] CMPCode = new bit[4];
		//branch code = 0101
		bit[] branchCode = new bit[4];
		//Stack code  = 0110
		bit[] stackCode = new bit[4];

		for(int i = 0; i<4; i++){
			moveCode[i] = new bit();
			interruptCode[i] = new bit();
			haltCode[i] = new bit();
			jumpCode[i] = new bit();
			CMPCode[i] = new bit();
			branchCode[i] = new bit();
			opCode[i] = new bit();
			stackCode[i] = new bit();
		}

		moveCode[0].set(1);
		interruptCode[1].set(1);
		jumpCode[0].set(1);
		jumpCode[1].set(1);
		CMPCode[2].set(1);
		branchCode[0].set(1);
		branchCode[2].set(1);
		stackCode[1].set(1);
		stackCode[2].set(1);

		for(int i = 12; i < 16; i++){
			//copies the last byte of the instruction
			opCode[i-12].set(currentInstruction.getBit(i).getValue());
		}

		//flag is used to determine if any of the bits are not the same
		boolean moveFlag = true;
		boolean intFlag = true;
		boolean haltFlag = true;
		boolean CMPFlag = true;
		boolean stackFlag = true;
		jumpFlag = true;
		branchFlag = true;

		for(int i = 0; i<4; i++){
			if(!(opCode[i].getValue() == moveCode[i].getValue())){
				moveFlag = false;
			}
			if(!(opCode[i].getValue() == interruptCode[i].getValue())){
				intFlag = false;
			}
			if(!(opCode[i].getValue() == haltCode[i].getValue())){
				haltFlag = false;
			}
			if(!(opCode[i].getValue() == jumpCode[i].getValue())){
				jumpFlag = false;
			}
			if(!(opCode[i].getValue() == CMPCode[i].getValue())){
				CMPFlag = false;
			}
			if(!(opCode[i].getValue() == branchCode[i].getValue())){
				branchFlag = false;
			}
			if(!(opCode[i].getValue() == stackCode[i].getValue())){
				stackFlag = false;
			}

		}   
		//if the sum of the bits is 0, then the opCode must be 0000
		if(haltFlag == true)
			halt();

		//if the opCode matches the opcode for move, then move is called
		if(moveFlag == true)
			move();

		if(intFlag == true)
			interrupt(currentInstruction.getBit(0).getValue());

		if(CMPFlag == true) {
			longword cmpResult = new longword();
			longword mask = new longword();
			bit one = new bit();
			one.set(1);
			for(int i = 0; i < 4; i++) {
				mask.setBit(i,one);
			}

			//if the values in the registers the bit signifying equals will be set to 1
			if(((registers[(int)((currentInstruction.rightShift(4)).and(mask)).getUnsigned()].getUnsigned() == registers[(int)(currentInstruction.and(mask)).getUnsigned()].getUnsigned()))){
				conditionCode1.set(1);
			}
			else {
				conditionCode1.set(0);
			}
			//subtracts the values in the two registers and copies the result into cmpResult
			cmpResult.copy(rippleAdder.subtract(registers[(int)((currentInstruction.rightShift(4)).and(mask)).getUnsigned()], registers[(int)(currentInstruction.and(mask)).getUnsigned()]));

			//if the results value is greater than 0 the bit designating greater than is set to 1
			if(cmpResult.getSigned() > 0) {
				conditionCode0.set(1);
			}	
		}

		if(branchFlag == true) {
			longword signMask = new longword();
			bit one = new bit();
			one.set(1);
			//signMask is used to AND with the current instruction to retrieve the sign
			signMask.setBit(9,one);
			//AddressMask is used to AND with the current instruction to return the address from branch
			longword AddressMask = new longword();
			for(int i = 0; i < 9; i++) {
				AddressMask.setBit(i, one);
			}

			if((signMask.and(currentInstruction).getBit(9)).getValue() == 1) {
				branchSign.set(1);
			}

			//the address is then copied into the global longword branchAddress
			branchAddress.copy(AddressMask.and(currentInstruction));					
		}

		if(stackFlag) {
			//if the stack flag is true, then the instruction is decoded to determine which stack operation is being called
			longword stackMask = new longword();
			bit oneBit = new bit();
			oneBit.set(1);

			longword maskResult = new longword();

			stackMask.setBit(0,oneBit);
			stackMask.setBit(1,oneBit);

			maskResult.copy(stackMask.and(currentInstruction.rightShift(10)));
			//The last two bits of the third byte are used to determine the type of stack operation, and are stored in the String stackInstruction
			String stackInstruction = Integer.toString(maskResult.getBit(1).getValue()) + Integer.toString(maskResult.getBit(0).getValue());

			//Declaration for a longword of size 4, representing 4 bytes
			longword size4 = new longword();
			size4.setBit(2,oneBit);

			switch(stackInstruction) {
			case "00":
				//push
				longword pushMask = new longword();
				for(int i = 0; i< 4; i++) {
					pushMask.setBit(i, oneBit);
				}
				//The value of the register determined by the instruction is pushed onto the stack, writing it into memory at the stack pointer
				memory.write(SP, registers[(int) (pushMask.and(currentInstruction)).getUnsigned()]);

				//the stack pointer is then decreased by 4 bytes, increasing the stack's size
				SP.copy(rippleAdder.subtract(SP, size4));

				break;
			case "01":
				//pop
				longword popMask = new longword();
				for(int i = 0; i< 4; i++) {
					popMask.setBit(i, oneBit);
				}
				//The last value in the stack is copied into the register determined by the instruction
				// 4 bytes are added to the Stack Pointer to retrieve the correct value
				registers[(int)(popMask.and(currentInstruction)).getUnsigned()].copy(memory.read(rippleAdder.add(SP, size4)));

				//the stack pointer is then incremented by 4, decreasing the stack by 4 bytes
				SP.copy(rippleAdder.add(SP, size4));
				break;

			case "10":
				//call
				longword callMask = new longword();
				for(int i = 0; i< 10; i++) {
					callMask.setBit(i, oneBit);
				}
				//The current PC is added to the stack as it is incremented in fetch, and currently holds the next instruction
				memory.write(SP, PC);

				//The current instruction is AND'd with callMask to return the 10 bit address
				PC.copy(currentInstruction.and(callMask));
				//The stack is then incremented by 4 bytes				
				SP.copy(rippleAdder.subtract(SP, size4));		
				break;

			case "11":
				//return

				//reads from the 4 bytes before the current Stack Pointer so that a longword of 0 is not returned
				PC.copy(memory.read(rippleAdder.add(SP, size4)));
				//decrements the stack by 4 bytes
				SP.copy(rippleAdder.add(SP, size4));
				break;	
			}


		}


		//if any of the flags were true, the ALU flag will be set to false
		if(moveFlag == true || intFlag == true || haltFlag == true ||
				CMPFlag == true || jumpFlag == true || branchFlag == true || stackFlag == true) {
			ALUFlag = false;	 
		}
		else {
			//if there were no flags that were true, an ALU operation with the opCode will be done
			result.copy(ALU.doOp(opCode,op1,op2));
			ALUFlag = true;
		}
	}

	/**
	 * 
	 */
	public void store(){
		bit oneBit = new bit();
		oneBit.set(1);   

		if(ALUFlag) {	
			longword mask = new longword();
			for(int i = 0; i<4; i++){
				mask.setBit(i,oneBit);
			}
			//stores the result of the operation in the register determined by the last byte of the instruction
			registers[(int)(mask.and(CICopy)).getUnsigned()].copy(result);
			ALUFlag = false;
		}

		if(jumpFlag) {
			CICopy.copy(currentInstruction);
			longword jumpMask = new longword();
			for(int i = 0; i<12; i++)
				jumpMask.setBit(i,oneBit);
			//if the jumpFlag is true  the address in the instruction will be copied into PC
			PC.copy(CICopy.and(jumpMask));
		}

		if(branchFlag) {
			if(branchSign.getValue() == 1)
				branchAddress.setBit(31, oneBit);

			//if the condition codes in the instruction are equal to the codes that resulted from compare then the address from the branch instruction will be added to PC
			if(conditionCode0.getValue() == currentInstruction.getBit(11).getValue() && conditionCode1.getValue() == currentInstruction.getBit(10).getValue())
				PC.copy(rippleAdder.add(PC, branchAddress));
		}


	}



	/**
	 * Stops the computer from running
	 */
	public void halt(){
		//stops the loop running the computer
		running.set(0);
	}
	
	/**
	 * Moves a longword into a register
	 */
	public void move(){	  
		bit zeroBit = new bit();
		bit oneBit = new bit();
		oneBit.set(1);
		//moves a value into a register
		CICopy.copy(currentInstruction);
		longword literalValue = new longword();
		longword registerValue = new longword();

		longword mask = new longword();
		for(int i = 0; i<8; i++)
			mask.setBit(i,oneBit);

		longword registerMask = new longword();
		for(int i = 0; i<4; i++)
			registerMask.setBit(i,oneBit);

		literalValue.copy(mask.and(CICopy));



		//shifting CICopy by 8 and ANDing with the registerMask results in the isolated register value
		registerValue.copy((CICopy.rightShift(8)).and(registerMask));

		//if the sign bit for the literal value is negative, then the number is preserved, and the sign value is placed into the 31st bit, allows for values 127  to -127
		if(literalValue.getBit(7).getValue()==1){
			literalValue.setBit(7,zeroBit);
			literalValue.setBit(31,oneBit);
		}
		//the value is then placed in the register determined 
		registers[(int)registerValue.getUnsigned()].copy(literalValue);   
	}
	
	/**
	 * Preloads an instruction into memory, allowing for it to be run
	 * @param strarray Array containing instructions to be loaded into memory
	 */
	public void preload(String[] strarray){
		longword resultLW = new longword();
		longword resultCopy = new longword();

		bit oneBit = new bit();
		oneBit.set(1);

		longword counter = new longword();
		longword lwOf4 = new longword();
		lwOf4.setBit(2,oneBit);

		String resultStr = "";
		String oddEnding = "0000000000000000";

		for(int i = 0; i<strarray.length; i++){
			strarray[i] = strarray[i].replaceAll("\\s", ""); 
		}

		for(int i = 0; i<strarray.length; i++){

			resultStr = strarray[i] + resultStr;

			if(i % 2 == 1){

				//setting the result longword equal to the string
				for(int j = 0; j< 32; j++){
					if(resultStr.charAt(j) == '1')
						resultLW.setBit(j,oneBit);
				}
				//reversing the order to get the proper instruction
				resultCopy.copy(resultLW);
				for(int k = 0; k < 32; k++){
					resultLW.setBit(31-k, resultCopy.getBit(k));
				}

				//writing result to memory        
				memory.write(counter,resultLW);

				//increments the address
				counter.copy(rippleAdder.add(counter,lwOf4));

				//resets the resultString and Longword;
				resultStr = "";
				resultLW.set(0);

			} 


			if(i == strarray.length-1 && i%2 == 0){
				resultStr = oddEnding + resultStr;
				//setting the result longword equal to the string
				for(int j = 0; j< 32; j++){
					if(resultStr.charAt(j) == '1')
						resultLW.setBit(j,oneBit);
				}
				//reversing the order to get the proper instruction
				resultCopy.copy(resultLW);
				for(int k = 0; k < 32; k++){
					resultLW.setBit(31-k, resultCopy.getBit(k));
				}
				memory.write(counter,resultLW);


			}

		}

	}


	/**
	 * Sends an interrupt signal to the computer
	 * @param interrupt	type of interrupt to be handled by the computer
	 */
	public void interrupt(int interrupt){
		bit oneBit = new bit();
		oneBit.set(1);

		//if the interrupt is 0 it will print the registers to the console
		if(interrupt == 0){
			for(int i = 0; i<16; i++) {
				System.out.println(registers[i].toString());
			}
		}
		//if the interrupt is 1, it will print all of the memory to the console
		if(interrupt == 1){
			longword counter = new longword();
			longword lwOf4 = new longword();
			lwOf4.setBit(2,oneBit);
			for(int i = 0; i<256; i++){
				System.out.println((memory.read(counter)).toString());
				counter.copy(rippleAdder.add(counter, lwOf4));
			}
		}   
	}
}