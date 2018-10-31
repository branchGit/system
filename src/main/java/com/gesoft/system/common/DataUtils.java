package com.gesoft.system.common;

import java.util.UUID;

public class DataUtils {

    public static String getUid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
