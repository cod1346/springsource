package lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx6 {

	public static void main(String[] args) {
		// 문자열을 리턴하는 기능 구현
		
		// 문자열을 입력받아서 문자열의 길이가 0 인지 판단하는 기능 구현
		
		// 문자열을 입력받아서 출력하는 기능 구현
		
		// 문자열을 입력받아서 정수로 리턴 기능 구현
		
		Supplier<String> s = () -> "안녕하세요"; 
		System.out.println(s.get());
		
		Predicate<String > t = i -> i.length()==10; 
		System.out.println(t.test("abcdefg"));
		
		Consumer<String> a = (i) -> System.out.println(i);
		a.accept("테스트");
		
		Function<String, Integer> c = (i) -> Integer.parseInt(i)+1;
		System.out.println(c.apply("12321"));
		

	}

}
