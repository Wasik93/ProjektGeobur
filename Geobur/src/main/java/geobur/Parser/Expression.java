package geobur.Parser;
import geobur.Errors.BadSyntaxException;

import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {

    String expression;
    ONP onp;

    Stack<Character> stack = new Stack<>();
    public Expression(String F){
        expression = F;
        onp = new ONP(expression);
    }
    public boolean checkSyntax(){
        if(expression.length() > 64){
            return false;
        }
        Pattern pattern = Pattern.compile("[^[\\dx()*+/^\\-]]");
        Matcher matcher = pattern.matcher(expression);
        boolean matchFound = matcher.find();
        if(matchFound){
            return false;
        }
        int counter_0 = 0;
        int counter_1 = 0;
        for(int i = 0; i < expression.length(); i++){
            if(counter_1 > counter_0){
                return false;
            }
            if(expression.charAt(i) == '('){
                counter_0++;
            }
            if(expression.charAt(i) == ')'){
                counter_1++;
            }
        }
        if(counter_1 != counter_0){
            return false;
        }
        for(int i = 1; i < expression.length(); i++){
            if((expression.charAt(i-1) == ')' ||
                    expression.charAt(i-1) == 'x' ||
                    (expression.charAt(i-1) >= '0' && expression.charAt(i-1) <= '9'))
                    && (expression.charAt(i) == '(' ||
                    expression.charAt(i) == 'x' ||
                    (expression.charAt(i) >= '0' && expression.charAt(i) <= '9'))){
                return false;
            }

        }
        onp.createStack();
        return true;
    }

    public double calculate(double aDouble){
        return onp.calculate(aDouble);
    }
}
