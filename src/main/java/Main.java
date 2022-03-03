import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Nmap;
import org.dom4j.DocumentException;
import util.AssetDet;
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
    public static void main(String[] args) throws IOException, DocumentException, InterruptedException {
        String IP_INSERT = "192.168.103.0/24";
        AssetDet.assetDetUtil(IP_INSERT);
    }
}

