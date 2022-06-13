package io.tn.core.res;

import java.net.URL;

public class BizRes {
    public static URL res(String internalPath) {
        return BizRes.class.getClassLoader().getResource(internalPath);
    }
}
