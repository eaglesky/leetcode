public class  ReadNCharactersGivenRead4 {
	private String file;
	private int filePointer;

	ReadNCharactersGivenRead4() {
	}

	public void open(String file) {
		this.file = file;
		filePointer = 0;
	}

	public void close() {
		this.file = null;
		filePointer = 0;
	}

	public void restart() {
		filePointer = 0;
	}

	//Given API
	//Read 4 characters if possible and store them to buf, starting from beginning.
	//Return the number of characters actually read.
	public int read4(char[] buf) {
		if (file == null) {
			System.out.println("Error: no file is opened!");
			return 0;
		}
		int i = 0;
		int iMax = Math.min(buf.length, 4);
		for(; filePointer < file.length() && i < iMax; ++filePointer, ++i) {
			buf[i] = file.charAt(filePointer);
		}
		return i;
	}

	//Similar to https://discuss.leetcode.com/topic/18289/another-accepted-java-solution
	public int read(char[] buf, int n) {
		int numRead = Math.min(buf.length, n);
		char[] temp = new char[4];
		int bufId = 0;
		while (bufId < numRead) {
			int curCount = read4(temp);
			if (curCount == 0) {
				break;
			}
			int numCopied = Math.min(numRead - bufId, curCount);
			System.arraycopy(temp, 0, buf, bufId, numCopied);
			bufId += numCopied;
		}
		return bufId;
	}

	private void testRead(char[] buf, int n) {
		restart();
		read(buf, n);
		System.out.println("buf size = " + buf.length + ", n = " + n);
		System.out.println("Result = " + new String(buf) + "#");
	} 

	public static void main(String[] args) {
		String file1 = "Allen Chin is a great man! He likes computer science very much!\n"
			+ "He studied very hard\n"
			+ "			He comes from China";
		System.out.println("File1: ");
		System.out.println(file1);
		ReadNCharactersGivenRead4 reader = new ReadNCharactersGivenRead4();
		reader.open(file1);

		//Testing the given API read4
		int count = 0;
		char[] buf = new char[5];
		int curCount = 0;
		for (; (curCount = reader.read4(buf)) > 0; count += curCount) {
			//System.out.println(curCount);
		}
		System.out.println("Number of characters read = " + count);
		System.out.println("Expected = " + file1.length());//Should be 107

		//Testing the required function read
		reader.restart();
		char[] buf1 = new char[6];
		int n1 = 50;
		reader.testRead(buf1, n1);
		char[] buf2 = new char[56];
		reader.testRead(buf2, n1);

		reader.open("Haha, hahaha\nGreat");
		char[] buf3 = new char[40];
		reader.testRead(buf3, n1);
		buf2 = new char[56];
		reader.testRead(buf2, n1);
	}
}