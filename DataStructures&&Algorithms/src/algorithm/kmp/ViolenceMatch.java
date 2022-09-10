package algorithm.kmp;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    /**
     * 暴力匹配字符串
     *
     * @param str1 原字符串
     * @param str2 匹配的字符串
     * @return 匹配成功的字符串的下标
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1_len = str1.length();
        int s2_len = str2.length();

        int i = 0;
        int j = 0;

        while (i < s1_len && j < s2_len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                //匹配失败
                i -= j - 1;
                j = 0;
            }
        }
        if(j == s2_len){
            return i - j;
        } else {
            return -1;
        }
    }
}
