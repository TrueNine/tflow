package io.tn.core.res;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;

@Slf4j
public class BizRes {

    private BizRes() {
    }

    public static URL res(String internalPath) {
        return BizRes.class.getClassLoader().getResource(internalPath);
    }

    public static InputStream resAsInputStream(String internalPath) {
        try {
            return BizRes.class.getClassLoader().getResourceAsStream(internalPath);
        } catch (Throwable e) {
            log.error("工具栏找不到文件路径 ${} ", internalPath, e);
        }
        return null;
    }

    public static byte[] resAsBytes(String internalPath) {
        try (var ins = resAsInputStream(internalPath);) {
            assert ins != null;
            return ins.readAllBytes();
        } catch (IOException | NullPointerException e) {
            log.error("在尝试读取文件 {} 时发生异常", internalPath, e);
        }
        return null;
    }
}
