package Tests;
import Computer.bit;
import Computer.longword;
import Computer.multiplier;

public class multiplier_test{


	public static void runTests(){
		longword Testlw = new longword();
		longword Testlw2 = new longword();
		longword Testlw3 = new longword();
		longword Testlw4 = new longword();
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

		Resultlw = multiplier.multiply(Testlw,Testlw2);
		System.out.println("The result of : \n" + Testlw.toString() + "\n and \n" + Testlw2.toString() + "\n is \n" + Resultlw.toString());
		System.out.println("The result of : \n" + Testlw.getSigned() + "\n and \n" + Testlw2.getSigned() + "\n is \n" + Resultlw.getSigned());

		//sets Testlw to 13 and Testlw4 to -19

		Testlw3.setBit(0, onebit);
		Testlw4.setBit(0, onebit);
		Testlw4.setBit(1, onebit);
		Testlw3.setBit(2,onebit);
		Testlw3.setBit(3,onebit);
		Testlw4.setBit(4, onebit);
		Testlw4.setBit(31,onebit);

		System.out.println("The result of : \n" + Testlw.toString() + "\n and \n" + Testlw2.toString() + "\n is ");
		Resultlw = multiplier.multiply(Testlw3,Testlw4);
		System.out.println(Resultlw.toString());               
		System.out.println("The result of 13 x -19 is :" + Resultlw.getSigned());

		//sets Testlw to -13 and Testlw4 to -19

		Testlw3.setBit(0, onebit);
		Testlw4.setBit(0, onebit);
		Testlw4.setBit(1, onebit);
		Testlw3.setBit(2,onebit);
		Testlw3.setBit(3,onebit);
		Testlw4.setBit(4, onebit);
		Testlw3.setBit(31,onebit);
		Testlw4.setBit(31,onebit);

		System.out.println("The result of : \n" + Testlw.toString() + "\n and \n" + Testlw2.toString() + "\n is ");
		Resultlw = multiplier.multiply(Testlw3,Testlw4);
		System.out.println(Resultlw.toString());               
		System.out.println("The result of -13 x -19 is :" + Resultlw.getSigned());

	}
}