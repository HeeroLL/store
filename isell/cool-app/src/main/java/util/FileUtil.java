package util;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUtil {
	public static void createDirectory(File file)
	  {
	    if (!file.exists())
	      file.mkdir();
	  }
	
	public static ServletFileUpload initFileUpload(File file)
	  {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setSizeThreshold(4096);
	    factory.setRepository(file);
	    return new ServletFileUpload(factory);
	  }
	
	public static String getImageSuffix(String name)
	  {
	    if (name.endsWith(".jpg"))
	      return ".jpg";
	    if (name.endsWith(".bmp"))
	      return ".bmp";
	    if (name.endsWith(".gif"))
	      return ".gif";
	    if (name.endsWith(".svg"))
	      return ".svg";
	    if (name.endsWith(".mp3")) {
	      return ".mp3";
	    }
	    return ".png";
	  }

}
