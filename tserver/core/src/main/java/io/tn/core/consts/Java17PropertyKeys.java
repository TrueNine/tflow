package io.tn.core.consts;

public final class Java17PropertyKeys extends JFS {
    Java17PropertyKeys() {
    }

    @Override
    public String jVersion() {
        return f().getProperty(J17Keys.VERSION);
    }

    @Override
    public String arch() {
        return f().getProperty(J17Keys.CPU_ARCH);
    }

    @Override
    public String osName() {
        return f().getProperty(J17Keys.OS_NAME);
    }

    @Override
    public String fileSep() {
        return f().getProperty(J17Keys.SYS_FILE_SEPARATOR);
    }

    @Override
    public String pathSep() {
        return f().getProperty(J17Keys.SYS_PATH_SEPARATOR);
    }

    @Override
    public String lineSep() {
        return f().getProperty(J17Keys.SYS_LINE_SEPARATOR);
    }

    @Override
    public String encodeN() {
        return f().getProperty(J17Keys.SYS_ENCODING);
    }
}
