package kubiks.aoc.day18;

public class FlatCalculator {
    enum Operand {
        PLUS,
        MULTIPLY
    }

    enum TokenType {
        NUMBER,
        OPERAND,
        START_OF_BLOCK,
        END_OF_BLOCK
    }

    class ValueString {
        String s;
        long value;

        public ValueString(long value, String s) {
            this.s = s;
            this.value = value;
        }
    }

    final String NUMBER_REGEX = "[0-9]+.*";
    boolean advancedMode;

    public FlatCalculator(boolean advancedMode) {
        this.advancedMode = advancedMode;
    }

    String removeSpaces(String s) {
        return s.replace(" ", "");
    }

    Operand getOperand(String s) throws FlatCalculatorException {
        if (s.charAt(0) ==  '+') {
            return Operand.PLUS;
        }
        if (s.charAt(0) ==  '*') {
            return Operand.MULTIPLY;
        }
        throw new FlatCalculatorException("Unknown operand at " + s);
    }

    TokenType getNextTokenType(String s) throws FlatCalculatorException {
        if (s.length() == 0) {
            return TokenType.END_OF_BLOCK;
        }
        if (s.matches(NUMBER_REGEX)) {
            return TokenType.NUMBER;
        }
        if (s.charAt(0) == '(') {
            return TokenType.START_OF_BLOCK;
        }
        if (s.charAt(0) == ')') {
            return TokenType.END_OF_BLOCK;
        }
        if (s.charAt(0) == '+' || s.charAt(0) == '*') {
            return TokenType.OPERAND;
        }
        throw new FlatCalculatorException("Unknown token at " + s);
    }

    ValueString calculateBlock(String expression) throws FlatCalculatorException {
        ValueString result = calculate(expression.substring(1));
        TokenType tokenType = getNextTokenType(result.s);
        if (tokenType != TokenType.END_OF_BLOCK) {
            throw new FlatCalculatorException("End of block expected at " + result.s);
        }
        result.s = result.s.substring(1);
        return result;
    }

    ValueString calculateNumber(String expression) {
        int value = Integer.parseInt(expression.substring(0, 1));
        return new ValueString(value, expression.substring(1));
    }

    long applyOperand(long a, long b, Operand operand) throws FlatCalculatorException {
        switch (operand) {
            case PLUS: return a+b;
            case MULTIPLY: return a*b;
        }
        throw new FlatCalculatorException("Unknown operand " + operand.toString());
    }

    ValueString calculate(String expression) throws FlatCalculatorException {
        int pos = 0;
        Operand operand = null;
        long left = 0;
        while (true) {
            ValueString result = null;
            TokenType nextTokenType = getNextTokenType(expression.substring(pos));
            switch (nextTokenType) {
                case END_OF_BLOCK:
                    return new ValueString(left, expression.substring(pos));
                case OPERAND:
                    operand = getOperand(expression.substring(pos));
                    if (advancedMode && operand==Operand.MULTIPLY) {
                        result = calculate(expression.substring(pos+1));
                        break;
                    }
                    pos++;
                    continue;
                case START_OF_BLOCK:
                    result = calculateBlock(expression.substring(pos));
                    break;
                case NUMBER:
                    result = calculateNumber(expression.substring(pos));
                    break;
            }
            if (operand != null) {
                left = applyOperand(left, result.value, operand);
                operand = null;
            } else {
                left = result.value;
            }
            expression = result.s;
            pos = 0;
        }
    }

    public long evaluateExpression(String expression) {
        try {
            ValueString result = calculate(removeSpaces(expression));
            if (result.s.length() > 0) {
                throw new FlatCalculatorException("Data past end of calculation at " + result.s);
            }
            return result.value;
        } catch (FlatCalculatorException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
