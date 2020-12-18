package kubiks.aoc.tests;

import kubiks.aoc.day18.FlatCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlatCalculatorTest {

    @Test
    void evaluateExpression() {
        FlatCalculator calc = new FlatCalculator(false);
        assertEquals(5, calc.evaluateExpression("5"));
        assertEquals(7, calc.evaluateExpression("5+2"));
        assertEquals(10, calc.evaluateExpression("5*2"));
        assertEquals(13, calc.evaluateExpression("5*2+3"));
        assertEquals(21, calc.evaluateExpression("5+2*3"));
        assertEquals(11, calc.evaluateExpression("5+(2*3)"));
        assertEquals(11, calc.evaluateExpression("5+(2*3)"));
        assertEquals(71, calc.evaluateExpression("1 + 2 * 3 + 4 * 5 + 6"));
        assertEquals(51, calc.evaluateExpression("1 + (2 * 3) + (4 * (5 + 6))"));
        assertEquals(26, calc.evaluateExpression("2 * 3 + (4 * 5)"));
        assertEquals(437, calc.evaluateExpression("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
        assertEquals(12240, calc.evaluateExpression("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
        assertEquals(13632, calc.evaluateExpression("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
    }

    @Test
    void evaluateExpressionAdvancedMode() {
        FlatCalculator calc = new FlatCalculator(true);
        assertEquals(5, calc.evaluateExpression("5"));
        assertEquals(7, calc.evaluateExpression("5+2"));
        assertEquals(10, calc.evaluateExpression("5*2"));
        assertEquals(25, calc.evaluateExpression("5*2+3"));
        assertEquals(21, calc.evaluateExpression("5+2*3"));
        assertEquals(21, calc.evaluateExpression("(5+2)*3"));
        assertEquals(11, calc.evaluateExpression("5+(2*3)"));
        assertEquals(51, calc.evaluateExpression("1 + (2 * 3) + (4 * (5 + 6))"));
        assertEquals(46, calc.evaluateExpression("2 * 3 + (4 * 5)"));
        assertEquals(1445, calc.evaluateExpression("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
        assertEquals(669060, calc.evaluateExpression("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
        assertEquals(23340, calc.evaluateExpression("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
    }

}