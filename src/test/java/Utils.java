package ElectoralSystem;

public class Utils {
  static String formatMultilineStr(String s) {
    return s.replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", "").replaceAll(" ", "");
  }
}
