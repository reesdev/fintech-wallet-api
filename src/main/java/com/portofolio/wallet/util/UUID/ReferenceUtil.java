package com.portofolio.wallet.util.UUID;

import java.util.UUID;

public class ReferenceUtil {
    public static String generate(){
        return UUID.randomUUID().toString();
    }
}
