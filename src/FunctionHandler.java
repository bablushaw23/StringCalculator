
public class FunctionHandler {
    Name n = new Name();
    Additionals a = new Additionals();

    public String solve(String data, boolean degree) throws Exception {
        data = data.toLowerCase();
        BracketMagnagement solveFurther = new BracketMagnagement();
        while (this.checkInsideFunction(data)) {
            data = this.solveFunction(data, degree);
        }
        return solveFurther.start(data);
    }

    private String solveFunction(String data, boolean degree) throws Exception {
        Double answer = 0.0;
        String functionName = this.returnFirstFunc(data);
        int lastWordIndex = this.lastIndexOfGivenWord(data, functionName);
        int termLastIndex = this.returnTermLastIndex(data, lastWordIndex);
        String parameter = this.returnUnderPart(data, lastWordIndex, termLastIndex, degree);
        if (functionName.equals(this.n.sin())) {
            answer = this.solveForSine(parameter, degree);
        } else if (functionName.equals(this.n.cosec())) {
            answer = this.solveForCoSecent(parameter, degree);
        } else if (functionName.equals(this.n.cos())) {
            answer = this.solveForCoSine(parameter, degree);
        } else if (functionName.equals(this.n.tan())) {
            answer = this.solveForTangent(parameter, degree);
        } else if (functionName.equals(this.n.sec())) {
            answer = this.solveForSecent(parameter, degree);
        } else if (functionName.equals(this.n.cot())) {
            answer = this.solveForCoTangent(parameter, degree);
        } else if (functionName.equals(this.n.log())) {
            answer = this.solveForLog(parameter);
        } else if (functionName.equals(this.n.ln())) {
            answer = this.solveForLn(parameter);
        }
        int startingIndex = lastWordIndex - functionName.length() + 1;
        Additionals ad = new Additionals();
        data = ad.replacePartOfString(startingIndex, termLastIndex, String.valueOf(answer), data);
        return data;
    }

    private String returnUnderPart(String data, int lastWordIndex, int termLastIndex, boolean degree) throws Exception {
        char letter = data.charAt(lastWordIndex + 1);
        String answer = letter == '(' ? data.substring(lastWordIndex + 2, termLastIndex) : data.substring(lastWordIndex + 1, termLastIndex + 1);
        if (this.checkInsideFunction(answer)) {
            answer = this.solve(answer, degree);
        }
        return answer;
    }

    private String nameOfFunction(int code) throws Exception {
        throw new Exception("Function not implemented as developer wants to use enum but dont know how to use");
    }

    private boolean checkInsideFunction(String data) throws Exception {
        Name name = new Name();
        if (!(data.contains(name.sin()) || data.contains(name.cos()) || data.contains(name.tan()) || data.contains(name.cosec()) || data.contains(name.sec()) || data.contains(name.cot()) || data.contains(name.ln()) || data.contains(name.log()))) {
            return false;
        }
        return true;
    }

