package com.example.demo;

import com.example.demo.pojo.Person;
import com.sun.webkit.BackForwardList;
import net.bytebuddy.asm.Advice;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.lang.model.element.VariableElement;
import java.util.*;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamApplicationTests {


	/**
	 * 使用stream流的方式,遍历结合,对集合中的数据进行过滤
	 * stram是jdk1.8之后出现的
	 * 关注的是做什么,而不是怎么做
	 */
	@Test
	public void testStream01() {
		List<String> list = new ArrayList<>();
		list.add("张无忌");
		list.add("周芷若");
		list.add("赵敏");
		list.add("张强");
		list.add("张三丰");
		//对list集合中的元素进行过滤,只要以张开头的元素,放入到一个新的集合中
		//对list集合中进行过滤,只要姓名长度为3的人,存储到一个新集合中
		//遍历list集合
		list.stream()
				.filter(name -> name.startsWith("张"))
				.filter(name -> name.length() == 3)
				.forEach(name -> System.out.println(name));

	}

	/**
	 * 获取一个流的方式
	 * 1.所有的collection 集合都可以通过stream默认方法获取流
	 * 2.stream接口的静态方法of可以获取数组对应的流
	 */
	@Test
	public void testGetStream() {
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();
		Set<String> set = new HashSet<>();
		Stream<String> stream1 = set.stream();

		//根据map获取流
		Map<String, String> map = new HashMap<>();
		//map的key
		Stream<String> keyStream = map.keySet().stream();
		//map的value
		Stream<String> valueStream = map.values().stream();
		//entryset 键值对的映射关系
		Stream<Map.Entry<String, String>> entryStream = map.entrySet().stream();

		//把数组转为stream流
		Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
		//可变参数可以传递数组
		String[] arr = {"a", "b", "c"};
		Stream<String> stream3 = Stream.of(arr);

	}

	/**
	 * steam流中的常用方法
	 * 1.forEach 该方法接收一个 Consumer 接口函数，会将每一个流元素交给该函数进行处理
	 * Consumer 是一个消费型的函数式接口.可以传递lambda表达式,消费数据
	 * foreach是一个终结方法,用来遍历流中的数据,遍历之后就不能再调用stream中的其他方法
	 */
	@Test
	public void steamForEach() {
		Stream<String> stringStream = Stream.of("张无忌", "张三丰", "周芷若");
		stringStream.forEach(name -> System.out.println(name)
		);
	}

	/**
	 * stream流中的常用方法
	 * 2.filter 用于对stream流中的数据进行过滤
	 * filter方法的参数Predicate是一个函数式接口,所以可以传递lambda表达式,对数据进行过滤
	 * Predicate中的抽象方法: boolean test(T t)
	 */
	@Test
	public void filterStream() {
		//创建一个stream流
		Stream<String> stream = Stream.of("张三丰", "张无忌", "张天师");
		//对Stream流中的元素进行过滤,只要姓张的人
		Stream<String> stream12 = stream.filter((String name) -> {
			return name.startsWith("张");
		});
		//遍历stream2流
		stream12.forEach(name -> System.out.println(name));

	}

	/**
	 * Stream流属于管道流,只能被消费(使用)一次,第一个stream流调用完毕方法,数据就会流转到下一个stream流
	 * 而这时第一个stream流已经使用完毕,就会关闭了
	 * 所以第一个stream流就不能再调用方法
	 *
	 */

	/**
	 * 常用stream方法
	 * map接口
	 */
	@Test
	public void testmap() {
		//获取一个string类型的stream流
		Stream<String> stream = Stream.of("1", "2", "3");
		//使用map方法,把字符串类型的整数,转换为Integer类型的整数
		Stream<Integer> stream1 = stream.map((String s) -> {
			return Integer.parseInt(s);
		});
		//遍历stream
		stream1.forEach(s -> System.out.println(s));

	}


	/**
	 * 常用stream方法
	 * count:用于统计stream流中元素的个数
	 * Long count
	 * count 方法是一个终结方法,返回值是一个long类型的整数
	 * 所以不能再继续调用stream流中的其他方法了
	 */
	@Test
	public void countStream() {
		//获取stream流
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		Stream<Integer> stream = list.stream();
		long count = stream.count();
		System.out.println(count);

	}

	/**
	 * 常用stream方法
	 * limit:用于截取流中的元素
	 * limit可以对流进行截取,只取前n个
	 * 方法签名:Stream<T>limit(long maxsize)
	 * 参数是一个long类型,如果集合当前长度大于参数则进行截取,否则不进行操作
	 * limit是一个延时方法,只是对流中的元素进行截取,返回的是一个新的流,可以调用流中的其他方法
	 */

	@Test
	public void  testLimit(){
		//获取一个Stream流
		String arr[] ={"美羊羊","懒洋洋","灰太狼"};
		Stream<String> stream = Stream.of(arr);
		//使用limit对steam流中的元素进行截取,只要前三个
		Stream<String> limit = stream.limit(2);
		//对截取后的流进行遍历
		limit.forEach(s->System.out.println(s));

	}
	/**
	 * * 常用stream方法
	 *skip 跳过前几个元素
	 * 如果流的当前长度大于你,则跳过前n个,否则将会得到一个长度为0的空流
	 * Stream<t> skip (long n)
	 */
	@Test
	public void testSkip(){
		//获取一个stream流
		String arr[] ={"美羊羊","懒洋洋","灰太狼"};
		Stream<String> stream1 = Stream.of(arr);
		//使用skip方法跳过前3个元素
		Stream<String> stream2 = stream1.skip(2);
		//遍历新跳过的流结果
		stream2.forEach(name->{System.out.println(name);}
		);

	}

	/**
	 * 常用stream方法
	 *concat 把流组合到一起
	 * static<t> stream<t> concat(Stream<? extends T> a ,Stream<? extend T>b)
	 * 如果有两个流,希望合并成为一个流,name可以使用stream接口的静态方法concat
	 */
	@Test
	public  void testConcat(){
		//获取一个stream流
		String arr[] ={"美羊羊","懒洋洋","灰太狼"};
		Stream<String> stream1 = Stream.of(arr);
		//另外一个流
		Stream<String> stream2 = Stream.of("1", "2", "3");
		//把两个流组合为一个新流
		Stream<String> concat = Stream.concat(stream1, stream2);
		//遍历组合的流
		concat.forEach(name->System.out.println(name));

	}

	/**
	 * 常用stream方法
	 * reduce()、count()，sum()等方法。
	 reduce()方法有三种形式：
	 Optionalreduce(BinaryOperator accumulator)
	 T reduce(T identity, BinaryOperator accumulator)
	 U reduce(U identity, BiFunction accumulator, BinaryOperator combiner)
	 eg：
	 一个参数的reduce方法：
	 */
	@Test
	public void testReduce(){
		//有两个参数,第一个参数是上次函数执行的返回值(中间结果),第二个参数是stream中的元素,这个函数把这两个值相加,得到的和会被赋值给下次执行这个函数的第一个参数
		//第一次执行的时候第一个参数的值是stream中的第一个元素,第二个参数是stream中的第二个元素
		//这个方法的返回值是Optional,这是java防止npe的一种可行方法
		List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9);
		Integer total = ints.stream().reduce((sum, item)->sum+item).get();
		System.out.println("ins sum is"+ total);

		//两个参数 它允许用户提供一个循环计算的初始值,如果stream为空,,就直接返回该值. 这个方法不会返回optional 因为其不会出现null值.
		List<Integer> ints2 = Lists.newArrayList(1,2,3,4,5);
		System.out.println("sum is"+ints2.stream().reduce(10,(sum,item)->sum+item) );


		//对比count方法 返回元素数量
		List<Integer> ints3 = Lists.newArrayList(1,2,3,4,5);
		System.out.println("sum is"+ints2.stream().count());





	}


	/**
	 * 练习题:
	 * 集合元素处理:
	 * 1. 第一个队伍只要名字为3个字的成员姓名；存储到一个新集合中。
	 2. 第一个队伍筛选之后只要前3个人；存储到一个新集合中。
	 3. 第二个队伍只要姓张的成员姓名；存储到一个新集合中。
	 4. 第二个队伍筛选之后不要前2个人；存储到一个新集合中。
	 5. 将两个队伍合并为一个队伍；存储到一个新集合中。
	 6. 根据姓名创建 Person 对象；存储到一个新集合中。
	 7. 打印整个队伍的Person对象信息。
	 两个队伍（集合）的代码如下：
	 */


	@Test
	public void  demoTest(){
		//第一只队伍
		ArrayList<String> one =new ArrayList<>();
		one.add("狄丽丽");
		one.add("宋远桥");
		one.add("苏星河");
		one.add("石破天");
		one.add("市院");
		one.add("宋慧乔");

		//1.第一个队伍只要名字为3个字的成员姓名,存储到一个新集合中
		ArrayList<String> one1 =new ArrayList<>();
		for (String s : one) {
			if(s.length()==3){
				one1.add(s);
			}
		}

		//2第一个队伍筛选之后,只要前三个人,存储到一个新的集合中
		ArrayList<String> one2 =new ArrayList<>();
		for (int i = 0; i <3 ; i++) {
			one2.add(one1.get(i)); //i=0,1,2
		}

		//第二支队伍
		ArrayList<String> two =new ArrayList<>();
		two.add("古力娜扎");
		two.add("张无忌");
		two.add("赵丽颖");
		two.add("张三丰");
		two.add("张天爱");
		two.add("张高华");

		//3.第二个队伍只要姓张的成员姓名,存到到一个集合中
		ArrayList<String> two1 =new ArrayList<>();
		for (String name : two) {
			if(name.startsWith("张")){
				two1.add(name);
			}
		}

		//4.第二个队伍筛选之后不要前2个人,存到一新集合中
		ArrayList<String> two2 =new ArrayList<>();
		for (int i = 2; i <two1.size() ; i++) {
			two2.add(two1.get(i)); //i不包含0,1
		}

		//5.将两个队伍合并为一个队伍,存到一个新的集合中
		ArrayList<String> all =new ArrayList<>();
		all.addAll(one2);
		all.addAll(two2);

		//6/根据姓名创建Person对象,存到到一个新集合中
		ArrayList<Person> list =new ArrayList<>();
		for(String name :all){
			list.add(new Person(name));
		}

		//7.打印整个队伍的Person对象信息
		for(Person person : list){
			System.out.println(person);
		}

		/*
			使用stream流完成上述流程
		 */
		//第一个队伍只要名字为3个字的成员,且只要前三个
		Stream<String> stream1 = one.stream().filter(name -> name.length() == 3).limit(3);
		//第二个队伍只要姓张的成员,不要前两个人
		Stream<String> twostream = two.stream().filter(name -> name.startsWith("张")).skip(2);
		//把两个队伍结果合并到一个队伍中,存储到一个新集合中,根据姓名创建person对象,打印整个队伍的person信息
		Stream.concat(stream1,twostream).map(name->new Person(name)).forEach(s->System.out.println(s));




	}









}

