package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.tree.VoidDescriptor;

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
	 public void testStream01(){
		List<String> list =new ArrayList<>();
		list.add("张无忌");
		list.add("周芷若");
		list.add("赵敏");
		list.add("张强");
		list.add("张三丰");
		//对list集合中的元素进行过滤,只要以张开头的元素,放入到一个新的集合中
		//对list集合中进行过滤,只要姓名长度为3的人,存储到一个新集合中
		//遍历list集合
		list.stream()
				.filter(name->name.startsWith("张"))
				.filter(name->name.length()==3)
				.forEach(name->System.out.println(name));

	}

	/**
	 * 获取一个流的方式
	 * 1.所有的collection 集合都可以通过stream默认方法获取流
	 * 2.stream接口的静态方法of可以获取数组对应的流
	 */
	@Test
	public void testGetStream(){
		List<String> list =new ArrayList<>();
		Stream<String> stream = list.stream();
		Set<String> set =new HashSet<>();
		Stream<String> stream1 = set.stream();

		//根据map获取流
		Map<String,String> map =new HashMap<>();
		//map的key
		Stream<String> keyStream = map.keySet().stream();
		//map的value
		Stream<String> valueStream = map.values().stream();
		//entryset 键值对的映射关系
		Stream<Map.Entry<String, String>> entryStream = map.entrySet().stream();

		//把数组转为stream流
		Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
		//可变参数可以传递数组
		String [] arr ={"a","b","c"};
		Stream<String> stream3 = Stream.of(arr);

	}

	/**
	 * steam流中的常用方法
	 * 1.forEach 该方法接收一个 Consumer 接口函数，会将每一个流元素交给该函数进行处理
	 * Consumer 是一个消费型的函数式接口.可以传递lambda表达式,消费数据
	 * foreach是一个终结方法,用来遍历流中的数据,遍历之后就不能再调用stream中的其他方法
	 */
	@Test
	public void steamForEach(){
		Stream<String> stringStream = Stream.of("张无忌", "张三丰", "周芷若");
		stringStream.forEach(name->System.out.println(name)
		);
	}

	/**
	 * stream流中的常用方法
	 * 2.filter 用于对stream流中的数据进行过滤
	 * filter方法的参数Predicate是一个函数式接口,所以可以传递lambda表达式,对数据进行过滤
	 * Predicate中的抽象方法: boolean test(T t)
	 */



















}