    private String returnFirstFunc(String data) throws Exception {
        int counter = -1;
        if (this.checkInsideFunction(data)) {
            String dataPart = "";
            char[] arrc = data.toCharArray();
            int n = arrc.length;
            int n2 = 0;
            while (n2 < n) {
                char i = arrc[n2];
                ++counter;
                if ((dataPart = String.valueOf(dataPart) + String.valueOf(i)).contains(this.n.sin())) {
                    return this.n.sin();
                }
                if (dataPart.contains(this.n.cos())) {
                    try {
                        if (data.charAt(counter + 1) == 'e' && data.charAt(counter + 2) == 'c') {
                            return this.n.cosec();
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                    return this.n.cos();
                }
                if (dataPart.contains(this.n.tan())) {
                    return this.n.tan();
                }
                if (dataPart.contains(this.n.sec())) {
                    return this.n.sec();
                }
                if (dataPart.contains(this.n.cosec())) {
                    return this.n.cosec();
                }
                if (dataPart.contains(this.n.cot())) {
                    return this.n.cot();
                }
                if (dataPart.contains(this.n.log())) {
                    return this.n.log();
                }
                if (dataPart.contains(this.n.ln())) {
                    return this.n.ln();
                }
                ++n2;
            }
        }
        return "nil";
    }

    private int returnTermLastIndex(String data, int termLastIndex) throws Exception {
        int returnIndex;
        block5 : {
            char letter = data.charAt(termLastIndex + 1);
            returnIndex = -1;
            if (letter == '(') {
                int closingBracesIndex = this.findCorrespondingClosingBracses(data, termLastIndex + 1);
                if (closingBracesIndex == -1) {
                    throw new Exception("internal error occured");
                }
                returnIndex = closingBracesIndex == 0 ? data.length() - 1 : closingBracesIndex;
            } else {
                int index = termLastIndex + 1;
                if (data.charAt(index) == '+' || data.charAt(index) == '-') {
                    ++index;
                }
                while (index < data.length()) {
                    if ((letter = data.charAt(index++)) != '+' && letter != '-' && letter != '*' && letter != '/' && letter != '(') continue;
                    returnIndex = index - 2;
                    break block5;
                }
                returnIndex = data.length() - 1;
            }
        }
        return returnIndex;
    }

    private int findCorrespondingClosingBracses(String data, int indexOfOpeningBraces) throws Exception {
        if (data.charAt(indexOfOpeningBraces) == '(') {
            int counter = 1;
            int i = indexOfOpeningBraces + 1;
            while (i < data.length()) {
                char letter = data.charAt(i);
                if (letter == '(') {
                    ++counter;
                } else if (letter == ')' && --counter == 0) {
                    return i;
                }
                ++i;
            }
        }
        throw new Exception("not proper braces");
    }

    private int lastIndexOfGivenWord(String dataSet, String word) throws Exception {
        String data = "";
        int i = 0;
        while (i <= dataSet.length() - word.length() + 1) {
            if (dataSet.charAt(i) == word.charAt(0)) {
                int j = i;
                while (j < i + word.length()) {
                    data = String.valueOf(data) + String.valueOf(dataSet.charAt(j));
                    ++j;
                }
                if (word.equalsIgnoreCase(data)) {
                    return --j;
                }
                data = "";
            }
            ++i;
        }
        return -1;
    }

    private double solveForSine(String parameter, boolean degree) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = degree ? Double.valueOf(Math.sin(Math.toRadians(Double.parseDouble(a.start(parameter))))) : Double.valueOf(Math.sin(Double.parseDouble(a.start(parameter))));
     //   return Double.parseDouble(this.a.calculatorFormat(answer));
        return answer;
    }

    private double solveForCoSine(String parameter, boolean degree) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = degree ? Double.valueOf(Math.cos(Math.toRadians(Double.parseDouble(a.start(parameter))))) : Double.valueOf(Math.cos(Double.parseDouble(a.start(parameter))));
        //return Double.parseDouble(this.a.calculatorFormat(answer));
        return answer;
    }

    private double solveForTangent(String parameter, boolean degree) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = degree ? Double.valueOf(Math.tan(Math.toRadians(Double.parseDouble(a.start(parameter))))) : Double.valueOf(Math.tan(Double.parseDouble(a.start(parameter))));
        return Double.parseDouble(this.a.calculatorFormat(answer));
        //return answer;
    }

    private double solveForCoSecent(String parameter, boolean degree) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = degree ? Double.valueOf(1.0 / Math.sin(Math.toRadians(Double.parseDouble(a.start(parameter))))) : Double.valueOf(1.0 / Math.sin(Double.parseDouble(a.start(parameter))));
        //return Double.parseDouble(this.a.calculatorFormat(answer));
        return answer;
    }

    private double solveForSecent(String parameter, boolean degree) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = degree ? Double.valueOf(1.0 / Math.cos(Math.toRadians(Double.parseDouble(a.start(parameter))))) : Double.valueOf(1.0 / Math.cos(Double.parseDouble(a.start(parameter))));
        //return Double.parseDouble(this.a.calculatorFormat(answer));
        return answer;
    }

    private double solveForCoTangent(String parameter, boolean degree) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = degree ? Double.valueOf(1.0 / Math.tan(Math.toRadians(Double.parseDouble(a.start(parameter))))) : Double.valueOf(1.0 / Math.tan(Double.parseDouble(a.start(parameter))));
        //return Double.parseDouble(this.a.calculatorFormat(answer));
        return answer;
    }

    private double solveForLog(String parameter) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = Math.log10(Double.parseDouble(a.start(parameter)));
        return answer;
    }

    private double solveForLn(String parameter) throws Exception {
        BracketMagnagement a = new BracketMagnagement();
        Double answer = Math.log(Double.parseDouble(a.start(parameter)));
        return answer;
    }

    /*	I want to use enum for better purpose, but i don't know in practicals how to utilize it. So getting another approach 
    private static enum enumFunc {
        sin,
        cos,
        tan,
        cosec,
        sec,
        cot;
        

        private enumFunc(String string2, int n2) {
        }
    }
*/
}

