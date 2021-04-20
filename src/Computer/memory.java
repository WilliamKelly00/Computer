package Computer;

/**
 * This class acts as the memory of the computer
 * @author Billy
 *
 */
public class memory{

	/**
	 *	Bit array acting as the memory of the computer
	 *	Size of 1024 bytes
	 */
	private bit[] bitArray = new bit[8192];

	public memory(){
		//initializes the bits in memory
		for(int i = 0; i < 8192; i++){
			bitArray[i] = new bit();
		}
	}

	/**
	 * reads a longword from memory - the bit array
	 * @param address	address to start reading from
	 * @return	the longword read from memory
	 */
	public longword read(longword address){
		longword resultlongword = new longword();
		//gets the value of the address
		long uAddress = address.getUnsigned();
		uAddress *= 8;
		//checks if it will go out of bounds
		if(uAddress + 31 > bitArray.length)
			throw new IllegalArgumentException("invalid address, overflow " + address);
		//sets the granularity of the pointer to a byte
		if(uAddress % 8 != 0){
			uAddress = (uAddress - (uAddress % 8)) + 8;
		}
		for(int i = 0; i < 32; i++){
			//reads from the address into the result
			resultlongword.setBit(i,bitArray[(int)uAddress + i]);
		}
		return resultlongword;
	}

	/**
	 * writes a longword to memory
	 * @param address	address to write to
	 * @param value	longword to write to memory
	 */
	public void write(longword address, longword value){
		long uAddress = address.getUnsigned();
		uAddress *= 8;
		//checks the bounds
		if(uAddress + 31 > bitArray.length) {
			throw new IllegalArgumentException("invalid address, overflow " + uAddress);
		}
		//sets the granularity of the pointer to a byte
		if(uAddress % 8 != 0){
			uAddress = (uAddress - (uAddress % 8)) + 8;
		}
		//writes from the longword into the bit array
		for(int i = 0; i < 32; i++){
			bitArray[i + (int)uAddress].set(value.getBit(i).getValue());
		}
	} 


}