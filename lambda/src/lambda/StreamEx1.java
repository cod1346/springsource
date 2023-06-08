package lambda;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/* 스트림 연산
 * - 다양한 연산을 이용해 복잡한 작업들을 간단히 처리함
 * - 연산종류
 *  1) 중간 연산 : 연산 결과가 스트림 
 *  			 map(), skip(), distinct()...
 *  2) 최종 연산 : 연산 결과가 스트림이 아닌 상태
 *  			 forEach(연산결과가 출력문), collect(), reduce(), count()..
 */
public class StreamEx1 {

	public static void main(String[] args) {
		// 파일 객체에서 이름을 출력
		
		List<File> list = new ArrayList<File>();
		
		list.add(new File("c:\\file1.txt"));
		list.add(new File("c:\\file2.txt"));
		list.add(new File("c:\\file3.txt"));
		list.add(new File("c:\\file4.txt"));
		list.add(new File("c:\\file5.txt"));
		
		
		List<String> fileNames = new ArrayList<String>();
		for (File file : list) {
			fileNames.add(file.getName());
		}
		for (String string : fileNames) {
			System.out.println(string);
		}
		
		// stream 으로 처리?
		// stream 변환 (list.stream()) -> 연산(map()) -> 결과 출력
		// map() : 스트림의 요소에 저장된 값 중에서 원하는 필드만 추출하거나 특정
		// 		   형태로 변환시 사용
		
		System.out.println("-------stream-------------");
//		Stream<String> names = list.stream().map(File::getName);
//		names.forEach(f -> System.out.println(f));
		
		list.stream().map(File::getName).forEach(f->System.out.println(f));
		
		List<String> fruits = Arrays.asList("melon","apple","banana","grape");
		
		List<String> toUpperCase = new ArrayList<String>();
		
		for (String string : fruits) {
			toUpperCase.add(string.toUpperCase());
		}
		for (String string : toUpperCase) {
			System.out.println(string);
		}
		fruits.stream().map(String::toUpperCase).forEach(System.out::println);
		
	}

}
