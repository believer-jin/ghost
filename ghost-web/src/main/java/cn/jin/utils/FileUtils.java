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
 * �ļ�����������
 * 
 * @author dingsj 2013-12-11 FileUtils
 */
public class FileUtils {

	/**
	 * �ļ���Ҫ��ȡ�ļ�������
	 */
	private final static String FILE_READ_ERROR_NOTFOUND = "6";
	private final static String FILE_READ_ERROR_NOTFILE = "7";

	/**
	 * �ļ�������ȷ
	 */
	private final static String FILE_READ_SUCCESS = "0";

	/**
	 * �ļ���������
	 */
	private final static String FILE_READ_FAILURE = "1";

	/**
	 * д���ļ�(����ļ����ڣ���β������)
	 * 
	 * @param data
	 * @param filePath
	 * @return boolean
	 * @author dingsj
	 * @since 2013-12-11 ����03:44:20
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
	 * ��ȡ�ļ�
	 * 
	 * @param filePath
	 * @param charset
	 * @return String
	 * @author dingsj
	 * @since 2013-12-11 ����03:55:42
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
	 * ��ȡ�ļ�
	 * 
	 * @param filePath
	 * @return String
	 * @author dingsj
	 * @since 2013-12-11 ����03:55:42
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
	 * ��ȡ�ļ����ݵ�list������
	 * 
	 * @param filePath
	 * @return
	 * @author dingsj 2015��10��26�� ����7:31:52
	 */
	public static List<String> readerLineForFile(String filePath) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
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
	 * �����ļ�����ͳ��ʽ������ʹ��BufferedInputStream��
	 * 
	 * @param fileFrom
	 *            �����Ƶ��ļ�
	 * @param fileTo
	 *            Ŀ���ļ�
	 * @param overlay
	 *            ���Ŀ���ļ����ڣ��Ƿ񸲸�
	 * @author dingsj 2015��10��26�� ����7:34:42
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
	 * �����ļ�����ͳ��ʽ������ʹ��FileInputStream��
	 * 
	 * @param fileFrom
	 *            �����Ƶ��ļ�
	 * @param fileTo
	 *            Ŀ���ļ�
	 * @param overlay
	 *            ���Ŀ���ļ����ڣ��Ƿ񸲸�
	 * @author dingsj 2015��10��26�� ����7:34:42
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
	 * �����ļ�(NIO)(�Ƽ�ʹ��)
	 * 
	 * @param fileFrom
	 *            �����Ƶ��ļ�
	 * @param fileTo
	 *            Ŀ���ļ�
	 * @param overlay
	 *            ���Ŀ���ļ����ڣ��Ƿ񸲸�
	 * @author dingsj 2015��10��26�� ����7:34:42
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
	 * �����ļ�(NIO:Buffer)
	 * 
	 * @param fileFrom
	 *            �����Ƶ��ļ�
	 * @param fileTo
	 *            Ŀ���ļ�
	 * @param overlay
	 *            ���Ŀ���ļ����ڣ��Ƿ񸲸�
	 * @author dingsj 2015��10��26�� ����7:34:42
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
	 * �����ļ�(NIO:MappedByteBuffer:�ڴ�ӳ��)
	 * 
	 * @param fileFrom
	 *            �����Ƶ��ļ�
	 * @param fileTo
	 *            Ŀ���ļ�
	 * @param overlay
	 *            ���Ŀ���ļ����ڣ��Ƿ񸲸�
	 * @author dingsj 2015��10��26�� ����7:34:42
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
	 * �ر���
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
