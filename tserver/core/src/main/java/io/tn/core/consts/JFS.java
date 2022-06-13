package io.tn.core.consts;

import java.util.Properties;

/**
 * 全称：java field s
 *
 * @author TrueNine
 */
public sealed abstract class JFS permits Java17PropertyKeys {


    public static JFS o() {
        return switch (f().get(J17Keys.VERSION).toString()) {
            default -> new Java17PropertyKeys();
        };
    }

    protected static Properties f() {
        return System.getProperties();
    }

    public abstract String jVersion();

    public abstract String arch();

    public abstract String osName();

    public abstract String fileSep();

    public abstract String pathSep();

    public abstract String lineSep();

    public abstract String encodeN();

    static class J17Keys {
        public static final String USER_NAME = "user.name";
        public static final String VERSION = "java.specification.version";
        public static final String CPU_ARCH = "sun.cpu.isalist";
        public static final String OS_ARCH = "os.arch";
        public static final String OS_NAME = "os.name";

        public static final String SYS_ENCODING = "native.encoding";
        public static final String SYS_FILE_SEPARATOR = "file.separator";
        public static final String SYS_PATH_SEPARATOR = "path.separator";
        public static final String SYS_LINE_SEPARATOR = "line.separator";

    }
}
