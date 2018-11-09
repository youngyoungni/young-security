package io;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable{

	/**
	 * transient关键字
	 * 	对象在序列化后得到的字节序列往往比较大，有时我们在对一个对象进行序列化时可以忽略某些不必要的属性，从而对序列化后得到的字节序列“瘦身”。
	 * 	transient：被该关键字修饰的属性在序列化时其值将被忽略。
	 */
	
	private static final long serialVersionUID = 1L;

	private String name;
	private Integer age;
	private char gender;
	transient private double salary;
	transient private List<String> otherInfo;
	
	
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", salary=" + salary + ", otherInfo="
				+ otherInfo + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public List<String> getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(List<String> otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	
}
