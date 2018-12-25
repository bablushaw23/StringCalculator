
import java.util.ArrayList;
import java.util.Iterator;

public class calc {
    public double start(String data) throws Exception {
        data = this.polishDataForCharAt0(data);
        data = this.removeMultiplesigns(data);
        while (!this.over(data)) {
            double answer;
            String answerToSet;
            int[] operatorsIndex = this.operatorIndexList(data);
            int operatorIndex = this.searchHigherPresedenceOperatorWithLowerIndex(data);
            char operator = data.charAt(operatorIndex);
            double rightOperand = 0.0;
            double leftOperand = 0.0;
            String leftOperandinString = null;
            String rightOperandinString = null;
            if (operator != '\u221a') {
                leftOperandinString = this.findLeftOperand(data, operatorIndex, operatorsIndex);
                leftOperand = Double.parseDouble(leftOperandinString);
            }
            if (operator != '!') {
                rightOperandinString = this.findRightOperand(data, operatorIndex, operatorsIndex);
                rightOperand = Double.parseDouble(rightOperandinString);
            }
            if ((answerToSet = String.valueOf(answer = this.calculate(operator, leftOperand, rightOperand))).charAt(0) != '-' && operator != '\u221a') {
                answerToSet = "+" + answerToSet;
            }
            if (operator == '!' && data.length() > operatorIndex + 1) {
                boolean flag = false;
                int[] arrn = operatorsIndex;
                int n = arrn.length;
                int n2 = 0;
                while (n2 < n) {
                    int i = arrn[n2];
                    if (i == operatorIndex + 1) {
                        flag = true;
                    }
                    ++n2;
                }
                if (!flag) {
                    data = String.valueOf(data.substring(0, operatorIndex + 1)) + "*" + data.substring(operatorIndex + 1);
                }
            }
            data = this.placeAnswerWithinData(answerToSet, leftOperandinString, rightOperandinString, data, String.valueOf(operator));
        }
        return Double.parseDouble(data);
    }

    private boolean over(String data) {
        int[] operatorsIndex = this.operatorIndexList(data);
        int lengthofOperatorsIndex = 0;
        int[] arrn = operatorsIndex;
        int n = arrn.length;
        int n2 = 0;
        while (n2 < n) {
            int i = arrn[n2];
            ++lengthofOperatorsIndex;
            ++n2;
        }
        if (operatorsIndex[0] == 0) {
            return lengthofOperatorsIndex <= 1;
        }
        return lengthofOperatorsIndex <= 0;
    }

    private String polishDataForCharAt0(String data) {
        char charAt0 = data.charAt(0);
        if (charAt0 == '*' || charAt0 == '/') {
            throw new ArithmeticException("Invalid String");
        }
        String newData = charAt0 != '+' && charAt0 != '-' ? "+" + data : data;
        return newData;
    }

    private String placeAnswerWithinData(String answerToSet, String leftOperand, String rightOperand, String Data, String operator) {
        String partOfDataToRemove = rightOperand == null ? String.valueOf(leftOperand) + operator : (leftOperand == null ? String.valueOf(operator) + rightOperand : String.valueOf(leftOperand) + operator + rightOperand);
        String newData = Data.replace(partOfDataToRemove, answerToSet);
        return newData;
    }

    private double calculate(char operator, double operand1, double operand2) {
        double result = 0.0;
        if (operator == '+') {
            result = operand1 + operand2;
        } else if (operator == '^') {
            result = Math.pow(operand1, operand2);
        } else if (operator == '-') {
            result = operand1 - operand2;
        } else if (operator == '*') {
            result = operand1 * operand2;
        } else if (operator == '/') {
            result = operand2 == 0.0 && operand1 > 0.0 ? Double.POSITIVE_INFINITY : (operand2 == 0.0 && operand1 < 0.0 ? Double.NEGATIVE_INFINITY : operand1 / operand2);
        } else if (operator == '!') {
            result = this.factorial(operand1);
        } else if (operator == 'p') {
            result = this.factorial(operand1) / this.factorial(operand1 - operand2);
        } else if (operator == 'c') {
            result = this.factorial(operand1) / (this.factorial(operand2) * this.factorial(operand1 - operand2));
        } else if (operator == '\u221a') {
            result = Math.sqrt(operand2);
        }
        return result;
    }

    private double factorial(double operand) {
        double result = 1.0;
        double operandPositive = Math.abs(operand);
        if (operandPositive == 1.0 || operandPositive == 0.0) {
            result = 1.0;
        } else {
            int i = 2;
            while ((double)i <= operandPositive) {
                result *= (double)i;
                ++i;
            }
        }
        if (operand < 0.0) {
            result *= -1.0;
        }
        return result;
    }

    private String findRightOperand(String data, int operatorIndex, int[] operatorsIndex) {
        int rightOperatorIndex = this.rightOperatorIndexFromGivenIndexExceptInstantRight(operatorIndex, operatorsIndex);
        String rightOperand = rightOperatorIndex == -1 ? data.substring(operatorIndex + 1, data.length()) : data.substring(operatorIndex + 1, rightOperatorIndex);
        return rightOperand;
    }

