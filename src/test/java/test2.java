/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/3/2	当前系统日期
 * @time 13:51	当前系统时间
 */
public class test2 {
    public static void main(String[] args) {
        final String IP_INSERT = "192.168.103.150";
        String IP = IP_INSERT.substring(0,IP_INSERT.indexOf("0"));
        System.out.println(IP);
    }
}
