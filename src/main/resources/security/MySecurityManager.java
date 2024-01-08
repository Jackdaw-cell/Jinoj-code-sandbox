
import java.io.FileDescriptor;

public class MySecurityManager extends SecurityManager{

    /***
     * 检查程序是否可执行
     * @param cmd
     */
    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("可执行权限异常：" + cmd);
    }

    /***
     * 检查程序是否可读
     * @param fd
     */
    @Override
    public void checkRead(FileDescriptor fd) {
        throw new SecurityException("可读权限异常：" + fd);
    }

    /**
     * 检查程序是否可读
     * @param file
     */
    @Override
    public void checkRead(String file) {
        //放行 F:\Jinoj\jinoj-code-sandbox\tmpCode 目录的文件
        if (file.contains("F:\\Jinoj\\jinoj-code-sandbox\\tmpCode")) {
            return;
        }
        throw new SecurityException("可读权限异常：" + file);
    }

    /**
     * 检查程序是否可读
     * @param file
     * @param context
     */
    @Override
    public void checkRead(String file, Object context) {
        //放行 F:\Jinoj\jinoj-code-sandbox\tmpCode 目录的文件
        if (file.contains("F:\\Jinoj\\jinoj-code-sandbox\\tmpCode")) {
            return;
        }
        throw new SecurityException("可读权限异常：" + file);
    }

    /***
     * 检查程序是否可写
     * @param fd
     */
    @Override
    public void checkWrite(FileDescriptor fd) {
        throw new SecurityException("写入权限异常：" + fd);

    }

    /***
     * 检查程序是否可写
     * @param file
     */
    @Override
    public void checkWrite(String file) {
        throw new SecurityException("写入权限异常：" + file);
    }

    /**
     * 检查程序是否可删除
     * @param file
     */
    @Override
    public void checkDelete(String file) {
        throw new SecurityException("删除权限异常：" + file);
    }

    /***
     * 检查程序是否连接网络
     * @param host
     * @param port
     */
    @Override
    public void checkConnect(String host, int port) {
        throw new SecurityException("网络权限异常：" + host+":"+port);
    }

    /***
     * 检查程序是否可连接网络
     * @param host
     * @param port
     * @param context
     */
    @Override
    public void checkConnect(String host, int port, Object context) {
        throw new SecurityException("网络权限异常：" + host+":"+port);
    }
}
