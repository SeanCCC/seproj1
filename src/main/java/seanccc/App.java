package seanccc;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App 
{
    static int getOrder(char input){
        switch(input){
            case '*': case '/':
                return 2;
            case '+': case '-':
                return 1;
            default:
                return 0;
        }
    }

    public static int op(int opr, int lhs, int rhs){
        switch(opr){
            case 43:
                return lhs+rhs;
            case 45:
                return lhs-rhs;
            case 42:
                return lhs*rhs;
            case 47:
                return lhs/rhs;
            default:
                return -1;
        }
    }

    static int eval(Integer[] postfix){
        int i=0;
        Stack<Integer> st = new Stack<Integer>();
        for(i=0;i<postfix.length;i++){
            switch(postfix[i]){
                case 42: case 43: case 47: case 45:
                    int rhs = st.pop();
                    int lhs = st.pop();
                    st.push(op(postfix[i],lhs,rhs));
                    break;
                default:
                    st.push(postfix[i]*-1);
                    break;
            }
        }
        return st.pop();
    }

    static Stack<Integer> infix2postfix(char[] infix){
        Stack<Character> st = new Stack<Character>();
        Stack<Integer> postfix = new Stack<Integer>();
        int i,cvTmp;
        // System.out.println("flag1");
        for(i=0;i<infix.length;i++){
            // System.out.println("handling char "+infix[i]);
            switch(infix[i]) {
                case '(':
                    st.push(infix[i]);
                    break;
                case '+': case '-': case '*': case '/':
                    // System.out.println("fa"+" "+infix[i]);
                    while(!st.empty() && getOrder(st.peek()) >= getOrder(infix[i])){
                        cvTmp = st.pop();
                        postfix.push(cvTmp);
                    }
                    st.push(infix[i]);
                    break;
                case ')':
                    while(st.peek() != '(') {
                        cvTmp = st.pop();
                        postfix.push(cvTmp);
                    }
                    st.pop();
                    break;
                default:
                    int numCnt = 0;
                    while( i+numCnt+1<infix.length && Character.isDigit(infix[i+numCnt+1])){
                        numCnt++;
                    }
                    cvTmp = Integer.parseInt(new String(infix).substring(i, i+numCnt+1));
                    i=i+numCnt;
                    postfix.push(cvTmp*-1);
            }
        }
        // System.out.println("flag2");
        while(!st.empty()){
            cvTmp = st.pop();
            postfix.push(cvTmp);
        }
        return postfix;
    }

    static int solver(String input){
        Stack<Integer> postfix;
        Vector postfixVec = new Vector<Integer>();
        Stack<Integer> tmp = new Stack<Integer>();
        char[] charArray = input.toCharArray();
        postfix = infix2postfix(charArray);
        while(!postfix.empty()){
            tmp.push(postfix.pop());
        }
        while(!tmp.empty()){
            postfixVec.addElement(tmp.pop());
        }
        Integer[] a = new Integer[postfixVec.size()];
        postfixVec.toArray(a);
        return eval(a);
    }

    public static void main(String args[]) {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        scanner.close();
        System.out.println(input+"="+solver(input));

//        Stack<Integer> postfix;
//        Vector postfixVec = new Vector<Integer>();
//        Stack<Integer> tmp = new Stack<Integer>();
//        postfix = infix2postfix(charArray);
//        while(!postfix.empty()){
//            tmp.push(postfix.pop());
//        }
//        while(!tmp.empty()){
//            postfixVec.addElement(tmp.pop());
//        }
//        Integer[] a = new Integer[postfixVec.size()];
//        postfixVec.toArray(a);
//        System.out.println(input+"="+eval(a));
    }
}
