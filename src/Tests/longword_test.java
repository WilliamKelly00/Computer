package Tests;

import Computer.bit;
import Computer.longword;

public class longword_test{

	public static void runTests(){
		longword Testlw = new longword();
		longword Testlw2 = new longword();
		longword resultlw = new longword();
		bit testbit1 = new bit();

		Testlw.set(0);
		Testlw2.set(0);

		System.out.println("Testlw = " + Testlw.toString());
		System.out.println("Testlw = " + Testlw2.toString());

		//comparing a longword comprised of 0s and another of 0s
		resultlw = Testlw.and(Testlw2);
		System.out.println("resultlw of and = " + resultlw.toString());
		resultlw = Testlw.or(Testlw2);
		System.out.println("resultlw of or = " + resultlw.toString());
		Testlw.xor(Testlw2);
		resultlw = Testlw.xor(Testlw2);
		System.out.println("resultlw of xor = " + resultlw.toString());

		//comparing a longword comprised of 0s and another of 1s
		Testlw2.set(1);
		System.out.println("Testlw = " + Testlw.toString());
		System.out.println("Testlw = " + Testlw2.toString());

		resultlw = Testlw.and(Testlw2);
		System.out.println("resultlw of and = " + resultlw.toString());
		resultlw = Testlw.or(Testlw2);
		System.out.println("resultlw of or = " + resultlw.toString());
		Testlw.xor(Testlw2);
		resultlw = Testlw.xor(Testlw2);
		System.out.println("resultlw of xor = " + resultlw.toString());


		System.out.println("bit 3 of Testlw2 = " +Testlw2.getBit(3));

		//comparing a longword comprised of 1s and another of 1s
		Testlw.set(1);
		System.out.println("Testlw = " + Testlw.toString());
		System.out.println("Testlw = " + Testlw2.toString());

		resultlw = Testlw.and(Testlw2);
		System.out.println("resultlw of and = " + resultlw.toString());
		resultlw = Testlw.or(Testlw2);
		System.out.println("resultlw of or = " + resultlw.toString());
		Testlw.xor(Testlw2);
		resultlw = Testlw.xor(Testlw2);
		System.out.println("resultlw of xor = " + resultlw.toString());

		//Tests left and right shift by 5
		resultlw = Testlw.leftShift(5);
		System.out.println("resultlw of Testlw left shifted by 5 = " + resultlw.toString());
		resultlw = Testlw.rightShift(5);
		System.out.println("resultlw of Testlw right shifted by 5 = " + resultlw.toString());

		//tests the get signed and unsigned functions
		System.out.println("unsigned Testlw = " + Testlw.getUnsigned());
		System.out.println("signed Testlw = " + Testlw.getSigned());
		Testlw.setBit(30,testbit1);
		System.out.println("Testlw = " + Testlw.toString());
		System.out.println("signed Testlw = " + Testlw.getSigned());
		Testlw.setBit(31,testbit1);
		System.out.println("Testlw = " + Testlw.toString());
		System.out.println("signed Testlw = " + Testlw.getSigned());

		//test of the not function
		resultlw = Testlw.not();
		System.out.println("resultlw of not Testlw = " + resultlw.toString());

		//test of copy
		resultlw.copy(Testlw);
		System.out.println("copy of  Testlw = " + resultlw.toString());
	}
}