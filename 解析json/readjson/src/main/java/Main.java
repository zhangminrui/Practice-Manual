import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\userLogin\\userLogin.json");
        FileInputStream inputStream = new FileInputStream(file);
        List<String> lines = IOUtils.readLines(inputStream);
        JSONObject jsonObj = (JSONObject) JSON.parse(lines.get(0));
        JSONObject hitsObj = jsonObj.getJSONObject("hits");
        JSONArray hitsAry = hitsObj.getJSONArray("hits");
        File target = new File("D:\\userLogin\\userLogin.xlsx");

        List<String> targetLines = new ArrayList<>();
        targetLines.add("\"员工\",\"访问地址\"");
        for (Object obj : hitsAry) {
            JSONObject hitObj = (JSONObject) obj;
            JSONObject sourceObj = hitObj.getJSONObject("_source");
            String staff = sourceObj.getString("staffName");
            String url = sourceObj.getString("sourceDetails");
            targetLines.add("\"" + staff + "\",\"" + url + "\"");
        }
        FileOutputStream outputStream = new FileOutputStream(target);
        IOUtils.writeLines(targetLines, "\n", outputStream);

    }
}
