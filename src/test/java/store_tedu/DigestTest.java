package store_tedu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class DigestTest {

	// @Test
	public void testFileDidest() throws IOException {
		String file = "passwd";
		// 打开文件
		InputStream in = new FileInputStream(file);
		// 计算md5摘要
		String md5 = DigestUtils.md5Hex(in);
		in.close();
		System.out.println(md5);
	}

	@Test
	public void testStringDigest() {
		String pwd = "123";
		String md5 = DigestUtils.md5Hex(pwd);
	}
}
