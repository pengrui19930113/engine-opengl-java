package pengrui.javagl.abstraction.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class InputStreamUtil {

	
	public static String inputStreamToString(InputStream is) throws IOException{
		BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(is));
    	StringBuilder sb = new StringBuilder();
        int len = -1;
        byte [] buffer = new byte[1024];
        while((len =  bis.read(buffer))>0){
        	sb.append(new String(buffer,0,len));
        }
        bis.close();
		return sb.toString();
	}
	
	public static byte[] inputStreamToByteArray(InputStream is) throws IOException{
		BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(is));
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = -1;
        byte [] buffer = new byte[1024];
        while((len =  bis.read(buffer))>0){
        	baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
	}
}
