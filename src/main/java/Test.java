public class Test {

	public static void main(String[] args) {
		
	/*	
		double expectedEnergy1 =  (180.0*100)/(1*365);
		System.out.println(expectedEnergy1);
		
		BigDecimal expectedEnergy2 = new BigDecimal(expectedEnergy1);
		expectedEnergy2 = expectedEnergy2.setScale(2,RoundingMode.HALF_UP);
		System.out.println(expectedEnergy2);*/
	
		String environment ="stg";
		
		 if(environment.startsWith("qas"))
		 {
		System.out.println(environment);
		 }
	
		 else {
			 System.out.println("false");
		 }
			 }
}
