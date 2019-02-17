package pengrui.javagl.abstraction.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class LogUtil {
	
	static class LogAdapter{
		PrintWriter out;
		PrintWriter err;
		public LogAdapter() {
			init();
		}
		private void init(){
			if(null == out)
				out = new PrintWriter(System.out);
			
			if(null ==err)
				err = new PrintWriter(System.err);
		}
		public LogAdapter(PrintWriter o,PrintWriter e) {
			if(null == o||null ==e){
				init();
			}else{
				out = o;
				err = e;
			}
			init();
		}
		public void reline(){
			out.println();
			out.flush();
		}
		
		static final int STACK_DEEP = 3;
		public void info(Object... obj){
			if(null == obj || obj.length<1)
				return;
			out.print(Thread.currentThread().getStackTrace()[STACK_DEEP]+"--info:");
			for (Object o : obj) {
				out.print(o);
			}
			out.println();
			out.flush();
		}
		public void error(Object... obj){
			if(null == obj || obj.length<1)
				return;
			err.print(Thread.currentThread().getStackTrace()[STACK_DEEP]+"--error:");
			for (Object o : obj) {
				err.print(o);
			}
			err.println();
			err.flush();
		}
		public void debug(Object... obj){
			if(null == obj || obj.length<1)
				return;
			out.print(Thread.currentThread().getStackTrace()[STACK_DEEP]+"--debug:");
			if(GlobalConfig.GLOBAL_DEBUG_ENABLE){
				for (Object o : obj) 
					out.print(o);
				
				out.println();
				out.flush();
			}
		}
	}

	public static void error(Object... obj){
		log.error(obj);
	}
	public static void debug(Object... obj){
		if(GlobalConfig.GLOBAL_DEBUG_ENABLE)
			log.debug(obj);
	}
	public static void info(Object... obj){
		if(GlobalConfig.GLOBAL_INFO_ENABLE)
		log.info(obj);
	}
	public static void reline(){
		log.reline();
	}
	public static final LogAdapter log;
	
	static{
		log = new LogAdapter();
				//staticInit();
	}
	
	static LogAdapter staticInit(){
		 final String defaultConfigName = "pengrui.properties";
		 final String defaultKey = "path";
		 final String defaultLogName = "pengrui.log";
		 final String separator = (String) System.getProperties().get("path.separator");
		 final String classpath = (String) System.getProperties().get("java.class.path");
//		System.getProperties().get("line.separator");
//		System.getProperties().get("file.encoding");
		if(null==separator || null == classpath)
			return new LogAdapter();
		
		Properties p = new Properties();
		String []strs = classpath.split(separator);
		for(String s:strs){
			if(!Files.isDirectory(Paths.get(s)))
				continue;
			
			//TODO  开发环境和打包后可能不一样需要兼容处理
			Path path = Paths.get(Paths.get(s).getParent().toString(),defaultConfigName);
			
			if(!Files.exists(path))
				continue;
			try {
				p.load(Files.newInputStream(path));
				break;
			} catch (IOException e) {
				e.printStackTrace();
				return new LogAdapter();
			}
		}
		
		final String targetLogPath = (String) p.get(defaultKey);
		if(null == targetLogPath || "".equals(targetLogPath))
			return new LogAdapter();
		
		Path logFilePath = Paths.get(new File(targetLogPath).getAbsolutePath(),defaultLogName);
		if(!Files.exists(logFilePath)){
			try {
				logFilePath = Files.createFile(logFilePath);
			} catch (IOException e) {
				e.printStackTrace();
				return new LogAdapter();
			}
		}
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(Files.newOutputStream(logFilePath,StandardOpenOption.APPEND));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(null == pw)
			return new LogAdapter();
		else
			return new LogAdapter(pw,pw);
	}
}
