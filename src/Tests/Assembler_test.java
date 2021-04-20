package Tests;

import Computer.Assembler;

public class Assembler_test {

	public static void runTests(){

		//checks instructions and registers
		//String[] instructions = {"Move R1 123","Halt", "Add R2 R3 R4", "interrupt 1", "xor r5 r6 r7", "interrupt 0", "not r8 r9 r10", "multiply r11 r12 r13", "leftshift r1 r14 r15", "Move R1 5"};

		//String[] instructions = {"BranchIfEqual 13", "branchifGreater -12", "branchifGreaterEqual 123", "Jump 10"};

		String[] instructions = {"pop R15","pop R1", "call 52", "pop R2", "push R3", "push R15","return" };

		//checks the move instruction 
		//String [] instructions2 = {"Move R1 123", "Move R2 63", "Move R1 31", "Move R1 15", "Move R1 2", "Move R1 -2", "Move R1 -123"};

		String[] result = Assembler.assemble(instructions);
		//String[] result2 = Assembler.assemble(instructions2);

		for(int i = 0; i< instructions.length; i++) {
			System.out.println(instructions[i]);
		}

		for(int j = 0; j < result.length; j++) {
			System.out.println(result[j]);
		}

		/*
		 for(int i = 0; i< instructions2.length; i++) {
			 System.out.println(instructions2[i]);
		 }

		 for(int j = 0; j < result2.length; j++) {
			 System.out.println(result2[j]);
		 }

		 */

	}
}
