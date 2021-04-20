package Tests;

import Computer.Assembler;
import Computer.computer;

public class cpu_test2 {

	public static void runTests(){

		String[] test1 = new String[] {"move r2 20", "move r5 10", "compare r2 r5", "branchifgreater 16", "move r10 127", "interrupt 0", "halt"};
		String[] test2 = new String[] {"jump 32", "move R1 5" ,"interrupt 0", "halt"};
		String[] test3 = new String[] {"move r2 10", "move r5 10", "compare r2 r5", "branchifEqual 16", "move r10 127", "interrupt 0", "halt"};

		String[] assembledCode = Assembler.assemble(test1);
		String[] assembledCode2 = Assembler.assemble(test2);
		String[] assembledCode3 = Assembler.assemble(test3);

		System.out.println("Test 1 ");
		computer cpuTest1 = new computer();
		cpuTest1.preload(assembledCode);
		cpuTest1.run();


		System.out.println("Test 2 ");
		computer cpuTest2 = new computer();
		cpuTest2.preload(assembledCode2);
		cpuTest2.run();

		System.out.println("Test 3 ");
		computer cpuTest3 = new computer();
		cpuTest3.preload(assembledCode3);
		cpuTest3.run();



	}

}

