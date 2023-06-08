package lambda;

/*  람다식
 *  - 함수형 언어
 *  - 메소드를 하나의 식으로 표현
 */

public class Lambda1 {
	int max(int a,int b) {
		return a>b?a:b;
	}
	
//	(int a,int b)->{return a>b?a:b;}
//	(int a, int b) -> a > b ? a : b
//	(a, b) -> a > b ? a : b
	
	void printVar(String name, int i) {
		System.out.println(name + " = "+ i );
	}
	
//	(String name, int i) -> {System.out.println(name + " = "+ i );} 
//	(String name, int i) -> System.out.println(name + " = "+ i ); 
//	(name, i) -> System.out.println(name + " = "+ i ); 
	
	int square(int x) {
		return x*x;
	}
	
//	(int x)->{return x*x;}
//	(int x)->x*x
//	x -> x*x
	
	int roll() {
		return (int)(Math.random()*6);
	}
	
//	()-> {return (int)(Math.random()*6);}
//	()-> (int)(Math.random()*6)
}
