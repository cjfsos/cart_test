package sp_basket;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		test1();
		test2();
		System.out.println(test1().get(0)+"/"+test2().get(0));
	}

	private static ArrayList<String> test2() {
		ArrayList<String> test22 = new ArrayList<>();
		test22.add("1");
		test22.add("2");
		test22.add("3");
		return test22;
	}

	private static ArrayList<String> test1() {
		ArrayList<String> test12 = new ArrayList<>();
		test12.add("a");
		test12.add("b");
		return test12;
	}
}
