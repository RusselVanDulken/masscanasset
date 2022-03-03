import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Nmap;
import org.dom4j.DocumentException;
import util.MasscanParsing;
import util.NmapParsing;
import util.OSUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/2/21	当前系统日期
 * @time 14:59	当前系统时间
 */
public class Main {
    public static String exchangeIP(String ip){
        ip=ip.replace(".","-");
        return ip;
    }
    public static void main(String[] args) throws IOException, DocumentException, InterruptedException {
        final String IP_INSERT = "192.168.103.0/24";
        String IP = IP_INSERT.substring(0,IP_INSERT.indexOf("/"));
        String IP_file = exchangeIP(IP);
//        masscan --ping {segment} --rate 10000 -oX /usr/lib/kbids/data/assetscan/ip_result.xml --wait 0
        final Thread t0 =new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> command0 = new ArrayList<>();
                command0.add("masscan");
                command0.add("--ping");
                command0.add(IP_INSERT);
                command0.add("--rate");
                command0.add("10000");
                command0.add("-oX");
                command0.add("./"+IP_file+"_masscan.xml");
                command0.add("--wait");
                command0.add("0");
                int size = command0.size();
                String[] command=new String[size];
                OSUtil.run(command0.toArray(command));
            }
        });
        //初始化线程t1,nmap扫描形成xml文件
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                MasscanParsing MS = new MasscanParsing();
                ArrayList<String> iplist = MS.masscanParsing("./"+IP_file+"_masscan.xml");
                for (String ip:iplist){
                    String ip_namefile = exchangeIP(ip);
                    ArrayList<String> command = new ArrayList<>();
                    command.add("nmap");
                    command.add("-O");
                    command.add("-A");
                    command.add("-Pn");
                    command.add("-oX");
                    command.add("./"+ip_namefile+".xml");
                    command.add(ip);
                    command.add("--min-rate");
                    command.add("64");
                    command.add("--host-timeout");
                    command.add("64");
                    command.add("--min-parallelism");
                    command.add("64");
                    int size = command.size();
                    String[] command1=new String[size];
                    OSUtil.run(command.toArray(command1));
                }
            }
        });
        //初始化线程t2，解析xml生成json文件
         Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                MasscanParsing MS = new MasscanParsing();
                ArrayList<String> iplist = MS.masscanParsing("./"+IP_file+"_masscan.xml");
                for(String ip:iplist){
                    NmapParsing np =new NmapParsing();
                    String ip_namefile = exchangeIP(ip);
                    ArrayList<Nmap> nmap = new ArrayList<>();
                    try {
                        nmap = np.nampparsing("./"+ip_namefile+".xml");
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    JSONArray jsonArray = JSON.parseArray(JSONObject.toJSONString(nmap));
                    FileWriter f1 ;
                    try {
                        f1 = new FileWriter(ip+".json");
                        f1.write(jsonArray.toJSONString());
                        f1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
         });
         t0.start();
         t0.join(0);
         t1.start();
         t1.join(0);
         t2.start();
    }
}

