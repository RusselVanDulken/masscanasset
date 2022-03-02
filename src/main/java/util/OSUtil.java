package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-09 09:14
 **/

public class OSUtil {
    public static String getOSType() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return "linux";
        }else if(System.getProperty("os.name").toLowerCase().contains("windows")) {
            return "windows";
        }else {
            return "unknownSystemType";
        }
    }

    public static BufferedReader run(String[] command) {
        Process p;
        try {
            //执行命令
            p = Runtime.getRuntime().exec(command);
            //取得命令结果的输出流
            InputStream fis = p.getInputStream();
            InputStream efs = p.getErrorStream();

            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(fis);
            InputStreamReader esr = new InputStreamReader(efs);

            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine())!=null) {
                sb.append(line).append("\n");
            }
//            System.out.println("len= " + sb.toString().length());
            if (sb.toString().length() < 1) {
                return new BufferedReader(esr);
            }

            return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8))));
//            String line=null;
            //直到读完为止
//            while((line=br.readLine())!=null)
//            {
//                System.out.println(line);
//            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean serviceStatus(String[] linuxCommand) throws IOException {
        String line;
        String result;
        BufferedReader commandResult = OSUtil.run(linuxCommand);
        while ((line = Objects.requireNonNull(commandResult).readLine()) != null) {
            if (line.contains("Active:")) {
                if ((line.split("Active:")[1]).contains("running")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
