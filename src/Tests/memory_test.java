package Tests;
import Computer.bit;
import Computer.longword;
import Computer.memory;

public class memory_test{

	public static void runTests(){

		longword Testlw = new longword();
		longword Testlw2 = new longword();
		longword Resultlw = new longword();
		memory mem = new memory();

		bit onebit = new bit();
		onebit.set(1);

		Testlw.set(0);
		Testlw2.set(0);

		for(int i = 0; i < 10; i++){
			Testlw2.setBit(i,onebit);
		}

		//addition test
		System.out.println("longword 1: " + Testlw.toString());
		System.out.println("longword 2: " + Testlw2.toString());

		//writes Testlw2 to 0-31
		mem.write(Testlw, Testlw2);
		Resultlw = mem.read(Testlw);
		System.out.println("Writing longword2 into the 0th spot of memory:");
		System.out.println("result of reading from the 0th spot: " + Resultlw.toString());

		//tests if granularity works
		Testlw.setBit(7,onebit);
		Testlw.setBit(5,onebit);
		Testlw.setBit(0,onebit);
		System.out.println("longword 1 is chaged to : " + Testlw.toString());
		System.out.println("longword 1 = " + Testlw.getSigned());
		mem.write(Testlw, Testlw2);
		Resultlw = mem.read(Testlw);
		System.out.println("longword 2 is written to the 168th spot, rather than the 161st ");
		System.out.println("reading from the 168th spot " + Resultlw.toString());

	}
}