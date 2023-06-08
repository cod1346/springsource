package lambda;

public class LambdaEx2 {

	public static void main(String[] args) {
		 
//		Lambda2 lambda = new Lambda2() {
//			@Override
//			public void method() {
//				System.out.println("익명 구현 클래스");
//			}
//		};
//		lambda.method();
		
		Lambda2 lambda = ()->System.out.println("익명 구현 클래스");;
		lambda.method();
		
		lambda=()->{
			int i = 10;
			System.out.println(i*i);
		};
		lambda.method();
	}
}
