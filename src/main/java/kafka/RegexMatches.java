package kafka;

import java.util.regex.Pattern;


public class RegexMatches
{
    private static String REGEX = "dog";
    private static String INPUT = "The dog says meow. " +
            "All dogs say meow.";
    private static String REPLACE = "cat";
    public static boolean startWithChar(String s) {

//        final String patt = "^\\d{2}\\s+\\w+\\s+\\d{4}$";
        final String patt = "^\\w{4}-\\w{2}-\\w{2}T\\w{2}:\\w{2}:\\w{2}.*$";

        if (s != null && s.length() > 0) {
            String start = s.split(":")[0].substring(0, 11);
            System.out.println(start);
            Pattern pattern = Pattern.compile(patt);
            return pattern.matcher(s).find();
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        Pattern p = Pattern.compile(REGEX);
//        // get a matcher object
//        Matcher m = p.matcher(INPUT);
//        INPUT = m.replaceAll(REPLACE);
//        System.out.println(INPUT);
        String str = " 2019-05-20T11:52:30+080030286authpriv30286info30286dtc-show0230286 pam_unix(sshd:session): session opened for user root by (uid=0)";
        boolean b = startWithChar(str.trim());
        System.out.println(b);
    }
}