    private String findLeftOperand(String data, int operatorIndex, int[] operatorsIndex) {
        int leftOperatorIndex = this.leftOperatorIndexfromGivenIndex(operatorIndex, operatorsIndex);
        String leftOperand = "";
        if (leftOperatorIndex == -1) {
            leftOperand = String.valueOf(leftOperand) + "+";
            leftOperand = String.valueOf(leftOperand) + data.substring(0, operatorIndex);
        } else {
            char symbol = data.charAt(leftOperatorIndex);
            leftOperand = leftOperatorIndex > 0 ? ((symbol == '+' || symbol == '-') && data.charAt(leftOperatorIndex - 1) != 'E' ? data.substring(leftOperatorIndex, operatorIndex) : data.substring(leftOperatorIndex + 1, operatorIndex)) : data.substring(leftOperatorIndex, operatorIndex);
        }
        return leftOperand;
    }

    private int rightOperatorIndexFromGivenIndexExceptInstantRight(int index, int[] operatorsIndexList) {
        int rightIndex = -1;
        int[] arrn = operatorsIndexList;
        int n = arrn.length;
        int n2 = 0;
        while (n2 < n) {
            int i = arrn[n2];
            if (i > index + 1) {
                rightIndex = i;
                break;
            }
            ++n2;
        }
        return rightIndex;
    }

    private int leftOperatorIndexfromGivenIndex(int index, int[] operatorsIndexList) {
        int leftIndex = -1;
        int[] arrn = operatorsIndexList;
        int n = arrn.length;
        int n2 = 0;
        while (n2 < n) {
            int i = arrn[n2];
            if (i >= index) break;
            leftIndex = i;
            ++n2;
        }
        return leftIndex;
    }

    private int searchHigherPresedenceOperatorWithLowerIndex(String data) {
        if (data.contains("^")) {
            return data.indexOf("^");
        }
        if (data.contains("!")) {
            return data.indexOf("!");
        }
        if (data.contains("√")) {			//square root here
            return data.indexOf("√");
        }
        if (data.contains("p") || data.contains("c")) {
            int PIndex = data.indexOf("p");
            int CIndex = data.indexOf("c");
            if (PIndex != -1 && CIndex != -1) {
                return Math.min(PIndex, CIndex);
            }
            if (PIndex == -1) {
                return CIndex;
            }
            return PIndex;
        }
        if (data.contains("*") || data.contains("/")) {
            int multIndex = data.indexOf("*");
            int divIndex = data.indexOf("/");
            if (multIndex != -1 && divIndex != -1) {
                return Math.min(multIndex, divIndex);
            }
            if (multIndex == -1) {
                return divIndex;
            }
            return multIndex;
        }
        if (data.contains("+") || data.contains("-")) {
            int sumIndex = data.indexOf("+", 1);
            int subIndex = data.indexOf("-", 1);
            if (subIndex != -1 && sumIndex != -1) {
                return Math.min(sumIndex, subIndex);
            }
            if (subIndex == -1) {
                return sumIndex;
            }
            return subIndex;
        }
        return -1;
    }

    private String removeMultiplesigns(String data) throws Exception {
        int continuous = 0;
        boolean iBefore = false;
        int i = 0;
        while (i < data.length()) {
            if (!(data.charAt(i) != '+' && data.charAt(i) != '-' || iBefore)) {
                ++continuous;
                iBefore = true;
            } else if ((data.charAt(i) == '+' || data.charAt(i) == '-') && iBefore) {
                ++continuous;
            } else {
                if (iBefore && continuous > 2) {
                    String toReplace = data.substring(i - continuous, i);
                    String s = String.valueOf(this.solveSign(toReplace));
                    data = this.replacePartOfString(i - continuous, i - 1, s, data);
                    i = i - continuous + 1;
                }
                iBefore = false;
                continuous = 0;
            }
            ++i;
        }
        return data;
    }

    private char solveSign(String signs) throws Exception {
        String data = "";
        int length = signs.length();
        int i = 1;
        while (i < length) {
            data = String.valueOf(data) + String.valueOf(signs.charAt(0)) + "1*";
            signs = signs.substring(1, signs.length());
            ++i;
        }
        calc c = new calc();
        double ans = c.start(data = String.valueOf(data) + data.charAt(0) + "1");
        if (ans < 0.0) {
            return '-';
        }
        return '+';
    }

    private String replacePartOfString(int startingIndex, int endingIndex, String dataToReplace, String data) throws Exception {
        Additionals additional = new Additionals();
        return additional.replacePartOfString(startingIndex, endingIndex, dataToReplace, data);
    }

    private int[] operatorIndexList(String data) {
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        int i = 0;
        while (i < data.length()) {
            char word = data.charAt(i);
            if (i > 0) {
                if (word == '+' && data.charAt(i - 1) != 'E' || word == '-' && data.charAt(i - 1) != 'E' || word == '*' || word == '/' || word == '^' || word == '!' || word == 'p' || word == 'c' || word == '\u221a') {
                    indexList.add(i);
                }
            } else if (word == '+' || word == '-' || word == '*' || word == '/' || word == '^' || word == '!' || word == 'p' || word == 'c' || word == '\u221a') {
                indexList.add(i);
            }
            ++i;
        }
        int[] indexArr = new int[indexList.size()];
        int index = -1;
        Iterator iterator = indexList.iterator();
        while (iterator.hasNext()) {
            int i2 = (Integer)iterator.next();
            indexArr[++index] = i2;
        }
        return indexArr;
    }
}

