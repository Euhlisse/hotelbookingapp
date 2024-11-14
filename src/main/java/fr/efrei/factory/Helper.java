package fr.efrei.factory;

import java.util.UUID;

public class Helper {
    public static boolean isNullOrEmpty(String str){
        return(str==null||str.isEmpty());
    }


    public static String generateId(){
        return UUID.randomUUID().toString();
    }
}
