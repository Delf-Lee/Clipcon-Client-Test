package contents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DirectoryCompression {
	/**
	 * ���� �޼ҵ�
	 *
	 * @param path
	 *            ���
	 * @param outputFileName
	 *            ������ϸ�
	 */
	public static void compress(File file) throws Throwable {
		//File file = new File(path);
		String outputFileName = file.getName() + ".zip";
		int pos = outputFileName.lastIndexOf(".");
		if (!outputFileName.substring(pos).equalsIgnoreCase(".zip")) {
			outputFileName += ".zip";
		}
		// ���� ��� üũ
		if (!file.exists()) {
			throw new Exception("Not File!");
		}
		
		FileOutputStream fos = null; // ��� ��Ʈ��
		ZipOutputStream zos = null; // ���� ��Ʈ��
		
		try {
			fos = new FileOutputStream(new File(outputFileName));
			zos = new ZipOutputStream(fos);
			// ���丮 �˻�
			searchDirectory(file, file.getPath(), zos);
		} catch (Throwable e) {
			throw e;
		} finally {
			if (zos != null)
				zos.close();
			if (fos != null)
				fos.close();
		}
	}

	/**
	 * ���丮 Ž��
	 *
	 * @param file
	 *            ���� ����
	 * @param root
	 *            ��Ʈ ���
	 * @param zos
	 *            ���� ��Ʈ��
	 */
	private static void searchDirectory(File file, String root, ZipOutputStream zos) throws Exception {
		if (file.isDirectory()) { // ������ ������ ���丮���� �������� �˻�
			// ���丮�� ��� ��Ž��(���)
			File[] files = file.listFiles();
			for (File f : files) {
				searchDirectory(f, root, zos);
			}
		} else {
			// ������ ��� ������ �Ѵ�.
			compressZip(file, root, zos);
		}
	}

	/**
	 * ���� �޼ҵ�
	 *
	 * @param file
	 * @param root
	 * @param zos
	 * @throws Exception
	 */

	private static void compressZip(File file, String root, ZipOutputStream zos) throws Exception {
		FileInputStream fis = null;
		try {
			String zipName = file.getPath().replace(root + "\\", "");
			fis = new FileInputStream(file); // ������ �о�帲
			ZipEntry zipentry = new ZipEntry(zipName); // Zip��Ʈ�� ����(�ѱ� ���� ����)
			zos.putNextEntry(zipentry); // ��Ʈ���� �о�ֱ�(�ڵ� ����)
			
			int length = (int) file.length();
			byte[] buffer = new byte[length];
			
			fis.read(buffer, 0, length); // ��Ʈ�� �о�帮��
			zos.write(buffer, 0, length); // ��Ʈ�� �ۼ�
			zos.closeEntry(); // ��Ʈ�� �ݱ�

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
	}
}
