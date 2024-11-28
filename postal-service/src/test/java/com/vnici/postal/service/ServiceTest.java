package com.vnici.postal.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vnici.postal.biz.entity.Point;
import com.vnici.postal.biz.entity.PostalArea;
import com.vnici.postal.biz.mapper.PostalAreaDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class ServiceTest {
    public static String URL = "https://api.map.baidu.com/api_region_search/v1/?";

    public static String AK = "jwkFF68VtLAAnukO1PoaBc4ccsEEY2F3";

    @Test
    public void Baidu() throws Exception {

        Map params = new LinkedHashMap<String, String>();
        params.put("keyword", "山东");
        params.put("sub_admin", "2");
        params.put("ak", AK);
        params.put("boundary", "1");
        requestGetAK(URL, params);
    }

    /**
     * 默认ak
     * 选择了ak，使用IP白名单校验：
     * 根据您选择的AK已为您生成调用代码
     * 检测到您当前的ak设置了IP白名单校验
     * 您的IP白名单中的IP非公网IP，请设置为公网IP，否则将请求失败
     * 请在IP地址为0.0.0.0/0 外网IP的计算发起请求，否则将请求失败
     */
    public void requestGetAK(String strUrl, Map<String, String> param) throws Exception {
        if (strUrl == null || strUrl.length() <= 0 || param == null || param.size() <= 0) {
            return;
        }

        StringBuffer queryString = new StringBuffer();
        queryString.append(strUrl);
        for (Map.Entry<?, ?> pair : param.entrySet()) {
            queryString.append(pair.getKey() + "=");
            //    第一种方式使用的 jdk 自带的转码方式  第二种方式使用的 spring 的转码方法 两种均可
            //    queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8").replace("+", "%20") + "&");
            queryString.append(UriUtils.encode((String) pair.getValue(), "UTF-8") + "&");
        }

        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        java.net.URL url = new URL(queryString.toString());
        System.out.println(queryString.toString());
        URLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
       reader.close();
        isr.close();
        System.out.println("AK: " + buffer.toString());
    }

    @Resource
    PostalAreaDao postalAreaDao;

//    @Test
//    public void addPostalArea() {
//        PostalArea postalArea = new PostalArea();
//        postalArea.setMarkColor("blue");
//        postalArea.setAreaPosition("[199.21,34.2]");
//        postalAreaDao.addPostalArea(postalArea);
//        postalArea.setAreaId(postalArea.getId());
//        postalAreaDao.updatePostalAreaById(postalArea);
//    }

    @Test
    public void selectPostalAreas(){
        List<PostalArea> allPostalAreas = postalAreaDao.getAllPostalAreas();
        System.out.println(allPostalAreas);
    }

    @Test
    public void updatePostalAreas(){
        PostalArea postalArea = new PostalArea();
        postalArea.setAreaId(1);
        postalArea.setId(1);
        postalAreaDao.updatePostalAreaById(postalArea);
    }

    @Test
    public void deletePostalArea(){
        postalAreaDao.removePostalAreaById(1);
    }


    @Test
    public void textToPoints(){
        String jsonStr = "[{\"lng\":120.02734710518742,\"lat\":31.789732986341097}," +
                "{\"lng\":120.02687998632223,\"lat\":31.78540526987781}," +
                "{\"lng\":120.03485693924961,\"lat\":31.78350172022476}," +
                "{\"lng\":120.03500066813078,\"lat\":31.789793687584915}," +
                "{\"lng\":120.03482868122538,\"lat\":31.7945541400751}," +
                "{\"lng\":120.03266507380573,\"lat\":31.794459177235524}," +
                "{\"lng\":120.03237761604143,\"lat\":31.78933323788806}," +
                "{\"lng\":120.02734710518742,\"lat\":31.789732986341097}]";
        Gson gson = new Gson();
        List<Point> points = gson.fromJson(jsonStr, new TypeToken<List<Point>>() {}.getType());
        System.out.println(points);
    }
}
