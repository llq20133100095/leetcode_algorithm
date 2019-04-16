package leetcodeAlgorithm;

import java.math.BigDecimal;
import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    public static ListNode put(int result,ListNode l){
        ListNode oldfirst=l;
        l=new ListNode(result);
        l.next=oldfirst;
        return l;
    }
}

public class leetcode {

    public static void store(Stack<Integer> stack,ListNode l){
        if(l==null){
            return;
        }
        stack.push(l.val);
        store(stack,l.next);
    }

    public static String num(Stack<Integer> stack){
        String s="";
        while(!stack.isEmpty()){
            s=s+stack.pop();
        }
        return s;
    }

    public static ListNode put(char result,ListNode l){
        ListNode oldfirst=l;
        l=new ListNode(Integer.parseInt(String.valueOf(result)));
        l.next=oldfirst;
        return l;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        store(stack1,l1);
        store(stack2,l2);

        String s1=null;
        String s2=null;
        s1=num(stack1);
        s2=num(stack2);

        Double add=Double.parseDouble(s1)+Double.parseDouble(s2);

        String result=new BigDecimal(add).toPlainString();
        String[] resultInt=result.split("\\.");
        System.out.println(result);
        char[] resultArray=resultInt[0].toCharArray();
        ListNode lResult=new ListNode(Integer.parseInt(String.valueOf(resultArray[0])));
        for(int i=1;i<resultArray.length;i++){
            lResult=put(resultArray[i],lResult);
        }
        return lResult;
    }


    public static void main(String[] args) {
        ListNode ln1 = new ListNode(9);

        ListNode ln2 = new ListNode(9);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(9, ln2);
        ln2 = ln2.put(1, ln2);

        ListNode lResult = addTwoNumbers(ln1, ln2);

        StringBuilder a = new StringBuilder();
        a.append("121");
        a.append("22");
        a.delete(1, 2);

        System.out.println(a.indexOf("3"));

    }




}
