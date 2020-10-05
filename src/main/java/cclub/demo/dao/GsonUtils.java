package cclub.demo.dao;

import com.google.gson.Gson;

public class GsonUtils {
    private static Gson gsonUtils;
    private GsonUtils(){};
    public static Gson getInstance(){
        if(gsonUtils==null){
            synchronized (GsonUtils.class){
                if(gsonUtils==null)return new Gson();
            }
        }
        return gsonUtils;
    }


}
