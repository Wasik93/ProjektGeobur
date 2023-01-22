package geobur.Parser;

import java.util.HashMap;
import java.util.Stack;

public class ONP {
    Stack<Character> stack = new Stack<>();

    Stack<Double> stackValue = new Stack<>();
    String expression;
    String onpExpression;

    HashMap<Character, Integer> priorityMap;

    public ONP(String F){
        expression = F;
        priorityMap = new HashMap<>();
        priorityMap.put('+', 0);
        priorityMap.put('-', 0);
        priorityMap.put('*', 1);
        priorityMap.put('/', 1);
        priorityMap.put('^', 2);
        priorityMap.put('(', -2137);
    }

    public void createStack(){
        stack.push('(');
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == 'x' || (expression.charAt(i) >= '0' && expression.charAt(i) <= '9')){
                stringBuilder.append(expression.charAt(i));
            }
            else if(expression.charAt(i) == '('){
                stack.push('(');
            }
            else if(expression.charAt(i) == ')'){
                while(stack.peek() != '('){
                    stringBuilder.append(stack.peek());
                    stack.pop();
                }
                stack.pop();
            }
            else{
                if(stack.peek() == '('){
                    stack.push(expression.charAt(i));
                }
                else if(priorityMap.get(expression.charAt(i)) > priorityMap.get(stack.peek())){
                    stack.push(expression.charAt(i));
                }
                else{
                    while(priorityMap.get(expression.charAt(i)) <= priorityMap.get(stack.peek())){
                        stringBuilder.append(stack.peek());
                        stack.pop();
                    }
                    stack.push(expression.charAt(i));
                }
            }
        }
        while(!stack.empty()){
            if(stack.peek() == '('){
                stack.pop();
                break;
            }
            stringBuilder.append(stack.peek());
            stack.pop();
        }
        stack.clear();
        onpExpression = stringBuilder.toString();

    }

    public double calculate(double aDouble){
        stackValue.clear();
        int k = onpExpression.length();
        double a, b;
        for(int i = 0; i < k; i++){
            char charA = onpExpression.charAt(i);
            if(charA == 'x'){
                stackValue.push(aDouble);
            }
            else if (charA >= '0' && charA <= '9'){
                stackValue.push((double)(charA - '0'));
            }
            else{
                a = stackValue.peek();
                stackValue.pop();
                b = stackValue.peek();
                stackValue.pop();
                if(charA == '+'){
                    a = a +b;
                    stackValue.push(a);
                }
                if(charA == '-'){
                    a = b - a;
                    stackValue.push(a);
                }
                if(charA == '*'){
                    a = b * a;
                    stackValue.push(a);
                }
                if(charA == '/'){
                    if(b == 0.0){
                        stackValue.push(0.0);
                    }
                    else{
                        a = b/a;
                        stackValue.push(a);
                    }
                }
                if(charA == '^'){
                    a = Math.pow(b, a);
                    stackValue.push(a);
                }
            }
        }
        return stackValue.peek();
    }

}
