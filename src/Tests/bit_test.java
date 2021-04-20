package Tests;
import Computer.bit;

public class bit_test{

	public static void runTests(){
		bit TestBit1 = new bit();
		bit TestBit2 = new bit();
		bit andResult;
		bit orResult;
		bit xorResult;
		bit notResult;

		System.out.println("Test Bit 1 value is " + TestBit1.toString());
		System.out.println("Test Bit 2 value is " + TestBit2.toString());

		//tests on 0 and 0
		andResult = TestBit1.and(TestBit2);
		System.out.println("and result of 0 and 0 is " + andResult.toString());

		orResult = TestBit1.or(TestBit2);
		System.out.println("or result of 0 and 0 is " + orResult.toString());

		xorResult = TestBit1.xor(TestBit2);
		System.out.println("xor result of 0 and 0 is " + xorResult.toString());

		notResult = TestBit1.not();
		System.out.println("not result of 0 is " + notResult.toString());

		// tests on 1 and 0
		TestBit1.set(1);
		System.out.println("Test Bit 1 value after set is " + TestBit1.toString());

		andResult = TestBit1.and(TestBit2);
		System.out.println("and result of 1 and 0 is " + andResult.toString());

		orResult = TestBit1.or(TestBit2);
		System.out.println("or result of 1 and 0 is " + orResult.toString());

		xorResult = TestBit1.xor(TestBit2);
		System.out.println("xor result of 1 and 0 is " + xorResult.toString());


		//tests on 1 and 1
		TestBit2.toggle();

		System.out.println("Test Bit 2 value after toggle is " + TestBit2.toString());

		andResult = TestBit1.and(TestBit2);
		System.out.println("and result of 1 and 1 is " + andResult.toString());

		orResult = TestBit1.or(TestBit2);
		System.out.println("or result of 1 and 1 is " + orResult.toString());

		xorResult = TestBit1.xor(TestBit2);
		System.out.println("xor result of 1 and 1 is " + xorResult.toString());

		notResult = TestBit1.not();
		System.out.println("not result of 1 is " + notResult.toString());

		TestBit2.clear();
		System.out.println("Test Bit 2 value after clear is " + TestBit2.toString());
	}
}