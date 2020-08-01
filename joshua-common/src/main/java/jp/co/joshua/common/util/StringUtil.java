package jp.co.joshua.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import jp.co.joshua.common.type.Charset;

/**
 * 文字列操作のUtilクラス
 *
 * @version 1.0.0
 */
public class StringUtil {

    /** カンマ */
    public static final String COMMA = ",";
    /** ハイフン */
    public static final String HYPHEN = "-";
    /** コロン */
    public static final String COLON = ":";
    /** ピリオド */
    public static final String PERIOD = ".";
    /** 空文字 */
    public static final String EMPTY = "";
    /** イコール */
    public static final String EQUAL = "=";
    /** 改行 */
    public static final String NEW_LINE = System.getProperty("line.separator");
    /** 半角スペース */
    public static final String SPACE = " ";
    /** 半角スラッシュ */
    public static final String THRASH = "/";
    /** アンダースコア */
    public static final String UNDER_SCORE = "_";
    /** タブ */
    public static final String TAB = "  ";

    /**
     * プライベートコンストラクタ
     */
    private StringUtil() {
    }

    /**
     * 対象文字列<code>target</code>を区切り文字<code>delim</code>で、区切ったリストを返す
     *
     * @param target
     *            対象文字列
     * @param delim
     *            区切り文字
     * @return List<String>
     */
    public static List<String> toStrList(String target, String delim) {
        return isEmpty(target) ? null : CollectionUtil.toList(target.split(delim));
    }

    /**
     * 指定した文字列<code>target</code>が空文字の場合true、それ以外の場合false<br>
     * トリム処理を行う
     *
     * @param target
     *            対象文字列
     * @return 判定結果
     */
    public static boolean isEmpty(String target) {
        return BeanUtil.isNull(target) || "".equals(target.trim());
    }

    /**
     * 指定した文字列<code>target</code>が空文字の場合true、それ以外の場合false<br>
     *
     * @param target
     *            対象文字列
     * @return 判定結果
     */
    public static boolean isBrank(String target) {
        return BeanUtil.isNull(target) || EMPTY.equals(target);
    }

    /**
     * 指定した文字列<code>target</code>に値が指定されていればtrue、それ以外の場合false
     *
     * @param target
     *            対象文字列
     * @return 判定結果
     */
    public static boolean hasValue(String target) {
        return !isEmpty(target);
    }

    /**
     * 半角スペースでPaddingする
     *
     * @see StringUtil#padding(String target, int length, String str,
     *      PaddingType paddingType)
     * @param target
     *            対象文字列
     * @param length
     *            padding後の文字長
     * @param paddingType
     *            Paddingタイプ(右詰/左詰)
     * @return Padding後の文字列
     */
    public static String paddingSpace(String target, int length,
            PaddingType paddingType) {
        return padding(target, length, SPACE, paddingType);
    }

    /**
     * 指定した文字列を<code>str</code>でpaddingする<br>
     * <code>target</code>の長さが<code>length</code>以上の文字列の場合、そのまま返す
     *
     * @param target
     *            対象文字列
     * @param length
     *            padding後の文字長
     * @param str
     *            paddingしたい文字列
     * @param paddingType
     *            Paddingタイプ(右詰/左詰)
     * @return Padding後の文字列
     */
    public static String padding(String target, int length, String str,
            PaddingType paddingType) {
        if (length <= target.length()) {
            // 指定した文字長がlength以上の場合
            return target;
        }
        String body = target;
        while (body.length() < length) {
            switch (paddingType) {
            case LEFT:
                // 左詰
                body = body + str;
                break;
            case RIGHT:
                // 左詰
                body = str + body;
                break;
            }
        }
        return body;
    }

    /**
     * 指定したbyte配列を16進文字列に変換する
     *
     * @param bArray
     *            byte配列
     * @return 16進文字列
     */
    public static String byteToHexString(byte[] bArray) {
        StringBuilder result = new StringBuilder();
        Stream.of(bArray).forEach(b -> result.append(String.format("%02x", b)));
        return result.toString();
    }

    /**
     * {@link org.springframework.util.StringUtils#capitalize(String)} のラッパーメソッド
     *
     * @param str
     *            対象文字列
     * @return capitalize後の文字列
     */
    public static String capitalize(String str) {
        return StringUtils.capitalize(str);
    }

    /**
     * 指定して桁数のランダム文字列を返す<br>
     * {@link org.apache.commons.lang3.RandomStringUtils#randomAlphabetic(int)}
     * のラッパーメソッド
     *
     * @param length
     *            文字列長
     * @return ランダム文字列
     */
    public static String getRandamStr(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * 指定した区切り文字で対象文字列を結合する
     *
     * @param delim
     *            区切り文字
     * @param ignoreRule
     *            結合しない条件
     * @param values
     *            対象文字列
     * @return 結合した文字列
     */
    public String join(String delim, Predicate<String> ignoreRule, String... values) {

        if (isEmpty(delim) || CollectionUtil.isEmpty(Arrays.asList(values))) {
            return null;
        }
        StringJoiner sj = new StringJoiner(delim);
        Arrays.asList(values).stream()
                .filter(e -> ignoreRule == null || !ignoreRule.test(e))
                .forEach(e -> sj.add(e));

        return sj.toString();
    }

    /**
     * 指定した文字列<code>str</code>を<code>length</code>で切り取る<br>
     *
     * @param str
     *            対象文字列
     * @param charset
     *            文字コード
     * @param length
     *            切りたい文字列の長さ
     * @return 切り取った後の文字列
     */
    public static String slice(String str, Charset charset, int length) {

        String result = null;
        if (isEmpty(str)) {
            return result;
        }

        try {
            byte[] b = str.getBytes(charset.getValue());
            if (b.length <= length) {
                result = str;
            } else {
                String s = new String(b, 0, length, charset.getValue());
                // 切り取った文字列の最後の1文字の位置
                int cutLastLen = s.length() - 1;
                // 切り取った文字列の最後の文字
                char c1 = s.charAt(cutLastLen);
                // 切り取る前の文字列の切り取り位置の文字
                char c2 = str.charAt(cutLastLen);
                if (c1 == c2) {
                    result = s;
                } else {
                    // 切り取った文字列の最後の1文字が半端なbyteの場合
                    result = slice(str, charset, length - 1);
                }
            }
        } catch (Exception e) {
            // 文字コードが不正な場合
            throw new RuntimeException(e);
        }
        return result;

    }

    /**
     * Padding指定の列挙
     */
    public static enum PaddingType {
        /** 右詰 */
        RIGHT,
        /** 左詰 */
        LEFT;
    }
}
