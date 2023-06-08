package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ForEach1 {
/* 스트림
 * - 컬렉션(List, Set, Map), 배열에 데이터를 담고 원하는 결과를 얻어내기 위해 for문과 Iterator을 사용함
 * ==>데이터마다 다른 방식으로 다뤄야 함, 재사용성 떨어짐, 같은 기능의 메소드들이 중복으로 정의
 * ==>List 정렬 시 Collections.sort()사용, 배열 정렬 시 Arrays.sort()
 * 
 * ==>위의 문제점 해결하기 위해 스트림이 추가됨
 * ==>데이터가 서로 다른 데이터라도 동일한 방식으로 처리
 * 
 * ==>특징
 * 1) 데이터 소스(원본)를 변경하지 않음
 * 2) 스트림은 일회용
 * 3) 스트림은 작업을 내부 반복으로 처리 
 */
	public static void main(String[] args) {
		//문자열 배열
		String [] strArr = {"aaa","ddd","ccc"};
		//문자열 리스트
		List<String> strList = Arrays.asList(strArr);
		
		Arrays.sort(strArr);
		for (String string : strList) {
			System.out.println(string);
		}
		
		System.out.println("-------------------");
		
		Collections.sort(strList);
		for (String string : strList) {
			System.out.println(string);
		}
		
		System.out.println("-------------stream");

		//steram 방식
		String [] strArr1 = {"aaa","ddd","ccc"};
		List<String> strList1 = Arrays.asList(strArr1);
		
		// 1.스트림을 생성
		Stream<String> stream1 = Arrays.stream(strArr1);
		Stream<String> stream2 = strList1.stream();
		
		// 2.정렬 + 출력
		stream1.sorted().forEach(System.out::println);
		System.out.println("-------------------");
		stream2.sorted().forEach(System.out::println);
		
		System.out.println(Arrays.toString(strArr1));
		System.out.println(strList1);
		
	}

}
