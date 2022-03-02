import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Nmap;
import org.dom4j.DocumentException;
import util.NmapParsing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/3/1	当前系统日期
 * @time 15:21	当前系统时间
 */
public class test {
    public static void main(String[] args) throws DocumentException, IOException {
        NmapParsing np =new NmapParsing();
        ArrayList<Nmap> nmap = null;
        nmap = np.nampparsing("src/main/resources/nmap3.xml");
//        Iterator iterator=nmap.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        JSONArray jsonArray = JSON.parseArray(JSONObject.toJSONString(nmap));
//        System.out.println(jsonArray.toJSONString());
        FileWriter f1 = new FileWriter("nmap_json.json");
        f1.write(jsonArray.toJSONString());
        f1.close();
    }
}
