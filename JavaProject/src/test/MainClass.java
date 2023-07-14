package test;

public class MainClass {
	public static void main(String[] args) {
		
		printNames();
		printNames("aaa");
		printNames("aaa", "bbb");
		printNames("aaa", "bbb", "ccc");
		
		
	}
	
	//가변 인자 String type을 전달 받는 메소드
	public static void printNames(String... names) {
		// ... 는 갯수를 동적으로 전달 할 수 있다.
		// String...names는 String이 들어있는 배열이다! -> 확장 for문 돌 수 있음
		
		for(String tmp: names) {
			System.out.println(tmp);
		}
		System.out.println(names.length+" 개를 출력했습니다");
	}
}
