

import java.util.ArrayList;
import java.util.Iterator;

public class BracketMagnagement {
    public String start(String data) {
        try {
            data = this.manageBrackets(data);
            data = this.polishDataForSignsBeforeBrackets(data);
            return this.solve(data);
        }
        catch (Exception e) {
            throw new ArithmeticException();
        }
    }

    private String solve(String data) throws Exception {
        int[] BracketIndexes;
        calc calc2 = new calc();
        while ((BracketIndexes = this.getBracketIndexes(data)).length != 0) {
            int leastOpeningBracesIndex = BracketIndexes[BracketIndexes.length / 2 - 1];
            int firstClosingBracesIndex = -1;
            int i = BracketIndexes.length / 2;
            while (i < BracketIndexes.length) {
                int val = BracketIndexes[i];
                if (val > leastOpeningBracesIndex) {
                    firstClosingBracesIndex = val;
                    break;
                }
                ++i;
            }
            String soluteDataPart = data.substring(leastOpeningBracesIndex + 1, firstClosingBracesIndex);
            String answerToreplace = String.valueOf(calc2.start(soluteDataPart));
            data = this.replacePartOfString(leastOpeningBracesIndex, firstClosingBracesIndex, answerToreplace, data);
        }
        return data;
    }

    private String replacePartOfString(int startingIndex, int endingIndex, String dataToReplace, String data) {
        String leftPart = data.substring(0, startingIndex);
        String rightPart = data.substring(endingIndex + 1);
        String newData = String.valueOf(leftPart) + dataToReplace + rightPart;
        return newData;
    }

    private int[] getBracketIndexes(String data) {
        char i;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int index = -1;
        char[] arrc = data.toCharArray();
        int n = arrc.length;
        int n2 = 0;
        while (n2 < n) {
            i = arrc[n2];
            ++index;
            if (i == '(') {
                list.add(index);
            }
            ++n2;
        }
        index = -1;
        arrc = data.toCharArray();
        n = arrc.length;
        n2 = 0;
        while (n2 < n) {
            i = arrc[n2];
            ++index;
            if (i == ')') {
                list.add(index);
            }
            ++n2;
        }
        int[] bracketList = new int[list.size()];
        index = 0;
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            int i2 = (Integer)iterator.next();
            bracketList[index++] = i2;
        }
        return bracketList;
    }

    private String polishDataForSignsBeforeBrackets(String data) {
        int[] bracketIndexes = this.getBracketIndexes(data);
        String newData = data;
        int j = 0;
        while (j < bracketIndexes.length) {
            int i = bracketIndexes[j];
            if (i > 0) {
                char symbol = data.charAt(i - 1);
                char symbolAti = data.charAt(i);
                if (symbolAti == '(' && symbol != '+' && symbol != '-' && symbol != '/' && symbol != '*' && symbol != '(' && symbol != '^' && symbol != '!' && symbol != '\u221a' && symbol != 'c' && symbol != 'p') {
                    newData = this.replacePartOfString(i, i - 1, "*", data);
                } else if (symbolAti == ')' && i + 1 != data.length() && (symbol = data.charAt(i + 1)) != '+' && symbol != '-' && symbol != '/' && symbol != '*' && symbol != ')' && symbol != '^' && symbol != '!' && symbol != '\u221a' && symbol != 'c' && symbol != 'p') {
                    newData = this.replacePartOfString(i + 1, i, "*", data);
                }
            }
            ++j;
        }
        return newData;
    }

    private String manageBrackets(String data) {
        String newData;
        int closingParanthesisNo;
        data = String.valueOf('(') + data + ')';
        int openingParanthesisNo = this.counterofSameCharWithinString(data, "(");
        if (openingParanthesisNo == (closingParanthesisNo = this.counterofSameCharWithinString(data, ")"))) {
            newData = data;
        } else if (openingParanthesisNo > closingParanthesisNo) {
            String closingP = "";
            int i = 0;
            while (i < openingParanthesisNo - closingParanthesisNo) {
                closingP = String.valueOf(closingP) + ")";
                ++i;
            }
            newData = String.valueOf(data) + closingP;
        } else {
            throw new ArithmeticException("Closing brackets are more than opening");
        }
        return newData;
    }

    private int counterofSameCharWithinString(String data, String toCount) {
        int count = 0;
        int index = 0;
        do {
            if ((index = data.indexOf(toCount, index) + 1) == 0) continue;
            ++count;
        } while (index != 0);
        return count;
    }
}

