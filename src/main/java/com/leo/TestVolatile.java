package com.leo;

/**
 * volatile语义：
 *  1. 内存可见性
 *     一个线程修改了volatile修饰的变量的值后，新值对另一个线程立即可见
 *  2. 禁止指令重排序优化
 *     对volatile修饰对变量进行读写操作的前后插入内存屏障以达到禁止volatile修饰的变量和其前后其他变量的指令重排序
 *  3. 使用时的表现：
 *     对volatile修饰的变量进行读操作时，将线程工作内存里的变量副本置为无效，然后从主内存读取并刷新工作内存的变量副本
 *     对volatile修饰对变量进行写操作时，将线程工作内存里对变量副本刷新到主内存
 *  4. 注意事项：
 *     volatile只能保证可见性和对此变量单独操作的原子性，当对volatile修饰的变量涉及到复合操作时不能保证原子性，也就不能保证并发的安全性了
 *     比如：
 *     private static volatile int count = 0;
 *     count++; //累加操作是一个复合操作，即使volatile修饰，多线程时也不能保证原子性
 *
 *     private static volatile boolean flag = false;
 *     flag = true; //当作标志位使用时，使用volatile可以保证多线程并发安全性
 *
 */
public class TestVolatile {
    private static int count = 0;
    public static void main(String[] args) {
        NonVolatile nonVolatile = new NonVolatile();
        nonVolatile.start();
        while (!nonVolatile.flag) {
            count++;
        }
        System.out.println("FLAG:TRUE 主程序退出 " + count);

        //Exp1

        //Exp1-1

        //Exp2
        //Integer wrappedInt = new Integer(0);

        //Exp3
        //int nonWrappedInt = 0;

        //Exp4
        //int nonWrappedIntAndPrint = 0;

        //Exp5
        //int nonWrappedIntAndSync = 0;

        //for (;;) {
        //    if (!nonVolatile.flag) {
        //        //count++;
        //
        //        //Exp1-1
        //        //try {
        //        //    Thread.sleep(1);
        //        //} catch (Exception e) {
        //        //    e.printStackTrace();
        //        //}
        //
        //        //Exp2
        //        //wrappedInt++;
        //
        //        //Exp3
        //        //nonWrappedInt++;
        //
        //        //Exp4
        //        //nonWrappedIntAndPrint++;
        //        //System.out.println("COUNT: " + nonWrappedIntAndPrint);
        //
        //        //Exp5
        //        //synchronized (TestVolatile.class) {
        //        //    nonWrappedIntAndSync++;
        //        //}
        //    } else {
        //        //Exp1：线程1睡眠超过10秒设置flag为true，线程2循环判断flag为true后打印并退出程序，结果：线程2死循环
        //        System.out.println("FLAG:TRUE 主程序退出");
        //
        //        //Exp1-1：线程1睡眠超过10秒设置flag为true，线程2循环判断flag为true后打印并退出程序，并且在判断不为true时sleep，结果：线程2正常退出
        //        //System.out.println("FLAG:TRUE 主程序退出");
        //
        //        //Exp2：线程1睡眠超过10秒设置flag为true，线程2循环判断flag为true后打印并退出程序，并且在循环体内累加包装类Integer计数器，结果线程2正常退出
        //        //System.out.println("FLAG:TRUE 主程序退出 " + wrappedInt);
        //
        //        //Exp3：线程1睡眠超过10秒设置flag为true，线程2循环判断flag为true后打印并退出程序，并且在循环体内累加基础类型int计数器，结果线程2死循环
        //        //System.out.println("FLAG:TRUE 主程序退出 " + nonWrappedInt);
        //
        //        //Exp4：线程1睡眠超过10秒设置flag为true，线程2循环判断flag为true后打印并退出程序，并且在循环体内累加基础类型int计数器 并打印计数器的值，结果线程2正常退出
        //        //System.out.println("FLAG:TRUE 主程序退出 " + nonWrappedIntAndPrint);
        //
        //        //Exp5：线程1睡眠超过10秒设置flag为true，线程2循环判断flag为true后打印并退出程序，并且在循环体内累加基础类型int计数器，并且累加操作加锁，结果线程2正常退出
        //        //System.out.println("FLAG:TRUE 主程序退出 " + nonWrappedIntAndSync);
        //
        //        return;
        //    }
        //}
    }

    static class NonVolatile extends Thread {
        private volatile boolean flag = false;
        //private boolean flag = false;
        @Override
        public void run() {
            try {
                // 程序执行结果还依赖于sleep时间
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("SET FLAG=TRUE");
        }
    }
}
