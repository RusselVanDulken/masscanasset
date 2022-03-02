/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/3/2	当前系统日期
 * @time 10:52	当前系统时间
 */
class MyThread2 implements Runnable
{
    @Override
    public void run ()
    {
        Thread currThread = Thread.currentThread ();
        synchronized (currThread)
        {
            while ("100000000000".equals (currThread.getName ()))
            {
                try
                {
                    currThread.wait (10000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace ();
                }
            }
            done ();
        }
    }

    public synchronized void done ()
    {
        System.out.println ("更改完毕");
    }
}
