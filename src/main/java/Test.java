import static org.junit.Assert.assertFalse;

import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		String str = " ''argument of type 'NoneType' is not iterable'' ";
		int month = Calendar.getInstance().get(Calendar.MONTH);
		System.out.println(month);
		if(str.startsWith(" ''argument"))
		{
			System.out.println("false block");
			assertFalse("argument of type 'NoneType' is not iterable", true);
		}
	}
}
