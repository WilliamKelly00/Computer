package Tests;

import Computer.computer;

public class cpu_test1{

	public static void runTests(){

		String moveTestStr = "0001 0010 0000 1001";
		String moveTestStr2 = "0001 0101 0001 1001";
		String interruptTest1Str = "0010 0000 0000 0000";
		String interruptTest2Str = "0010 0000 0000 0001";
		String ALUTestStr = "1000 0010 0101 0001";
		String haltTestStr = "0000 0000 0000 0000";

		//String[] Test1 = { moveTestStr, moveTestStr2, ALUTestStr, interruptTest1Str, interruptTest2Str , haltTestStr};  
		//String[] Test1 = { moveTestStr};
		String[] Test1 = {moveTestStr, moveTestStr2, ALUTestStr};
		computer cpuTest1 = new computer();
		cpuTest1.preload(Test1);
		cpuTest1.run();

		//cpuTest1.interrupt(1);
		cpuTest1.interrupt(0);



	}
}