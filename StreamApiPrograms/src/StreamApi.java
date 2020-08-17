import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {

	public static void main(String[] args) {

		//filter

		List<Integer> myList = new ArrayList<>();
		for(int i=0; i<100; i++) myList.add(i);
		Stream<Integer> sequentialStream = myList.stream();

		Stream<Integer> highNums = sequentialStream.filter(p -> p > 90); //filter numbers greater than 90
		System.out.print("High Nums greater than 90=");
		highNums.forEach(p -> System.out.print(p+" "));
		//prints "High Nums greater than 90=91 92 93 94 95 96 97 98 99 "

/*****************************************************************/	
		//sorted

		Stream<String> names2 = Stream.of("aBc", "d", "ef", "123456");
		List<String> reverseSorted = names2.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(reverseSorted); // [ef, d, aBc, 123456]

		Stream<String> names3 = Stream.of("aBc", "d", "ef", "123456");
		List<String> naturalSorted = names3.sorted().collect(Collectors.toList());
		System.out.println(naturalSorted); //[123456, aBc, d, ef]

/*****************************************************************/	
		
		//map

		Stream<String> names = Stream.of("aBc", "d", "ef");
		System.out.println(names.map(s -> {
				return s.toUpperCase();
			}).collect(Collectors.toList()));
		//prints [ABC, D, EF]


		//flatmap

		Stream<List<String>> namesOriginalList = Stream.of(
			Arrays.asList("Pankaj"), 
			Arrays.asList("David", "Lisa"),
			Arrays.asList("Amit"));
		//flat the stream from List<String> to String stream
		Stream<String> flatStream = namesOriginalList
			.flatMap(strList -> strList.stream());

		flatStream.forEach(System.out::println);

/*****************************************************************/		
		
		//java stream terminal operations example
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
				
		Optional<Integer> intOptional = numbers.reduce((i,j) -> {return i*j;});
		if(intOptional.isPresent()) System.out.println("Multiplication = "+intOptional.get()); //120


		Stream<Integer> numbers1 = Stream.of(1,2,3,4,5);
				
		System.out.println("Number of elements in stream="+numbers1.count()); //5


		Stream<Integer> numbers2 = Stream.of(1,2,3,4,5);
		numbers2.forEach(i -> System.out.print(i+",")); //1,2,3,4,5,

		Stream<Integer> numbers3 = Stream.of(1,2,3,4,5);
		System.out.println("Stream contains 4? "+numbers3.anyMatch(i -> i==4));
		//Stream contains 4? true

		Stream<Integer> numbers4 = Stream.of(1,2,3,4,5);
		System.out.println("Stream contains all elements less than 10? "+numbers4.allMatch(i -> i<10));
		//Stream contains all elements less than 10? true

		Stream<Integer> numbers5 = Stream.of(1,2,3,4,5);
		System.out.println("Stream doesn't contain 10? "+numbers5.noneMatch(i -> i==10));
		//Stream doesn't contain 10? true

		Stream<String> names4 = Stream.of("Pankaj","Amit","David", "Lisa");
		Optional<String> firstNameWithD = names4.filter(i -> i.startsWith("D")).findFirst();
		if(firstNameWithD.isPresent()){
			System.out.println("First Name starting with D="+firstNameWithD.get()); //David
		}



	}

}
