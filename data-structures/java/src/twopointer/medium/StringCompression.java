package twopointer.medium;

public class StringCompression {

  public int compress(char[] chars) {
    int write = 0;

    for (int read = 0; read < chars.length; read++) {
      char curChar = chars[read];
      int curCount = 0;

      int temp = read;
      while (temp < chars.length && chars[temp] == curChar) {
        temp++;
        curCount++;
      }

      chars[write++] = curChar;
      if (curCount > 1) {
        for (char digit : String.valueOf(curCount).toCharArray()) {
          chars[write++] = digit;
        }
      }

      read = temp-1;
    }

    return write;
  }

  public static void main(String[] args) {
    var solution = new StringCompression();

    char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};

    int result = solution.compress(chars);
    System.out.println(result);
  }
}
