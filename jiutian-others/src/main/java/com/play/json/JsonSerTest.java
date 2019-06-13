package com.play.json;

import com.google.gson.*;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *https://monsterlin.com/archives/Java-parse-json.html
 * @author Li Hao
 * @since 2018/9/13  20:57
 */
public class JsonSerTest {
    static String str = "{\"resultcode\":\"200\",\"reason\":\"successed!\",\"result\":{\"sk\":{\"temp\":\"24\",\"wind_direction\":\"西南风\",\"wind_strength\":\"2级\",\"humidity\":\"51%\",\"time\":\"10:11\"},\"today\":{\"temperature\":\"16℃~27℃\",\"weather\":\"阴转多云\",\"weather_id\":{\"fa\":\"02\",\"fb\":\"01\"},\"wind\":\"西南风3-4 级\",\"week\":\"星期四\",\"city\":\"滨州\",\"date_y\":\"2015年06月04日\",\"dressing_index\":\"舒适\",\"dressing_advice\":\"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。\",\"uv_index\":\"最弱\",\"comfort_index\":\"\",\"wash_index\":\"较适宜\",\"travel_index\":\"\",\"exercise_index\":\"较适宜\",\"drying_index\":\"\"},\"future\":[{\"temperature\":\"16℃~27℃\",\"weather\":\"阴转多云\",\"weather_id\":{\"fa\":\"02\",\"fb\":\"01\"},\"wind\":\"西南风3-4 级\",\"week\":\"星期四\",\"date\":\"20150604\"},{\"temperature\":\"20℃~32℃\",\"weather\":\"多云转晴\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"00\"},\"wind\":\"西风3-4 级\",\"week\":\"星期五\",\"date\":\"20150605\"},{\"temperature\":\"23℃~35℃\",\"weather\":\"多云转阴\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"02\"},\"wind\":\"西南风3-4 级\",\"week\":\"星期六\",\"date\":\"20150606\"},{\"temperature\":\"20℃~33℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"北风微风\",\"week\":\"星期日\",\"date\":\"20150607\"},{\"temperature\":\"22℃~34℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"西南风3-4 级\",\"week\":\"星期一\",\"date\":\"20150608\"},{\"temperature\":\"22℃~33℃\",\"weather\":\"阴\",\"weather_id\":{\"fa\":\"02\",\"fb\":\"02\"},\"wind\":\"西南风3-4 级\",\"week\":\"星期二\",\"date\":\"20150609\"},{\"temperature\":\"22℃~33℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"南风3-4 级\",\"week\":\"星期三\",\"date\":\"20150610\"}]},\"error_code\":0}";

    public static void main(String[] args) {
        JsonParser parse = new JsonParser();  //创建json解析器
        try {
            JsonObject json = (JsonObject) parse.parse(str);  //创建jsonObject对象
            System.out.println("resultcode:" + json.get("resultcode").getAsInt());  //将json数据转为为int型的数据
            System.out.println("reason:" + json.get("reason").getAsString());     //将json数据转为为String型的数据

            JsonObject result = json.get("result").getAsJsonObject();
            JsonObject today = result.get("today").getAsJsonObject();
            System.out.println("temperature:" + today.get("temperature").getAsString());
            System.out.println("weather:" + today.get("weather").getAsString());

            JsonArray jsonArray =  result.get("future").getAsJsonArray();
            System.out.println(jsonArray.get(0));

        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}

