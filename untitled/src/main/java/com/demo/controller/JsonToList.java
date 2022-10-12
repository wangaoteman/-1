package com.demo.controller;

import com.sun.glass.ui.Size;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import sun.awt.CustomCursor;

import java.io.*;
import java.util.*;

import static javax.swing.UIManager.getInt;

/**
 * json数组转list<Map>
 */
public class JsonToList {
    public static void main(String[] args) {
        String json = readJson();
        JSONArray array = JSONArray.fromObject(json);
        List<Map<String, String>> list = (List<Map<String, String>>) translate(array);
        System.out.println(list);
        //list存了多少数据
        System.out.println(list.size()+"+++++++");
        for (Map<String,String> map:list){
            String id=map.get("id");
            String name = map.get("name");
            String code = map.get("code");
            String shortName = map.get("shortName");
            String custClassName = map.get("custClassName");  //所在城市
            String detailinfo = map.get("detailinfo");    //客户地址
            String taxpayerid = map.get("taxpayerid");   //税号
            String accnum = map.get("accnum");        //银行账号
            String bankname = map.get("bankname");  //银行

            String search_sql = "select count(1) as count from basic_customer where customer_code = '"+code+"'";
            int count=search_sql.charAt(1);

//            System.out.println(count+"-----");
            String sql="";
            if(count > 0) {
                sql = " update basic_customer set \n" +
                        " customer_name='" + name + "',customer_s_name='" + shortName + "',customer_addr='" + detailinfo + "', \n customer_tax_no='" + taxpayerid + "',customer_bank_name='" + bankname + "', customer_bank_number='" + accnum + "', \n" +
                        " where customer_code='" + code + "'";
//                bean.writeLog("SyncFacOrderJob execute_sql1:"+execute_sql);
//                rs.execute(execute_sql);
            }else {
                sql = "insert into basic_customer \n"+
                        " (customer_code,customer_name,customer_s_name,customer_city,customer_addr,customer_tax_no, \n"+
                        "  customer_bank_number,customer_bank_name ) \n"+
                        " values ('"+code+"','"+name+"','"+shortName+"','"+custClassName+"','"+detailinfo+"',\n"+
                        " '"+taxpayerid+"','"+accnum+"','"+bankname+"')";
            }
        }
    }

    /**
     * 读取json文件
     * @return
     */
    public static String readJson(){

        FileInputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bf = null;
        StringBuffer sf = null;
        try {
            
    //            RestTemplate restTemplate = new RestTemplate();
    //            restTemplate.postForEntity("http://192.168.12.205:8080/external/jc/NcCustomer",);

            inputStream = new FileInputStream(new File("src/main/resources/json/response.json"));
            reader = new InputStreamReader(inputStream, "UTF-8");
            bf = new BufferedReader(reader);
            sf = new StringBuffer();
            String json = null;
            while ((json = bf.readLine()) != null) {
                sf.append(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                reader.close();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sf.toString();
    }
    /**
     * 将JSONArray对象转换成List集合
     *
     * @param json
     * @return
     * @see
     */
    public static Object translate(JSONArray json) {
        List<Object> list = new ArrayList<Object>();
        for (Object o : json) {
            if (o instanceof JSONArray)
                list.add(translate((JSONArray) o));
            else if (o instanceof JSONObject)
                list.add(translate((JSONObject) o));
            else
                list.add(o);
        }
        return list;
    }

    /**
     * 将JSONObjec对象转换成Map集合
     *
     * @param json
     * @return
     * @see
     */
    public static HashMap<String, Object> translate(JSONObject json) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Set keys = json.keySet();
        for (Object key : keys) {
            Object o = json.get(key);
            if (o instanceof JSONArray)
                map.put((String) key, translate((JSONArray) o));
            else if (o instanceof JSONObject)
                map.put((String) key, translate((JSONObject) o));
            else
                map.put((String) key, o);
        }
        return map;
    }
}