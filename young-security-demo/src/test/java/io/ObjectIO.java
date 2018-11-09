package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 	对象序列化
 * @author Youngni
 *
 */
public class ObjectIO {

	/**
	 * 	对象流是一组高级流,作用是方便读写java中任何对象
	 * 	对象输出流,可以写出java对象
	 *	工作原理:将给定java对象按照其结构转化为一组字节然后写出.
	 */
	public static void main(String[] args) {
		
		Person person = new Person();
		person.setName("Young");
		person.setAge(20);
		person.setGender('男');
		person.setSalary(4000);
		List<String> otherList = new ArrayList<String>();
		otherList.add("是一位诗人");
		otherList.add("也是一位伟人");
		person.setOtherInfo(otherList);
		System.out.println(person.toString());
		
		serializeObject("D:\\b.txt", person);
		deserializeObject("D:\\b.txt");
		
	}
	
	/**
	 * 	序列化对象
	 */
	public static void serializeObject(String pathName,Object object) {
		try (
			//初始化 对象输出字节流
			ObjectOutputStream output = 
				new ObjectOutputStream(new FileOutputStream(new File(pathName),false));
				){
			/*
			 * 	当调用output.writeObject时,实际上做了两件事:
			 * 		将 “object” 对象按照结果转换为了一组字节(对象序列化)
			 * 		然后再将这组字节通过FOS写入到文件中,将数据写入硬盘的过程成为：持久化
			 * 	可能会报错: java.io.NotSerializableException,
			 * 	这是因为对象所属的类没有继承 Serializable 接口,需要继承接口并生成serialVersionUID
			 */
			output.writeObject(object);
			System.out.println("对象序列化完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	反序列化对象
	 */
	public static void deserializeObject(String pathName) {
		try (
			ObjectInputStream input =
				new ObjectInputStream(new FileInputStream(new File(pathName)));
				){
			Person p = (Person) input.readObject();
			System.out.println(p.toString());
			System.out.println("从硬盘反序列化完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
