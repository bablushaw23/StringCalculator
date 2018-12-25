import java.text.DecimalFormat;

public class Additionals {
    public String replacePartOfString(int startingIndex, int endingIndex, String dataToReplace, String data) throws Exception {
        String newData = "";
        String rightPart = "";
        String leftPart = data.substring(0, startingIndex);
        if (data.substring(endingIndex + 1).length() > 0) {
            rightPart = data.substring(endingIndex + 1);
        }
        newData = String.valueOf(leftPart) + dataToReplace + rightPart;
        return newData;
    }

    public String calculatorFormat(Double data) throws Exception {
        DecimalFormat f = new DecimalFormat();
        if (data >= 1.0E7 || data <= -1.0E7) {
            f = new DecimalFormat("##.####E0");
            return data.toString();
        }
        f = new DecimalFormat("0.####");
        return data.toString();
    }

    public char[] invalidVarSet() {		//used to ensure that char used in variable might not get matched with any of char in function 
        int[] frequency = new int[26];
        frequency[15] = 1;
        frequency[2] = 1;
        Name name = new Name();
        String funcName = "";
        funcName = String.valueOf(funcName) + name.sin();
        funcName = String.valueOf(funcName) + name.cos();
        funcName = String.valueOf(funcName) + name.tan();
        funcName = String.valueOf(funcName) + name.cosec();
        funcName = String.valueOf(funcName) + name.sec();
        funcName = String.valueOf(funcName) + name.cot();
        funcName = String.valueOf(funcName) + name.ln();
        funcName = String.valueOf(funcName) + name.log();
        char[] arrc = funcName.toCharArray();
        int n = arrc.length;
        int n2 = 0;
        while (n2 < n) {
            char i = arrc[n2];
            int[] arrn = frequency;
            int n3 = i - 97;
            arrn[n3] = arrn[n3] + 1;
            ++n2;
        }
        char[] charSet = new char[26];
        int index = -1;
        int i = 0;
        while (i < 26) {
            if (frequency[i] != 0) {
                charSet[++index] = (char)(i + 97);
            }
            ++i;
        }
        return charSet;
    }
}

