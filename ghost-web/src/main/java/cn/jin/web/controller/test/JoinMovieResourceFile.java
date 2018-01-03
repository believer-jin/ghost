package cn.jin.web.controller.test;

import cn.jin.utils.FileUtils;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * 将电源资源文件整合到一个文件中
 */
public class JoinMovieResourceFile {

    /**
     * 资源文件名前缀
     */
    private final static String FILE_NAME = "movieResource";

    /**
     * 资源路径
     */
    private final static String FILE_PATH = "E:\\workspce_jin\\ghost\\ghost-web\\src\\main\\resources\\";

    /**
     * 资源路径
     */
    private final static String FILE_SUFFIX = ".dpl";

    public static void main(String[] args) {
        File fileDir  = new File(FILE_PATH);
        if(fileDir.exists() && fileDir.isDirectory()){
            File[] files = fileDir.listFiles((File file) -> {
                if(file != null && !StringUtils.isEmpty(file.getName()) && file.getName().startsWith(FILE_NAME)){
                    return true;
                }
                return false;
            });
            if(files != null && files.length > 0){
                for (File file : files) {
                    FileUtils.writerToFile(FileUtils.readerToFile(file.getPath()),FILE_PATH+ FILE_NAME +FILE_SUFFIX);
                }
            }
        }
    }

}
