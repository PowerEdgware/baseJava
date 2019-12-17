package mysql.fc;

/**
 * 分词
 * 
 * @author shangcj
 *
 */
public class WordSplit {

	public static void main(String[] args) {
		String word = "Google's  free service instantly translates words, phrases, and web pages between English and over 100 other languages. ";
		char[] words = word.toCharArray();

		String temp = "";
		for (int i = 0; i < words.length; i++) {
			//去除特殊字符
			if (words[i] != ' ' && words[i]!=',' && words[i]!='.') {
				temp += words[i];
				continue;
			}
			if (temp.length() >= 3) {
				System.out.println(temp + " pos=" + (i - temp.length()));
				temp = "";
			}
		}
	}
}
