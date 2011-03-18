package com.btit.cuncu.test.ExceptionCatching;

public class ExceptionCatching{
    public static void main (String args[])
    {
        int i=0;
        int a[] = {5,6,7,8};
        for(i=0;i<5;i++)
        {
            try
            {
                System.out.print("a["+i+"]/"+i+"="+(a[i]/i));
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.print("捕获数组下标越界异常!");
            }
            catch(ArithmeticException e)
            {
                System.out.print("捕获算术异常!");
            }
            catch(Exception e)
            {
                System.out.print("捕获"+e.getMessage()+"异常!");
            }                                      //显示异常信息
            finally
            {
                System.out.println("  finally  i="+i);
            }
        }
        System.out.println("继续!");
    }
}



 