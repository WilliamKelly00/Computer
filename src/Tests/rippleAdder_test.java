package Tests;
import Computer.bit;
import Computer.longword;
import Computer.rippleAdder;

public class rippleAdder_test{

	public static void runTests(){

		longword Testlw = new longword();
		longword Testlw2 = new longword();
		longword Resultlw = new longword();

		bit onebit = new bit();
		onebit.set(1);

		Testlw.set(0);
		Testlw2.set(0);

		//sets Testlw to 13 and Testlw2 to 23

		Testlw.setBit(0, onebit);
		Testlw2.setBit(0, onebit);
		Testlw2.setBit(1, onebit);
		Testlw.setBit(2,onebit);
		Testlw2.setBit(2,onebit);
		Testlw.setBit(3,onebit);
		Testlw2.setBit(4, onebit);

		//addition test
		System.out.println("longword 1: " + Testlw.toString());
		System.out.println("longword 2: " + Testlw2.toString());
		System.out.println("longword 1 signed is : " + Testlw.getSigned());
		System.out.println("longword 2 signed is : " + Testlw2.getSigned());
		Resultlw = rippleAdder.add(Testlw, Testlw2);
		System.out.println("result of longword 1 + longword 2 : \n " + Resultlw.toString());
		System.out.println("result of longword 1 + longword 2 signed : \n " + Resultlw.getSigned());

		//Tests subtracting 23 from 13 result is a negative number
		Resultlw = rippleAdder.subtract(Testlw, Testlw2);
		System.out.println("result of longword 1 - longword 2 : \n " + Resultlw.toString());
		System.out.println("result of longword 1 - longword 2 signed : \n " + Resultlw.getSigned());


		//Tests subtracting 13 from 23 result is a positive number
		Resultlw = rippleAdder.subtract(Testlw2, Testlw);
		System.out.println("result of longword 2 - longword 1 : \n " + Resultlw.toString());
		System.out.println("result of longword 2 - longword 1 signed : \n " + Resultlw.getSigned());


	}
}