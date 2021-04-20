package Drivers;
import Tests.*;

public class Main_tests{

	public static void runTests() {
		bit_test.runTests();
		longword_test.runTests();
		rippleAdder_test.runTests();
		multiplier_test.runTests();
		ALU_test.runTests();
		memory_test.runTests();
		cpu_test1.runTests();
		Assembler_test.runTests();
		cpu_test2.runTests();
		cpu_test3.runTests();
	}

	public static void main(String[] args){
		runTests();
	}
}