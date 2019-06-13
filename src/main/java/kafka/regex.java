package kafka;


import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.io.FileDescriptor.in;


public class regex {
    static Map<String, String> headers = new HashMap<>();
    public static final String TAG_REGEX = "%(\\w|%)|%\\{([\\w\\.-]+)\\}|%\\[(\\w+)\\]";
    public static final Pattern tagPattern = Pattern.compile(TAG_REGEX);
    public static void main(String[] args) {
        Matcher matcher = tagPattern.matcher( "/flume/events/%y-%m-%d/%H%M/%S");
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String replacement = "";
            // Group 2 is the %{...} pattern
                if (matcher.group(2) != null) {
                    replacement = headers.get(matcher.group(2));
                    if (replacement == null) {
                        replacement = "";
//          LOG.warn("Tag " + matcher.group(2) + " not found");
                    }
                } else {
                    // The %x pattern.
                    // Since we know the match is a single character, we can
                    // switch on that rather than the string.
                    Preconditions.checkState(matcher.group(1) != null
                                    && matcher.group(1).length() == 1,
                            "Expected to match single character tag in string " + in);
                    System.out.println(matcher.group(1));
                    char c = matcher.group(1).charAt(0);
//                    replacement = replaceShorthand(c, headers, timeZone,
//                            needRounding, unit, roundDown, useLocalTimeStamp, ts);
                }

                // The replacement string must have '$' and '\' chars escaped. This
                // replacement string is pretty arcane.
                //
                // replacee : '$' -> for java '\$' -> for regex "\\$"
                // replacement: '\$' -> for regex '\\\$' -> for java "\\\\\\$"
                //
                // replacee : '\' -> for java "\\" -> for regex "\\\\"
                // replacement: '\\' -> for regex "\\\\" -> for java "\\\\\\\\"

                // note: order matters
                replacement = replacement.replaceAll("\\\\", "\\\\\\\\");
                replacement = replacement.replaceAll("\\$", "\\\\\\$");

                matcher.appendReplacement(sb, replacement);
            }



//        Pattern p = Pattern.compile("(\\d+)(\\d+)");
//        Matcher m = p.matcher("22bb23");
        while(matcher.find()) {// 返回true
            System.out.println(matcher.group(1));

//            System.out.println(matcher.group(1).length());
        }


    }
}
