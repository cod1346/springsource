package lambda;

public class LambdaEx4 {

	public static void main(String[] args) {

		
		Lambda4 lambda = (x,y)->x>y?x:y;
		System.out.println(lambda.max(100,150));
		
		Lambda5 lambda2 = (x,y)->x<y?x:y;
		System.out.println(lambda2.min(100, 150));
		
//		lambda=(x,y)->{
//			int i = 10;
//			System.out.println(i*x);
//		};
	}
}
