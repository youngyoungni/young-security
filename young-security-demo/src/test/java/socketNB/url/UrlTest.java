package socketNB.url;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlTest {

	public static void main(String[] args) throws Exception {
		
		URL url = new URL("https://www.imooc.com");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setConnectTimeout(5000);
		if( connection.getResponseCode() == 200) {
			InputStream inputStream = url.openStream();
			
			byte[] b = new byte[1024];
			int len ;
			StringBuilder sb = new StringBuilder();
			while( ( len = inputStream.read(b)) != -1) {
				sb.append(new String(b,0,len,"UTF-8"));
			}
			System.out.println(sb);
			inputStream.close();
		}else {
			System.out.println("获取信息失败");
		}
		
	}
	
	public static void URLTest() throws Exception {
		URL imooc = new URL("https://www.imooc.com");
		URL url = new URL(imooc,"/index.html?username=tom#test");	//?参数和#瞄点
		System.out.println("应用层协议："+url.getProtocol());
		System.out.println("主机："+url.getHost());
		
		//如果未指定端口号，则使用默认的端口号（协议的端口号）
		System.out.println("端口："+url.getPort());
		System.out.println("文件路径："+url.getPath());
		System.out.println("文件名称："+url.getFile());
		System.out.println("相对路径："+url.getRef());
		System.out.println("查询字符串："+url.getQuery());
	}
}
