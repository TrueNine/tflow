package io.tn.core;

import io.tn.core.res.BizRes;

public class Runner {
    public static void main(String[] args) {
        var a = BizRes.res("a.txt");
        System.out.println(a);
    }
}
