package guoshe.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Constants {

    public static String getCurrenPath(){
        File directory = new File("");
        String filePath ="D:\\images\\";
        return filePath;
    }

    public static Boolean responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
            return true;
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return false;
    }
}
