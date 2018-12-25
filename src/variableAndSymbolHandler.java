
public class variableAndSymbolHandler {
    private Additionals a = new Additionals();

    public double solve(String eqn, boolean degree, int noOfVars, char[] varChar, double[] varValues) throws Exception {
        eqn = this.replaceVarWithValues(eqn, noOfVars, varChar, varValues);
        return this.solve(eqn, degree);
    }

    public double solve(String eqn, boolean degree) throws Exception {
        eqn = this.replaceSymbolsWithValues(eqn);
        FunctionHandler f = new FunctionHandler();
        eqn = f.solve(eqn, degree);
        return Double.parseDouble(eqn);
    }

    private String replaceVarWithValues(String eqn, int noOfVars, char[] varChar, double[] varValues) throws Exception {
        int index = -1;
        char[] arrc = this.a.invalidVarSet();
        int n = arrc.length;
        int n2 = 0;
        while (n2 < n) {
            char i = arrc[n2];
            char[] arrc2 = varChar;
            int n3 = arrc2.length;
            int n4 = 0;
            while (n4 < n3) {
                char j = arrc2[n4];
                if (i == j) {
                    throw new Exception("Variable taken from invalid variables set");
                }
                ++n4;
            }
            ++n2;
        }
        index = 0;
        while (index < noOfVars) {
            char var = varChar[index];
            Double varValue = varValues[index];
            eqn = this.AuxillaryReplaceVarWithValues(eqn, var, varValue);
            ++index;
        }
        return eqn;
    }

    private String AuxillaryReplaceVarWithValues(String eqn, char var, Double Value) throws Exception {
        while (eqn.contains(String.valueOf(var))) {
            int indexOfVar = eqn.indexOf(var);
            boolean isSymbol = false;
            try {
                isSymbol = variableAndSymbolHandler.whetherSymbolOrNot(eqn, indexOfVar - 1);
            }
            catch (IndexOutOfBoundsException e) {
                isSymbol = true;
            }
            eqn = isSymbol ? this.a.replacePartOfString(indexOfVar, indexOfVar, "(" + String.valueOf(Value) + ")", eqn) : this.a.replacePartOfString(indexOfVar, indexOfVar, "*(" + String.valueOf(Value) + ")", eqn);
        }
        return eqn;
    }

    private String replaceSymbolsWithValues(String eqn) throws Exception {
        while (eqn.contains("π")) {				// pi used here
            int indexOfPi = eqn.indexOf("π");
            boolean isSymbol = false;
            try {
                isSymbol = variableAndSymbolHandler.whetherSymbolOrNot(eqn, indexOfPi - 1);
            }
            catch (IndexOutOfBoundsException e) {
                isSymbol = true;
            }
            eqn = isSymbol ? this.a.replacePartOfString(indexOfPi, indexOfPi, String.valueOf(3.141592653589793), eqn) : this.a.replacePartOfString(indexOfPi, indexOfPi, "*" + String.valueOf(3.141592653589793), eqn);
        }
        return eqn;
    }

    private static boolean whetherSymbolOrNot(String data, int Index) throws IndexOutOfBoundsException {
        char val = data.charAt(Index);
        if (val != '+' && val != '-' && val != '*' && val != '/' && val != '\u221a' && val != '(' && val != ')' && Character.toLowerCase(val) != 'c' && Character.toLowerCase(val) != 'p' && Character.toLowerCase(val) != 'g' && Character.toLowerCase(val) != 'n') {
            return false;
        }
        return true;
    }
}

