package cn.jin.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作辅助类
 * 
 * @author dingsj 2013-12-11 FileUtils
 */
public class FileUtils {

	/**
	 * 文件：要读取文件不存在
	 */
	private final static String FILE_READ_ERROR_NOTFOUND = "6";
	private final static String FILE_READ_ERROR_NOTFILE = "7";

	/**
	 * 文件拷贝正确
	 */
	private final static String FILE_READ_SUCCESS = "0";

	/**
	 * 文件拷贝错误
	 */
	private final static String FILE_READ_FAILURE = "1";

	/**
	 * 写入文件(如果文件存在，在尾部增加)
	 * 
	 * @param data
	 * @param filePath
	 * @return boolean
	 * @author dingsj
	 * @since 2013-12-11 下午03:44:20
	 */
	public static boolean writerToFile(String data, String filePath) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath,true);
			fileWriter.write(data);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 读取文件
	 * 
	 * @param filePath
	 * @param charset
	 * @return String
	 * @author dingsj
	 * @since 2013-12-11 下午03:55:42
	 */
	public static String readerToFile(String filePath, String charset) {
		String result = "";
		try {
			FileInputStream in = new FileInputStream(new File(filePath));
			int size = in.available();
			byte[] bytes = new byte[size];
			in.read(bytes);
			in.close();
			result = new String(bytes, charset);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取文件
	 * 
	 * @param filePath
	 * @return String
	 * @author dingsj
	 * @since 2013-12-11 下午03:55:42
	 */
	public static String readerToFile(String filePath) {
		String result = "";
		try {
			FileInputStream in = new FileInputStream(new File(filePath));
			int size = in.available();
			byte[] bytes = new byte[size];
			in.read(bytes);
			in.close();
			result = new String(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取文件内容到list集合中
	 * 
	 * @param filePath
	 * @return
	 * @author dingsj 2015年10月26日 下午7:31:52
	 */
	public static List<String> readerLineForFile(String filePath) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				list.add(tempString);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 拷贝文件（传统方式拷贝，使用BufferedInputStream）
	 * 
	 * @param fileFrom
	 *            待复制的文件
	 * @param fileTo
	 *            目标文件
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 * @author dingsj 2015年10月26日 下午7:34:42
	 * @deprecated
	 */
	public static String copyfileForBuffer(String fileFrom, String fileTo, boolean overlay) {
		InputStream fis = null;
		OutputStream fos = null;
		try {
			File source = new File(fileFrom);
			if (!source.exists()) {
				return FILE_READ_ERROR_NOTFOUND;
			} else if (!source.isFile()) {
				return FILE_READ_ERROR_NOTFILE;
			}
			File target = new File(fileTo);
			if (target.exists()) {
				if (overlay) {
					target.delete();
				}
			}
			
			fis = new BufferedInputStream(new FileInputStream(source));
			fos = new BufferedOutputStream(new FileOutputStream(fileTo));
			byte[] buf = new byte[4096];
			int i;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
				return FILE_READ_FAILURE;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return FILE_READ_SUCCESS;
	}

	/**
	 * 拷贝文件（传统方式拷贝，使用FileInputStream）
	 * 
	 * @param fileFrom
	 *            待复制的文件
	 * @param fileTo
	 *            目标文件
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 * @author dingsj 2015年10月26日 下午7:34:42
	 * @deprecated
	 */
	public static String copyfileForFile(String fileFrom, String fileTo, boolean overlay) {
		InputStream fis = null;
		OutputStream fos = null;
		try {
			File source = new File(fileFrom);
			if (!source.exists()) {
				return FILE_READ_ERROR_NOTFOUND;
			} else if (!source.isFile()) {
				return FILE_READ_ERROR_NOTFILE;
			}
			File target = new File(fileTo);
			if (target.exists()) {
				if (overlay) {
					target.delete();
				}
			}
			fis = new FileInputStream(source);
			fos = new FileOutputStream(target);
			byte[] buf = new byte[4096];
			int i;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
				return FILE_READ_FAILURE;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return FILE_READ_SUCCESS;
	}

	/**
	 * 拷贝文件(NIO)(推荐使用)
	 * 
	 * @param fileFrom
	 *            待复制的文件
	 * @param fileTo
	 *            目标文件
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 * @author dingsj 2015年10月26日 下午7:34:42
	 */
	public static String copyFile(String fileFrom, String fileTo, boolean overlay) {
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			File source = new File(fileFrom);
			if (!source.exists()) {
				return FILE_READ_ERROR_NOTFOUND;
			} else if (!source.isFile()) {
				return FILE_READ_ERROR_NOTFILE;
			}
			File target = new File(fileTo);
			if (target.exists()) {
				if (overlay) {
					target.delete();
				}
			}
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);
			in = inStream.getChannel();
			out = outStream.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				return disconnect(in, out, inStream, outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return FILE_READ_SUCCESS;
	}

	/**
	 * 拷贝文件(NIO:Buffer)
	 * 
	 * @param fileFrom
	 *            待复制的文件
	 * @param fileTo
	 *            目标文件
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 * @author dingsj 2015年10月26日 下午7:34:42
	 */
	public static String nioBufferCopy(String fileFrom, String fileTo, boolean overlay) {
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			File source = new File(fileFrom);
			if (!source.exists()) {
				return FILE_READ_ERROR_NOTFOUND;
			} else if (!source.isFile()) {
				return FILE_READ_ERROR_NOTFILE;
			}
			File target = new File(fileTo);
			if (target.exists()) {
				if (overlay) {
					target.delete();
				}
			}
			
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);
			in = inStream.getChannel();
			out = outStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(4096);
			while (in.read(buffer) != -1) {
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				return disconnect(in, out, inStream, outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return FILE_READ_SUCCESS;
	}

	/**
	 * 拷贝文件(NIO:MappedByteBuffer:内存映射)
	 * 
	 * @param fileFrom
	 *            待复制的文件
	 * @param fileTo
	 *            目标文件
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 * @author dingsj 2015年10月26日 下午7:34:42
	 */
	public static String nioMapperCopy(String fileFrom, String fileTo, boolean overlay) {
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			File source = new File(fileFrom);
			if (!source.exists()) {
				return FILE_READ_ERROR_NOTFOUND;
			} else if (!source.isFile()) {
				return FILE_READ_ERROR_NOTFILE;
			}
			File target = new File(fileTo);
			if (target.exists()) {
				if (overlay) {
					target.delete();
				}
			}
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);
			in = inStream.getChannel();
			out = outStream.getChannel();
			MappedByteBuffer mbb = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
			out.write(mbb);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				return disconnect(in, out, inStream, outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return FILE_READ_SUCCESS;
	}

	/**
	 * 关闭流
	 * @param in
	 * @param out
	 * @param inStream
	 * @param outStream
	 * @return
	 * @throws IOException
	 */
	private static String disconnect(FileChannel in, FileChannel out, FileInputStream inStream,
			FileOutputStream outStream) throws IOException {
		if(in != null){
			in.close();
		}
		if(out != null){
			out.close();
		}
		if(inStream != null){
			inStream.close();
		}
		if(outStream != null){
			outStream.close();
		}
		return FILE_READ_FAILURE;
	}
}
