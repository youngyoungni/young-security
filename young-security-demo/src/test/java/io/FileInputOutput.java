package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileInputOutput {
	
	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//		writeFile("D:\\b.txt", "\nINFO: 当前时间戳："+System.currentTimeMillis()+"，时间为："+simpleDateFormat.format(new Date()));
//		readFile("D:\\b.txt");
		copyFile("D:\\b.txt", "D:\\a.txt");
	}
	
	public static void readFile(String pathName) {
		try (
				FileInputStream input = new FileInputStream(new File(pathName));
				){
			byte[] b = new byte[1024];
			int len = 0 ;
			StringBuffer sb = new StringBuffer();
			while( (len=input.read(b)) != -1) {
				sb.append(new String(b,0,len));
			}
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writeFile(String pathName,String str) {
		try (
				FileOutputStream output = new FileOutputStream(new File(pathName),true);
				){
			output.write(str.getBytes());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void copyFile(String sourcePath,String targetPath) {
		long begin = System.currentTimeMillis();
		try (
				FileInputStream input = new FileInputStream(new File(sourcePath));
				FileOutputStream output = new FileOutputStream(new File(targetPath));
				){
				int len = 0 ;
				byte[] b = new byte[1024];
				while( (len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
		} catch (Exception e) {
		}
		System.out.println("文件复制耗时为："+(System.currentTimeMillis() - begin)+"毫秒");
	}
	
	public static void dateMutualString() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String date = "2018-04-25 10:05:21";
		long time = new Date().getTime();
		System.out.println(simpleDateFormat.format(time).getClass());
		System.out.println(simpleDateFormat.parse(date).getClass());
	}
}
