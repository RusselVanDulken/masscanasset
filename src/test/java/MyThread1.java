import entity.Nmap;
import org.dom4j.DocumentException;
import util.NmapParsing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/3/2	当前系统日期
 * @time 10:51	当前系统时间
 */

class MyThread1 implements Runnable
{
    public int i = 1;

    @Override
    public void run ()
    {
        Thread currThread = Thread.currentThread ();
        synchronized (currThread)
        {
            NmapParsing np =new NmapParsing();
            ArrayList<Nmap> nmap = null;
            try {
                nmap = np.nampparsing("src/main/resources/nmap3.xml");
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            Iterator iterator=nmap.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            ++i;
            System.out.println (this.getClass ().getName () + " i = " + i);
            currThread.notify ();
        }
    }
}

