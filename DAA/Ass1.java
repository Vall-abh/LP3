import java.util.*;

public class Ass1
{

    public static void NRFibonacci(int n)
    {
        int a = 0;
        int b = 1;
        System.out.print(a + " " + b);
        for(int i = 1; i < n; i++)
        {
            int c = a + b;
            System.out.print(" " + c);
            a = b;
            b = c;
        }
    }

    public static int RFibonacci(int a, int b, int n)
    {
        while(n >= 0)
        {
            System.out.print(a + " ");
            return RFibonacci(b, a+b, n-1);
        }
        return 0;
    }

    public static void main(String[] args)
    {
        NRFibonacci(10);
        System.out.println();
        RFibonacci(0, 1, 10);
    }
}