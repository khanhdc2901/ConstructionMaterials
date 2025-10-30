/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class CommonFunction {

    public static boolean isEmptyString(String str) {
        return str == null || str.equals("");
    }

    public static String getVNDString(String str) {

        if (str.indexOf("%") > 0) {
            return str;
        }

        String oldStr = str;
        String newStr = "";
        String tmpStr = "";

        while (oldStr.length() > 0) {

            if (oldStr.length() > 3) {
                tmpStr = oldStr.substring(oldStr.length() - 3, oldStr.length());
                oldStr = oldStr.substring(0, oldStr.length() - 3);
            } else {
                tmpStr = oldStr;
                oldStr = "";
            }

            newStr = tmpStr + "." + newStr;

        }

        newStr = newStr.substring(0, newStr.length() - 1);
        newStr = newStr + " VND";

        return newStr;
    }
    
    public static String stringConvertDateTime(String str) {
        String[] strs = str.split("T");

        if (strs.length >= 2) {
            return String.format("%s %s", strs[0], strs[1]).trim();
        } else {
            return str;
        }
    }
    
    public static int getTotalPages(int countItems) {
        return (int) Math.ceil((double) countItems/Constants.MAX_ELEMENTS_PER_PAGE);
    }
    
    
}
