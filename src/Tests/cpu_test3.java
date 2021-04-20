package Tests;

import Computer.Assembler;
import Computer.computer;

public class cpu_test3 {

	public static void runTests(){

		//Test for push and pop functions and assembly
		String[] pushPopTest = new String[] {"move R1 45", "move R2 12", "push R1","push R2", "pop R4", "pop R5", "interrupt 0", "halt"};
		String[] assembledCode = Assembler.assemble(pushPopTest);
		System.out.println("Push/Pop Test");
		computer cpuTest1 = new computer();
		cpuTest1.preload(assembledCode);
		cpuTest1.run();

		//Tests call and return
		String[] callTest = new String[] {"call 4", "halt", "interrupt 0", "return"};
		String[] assembledCode2 = Assembler.assemble(callTest);
		System.out.println("Call Test");
		computer cpuTest2 = new computer();
		cpuTest2.preload(assembledCode2);
		cpuTest2.run();

		//Simultaneously tests all stack functions
		String[] smallProgramTest = new String[] {"move R1 45", "move R2 12", "push R1","push R2", "pop R4", "pop R5", "call 18", "interrupt 0", "halt" , "add r2 r1 r0", "return"};
		String[] assembledCode3 = Assembler.assemble(smallProgramTest);
		System.out.println("Small Program Test");
		computer cpuTest3 = new computer();
		cpuTest3.preload(assembledCode3);
		cpuTest3.run();

		//Printing Assembled code for the small program to the screen
		System.out.println("Assembled code for smallProgramTest ");
		for(int i=0; i<assembledCode3.length;i++) {
			System.out.println(assembledCode3[i]);
		}





	}
}
