public class ParamTest {

	public static void main(String[] args) {
		String s = "123";
		String s1 = testStr(s);
		System.out.println("s:"+s);
		System.out.println("s1:"+s1);
	}
	
	public static String testStr(String str) {
		str = "123456";
		return "123456";
	}
}
