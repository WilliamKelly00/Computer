package Tests;
import Computer.ALU;
import Computer.bit;
import Computer.longword;

public class ALU_test{

	public static void runTests(){
		longword a = new longword();
		longword b = new longword();
		longword result  = new longword();

		bit onebit = new bit();
		onebit.set(1);

		a.setBit(0, onebit);
		b.setBit(0, onebit);
		b.setBit(1, onebit);
		a.setBit(2,onebit);
		b.setBit(2,onebit);
		a.setBit(3,onebit);
		b.setBit(4, onebit);

		bit[] bitArray = new bit[4];
		for(int i = 0; i < 4; i++){
			bitArray[i] = new bit();
		}
		System.out.println("a = :" + a.toString());
		System.out.println("b = :" + b.toString());

		//1000 And
		bitArray[3].set(1);  
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a and b = :" + result.toString());
		//1001 Or
		bitArray[0].set(1);  
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a or b = :" + result.toString());
		//1011 not
		bitArray[1].set(1);  
		result = ALU.doOp(bitArray,a,b);
		System.out.println("not a or b = :" + result.toString());

		//1111 Subtract
		bitArray[2].set(1);  
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a - b = :" + result.toString());

		//1010 xor
		bitArray[2].set(0);   
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a xor b = :" + result.toString());

		//1110 add
		bitArray[2].set(1);   
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a + b = :" + result.toString());

		//0111 multiply
		bitArray[3].set(0);  
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a * b = :" + result.toString());

		//1101 right Shift
		bitArray[3].set(1);  
		bitArray[1].set(0);  
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a >> b = :" + result.toString());

		//a is reset
		a.set(0);
		a.setBit(0, onebit);
		a.setBit(2,onebit);
		a.setBit(3,onebit);


		//1100 left Shift
		bitArray[0].set(0);  
		result = ALU.doOp(bitArray,a,b);
		System.out.println("a << b = :" + result.toString());

		//a is reset
		a.set(0);
		a.setBit(0, onebit);
		a.setBit(2,onebit);
		a.setBit(3,onebit);


	}
}