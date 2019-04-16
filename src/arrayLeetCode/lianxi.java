package arrayLeetCode;

/**
 * Created by llq on 2017/7/14.
 */
public class lianxi {
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >=0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                break;
            } else {
                digits[i] = 0;
            }
        }
        if (digits[0] == 0) {
            int[] res = new int[digits.length+1];
            res[0] = 1;
            return res;
        }
        return digits;
    }

    public static void main(String[] args){
        int a1=1;
        while(a1<=5){
            a1++;
        }
        System.out.println(a1);
        for(int i=1;i<=10;i++){
            a1++;
        }
        System.out.println(a1);

        double[] a=new double[10];
        for(double x:a){
            System.out.print(x+" ");
        }
        System.out.println();

        int[] input=new int[]{0};
        int[] output=plusOne(input);
        for(int x:output){
            System.out.print(x);
        }


    }
}
