package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BufferedIO {
	
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		writeBuffer("D:\\b.txt","\r\nINFO Time："+simpleDateFormat.format(new Date().getTime())+" hello world");
		readBuffer("D:\\b.txt");
		
	}
	
	
	public static void writeBuffer(String pathName , String str) {
		try (
			// true 表示 是否追加，true:追加 false:覆盖
			FileOutputStream output = new FileOutputStream(new File(pathName),true);
			// 所有的字节被存入缓冲区，等待一次性写出
			BufferedOutputStream bos = new BufferedOutputStream(output);
				){
			byte[] b = str.getBytes();
			bos.write(b);
			bos.flush();	 //强制将缓冲区已缓存数据一次性写出
			System.out.println("写出完毕");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readBuffer(String pathName) {
		 
	        try (
	        	BufferedInputStream bufferedInput  = 
	        		new BufferedInputStream(new FileInputStream(pathName));
	        		){
	            int len = 0;
	            byte[] buffer = new byte[1024];
	            //从文件中按字节读取内容，到文件尾部时read方法将返回-1
	            while ((len = bufferedInput.read(buffer)) != -1) {
	                //将读取的字节转为字符串对象
	            	String info = new String(buffer, 0, len);
	                System.out.print(info);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
