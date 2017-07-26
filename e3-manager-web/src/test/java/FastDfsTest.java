import cn.e3mall.util.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * Created by ZX on 2017/7/26 15:06.
 */
public class FastDfsTest {

    @Test
    public void test_upload() throws Exception {
        //创建配置文件,tracker服务器地址
        //全局对象加载配置文件
        ClientGlobal.init("E:/repository/e3mall/e3-manager-web/src/test/resources/fastDfs_client.conf");
        //创建TrackerClient对象
        TrackerClient client = new TrackerClient();
        //通过TrackerClient获得一个TrackerServer对象
        TrackerServer connection = client.getConnection();
        //创建一个StorageServer的引用
        StorageServer storageServer = null;
        //创建storageClient
        StorageClient storageClient = new StorageClient(connection, storageServer);
        //上传图片
        String[] result = storageClient.upload_file("F:/photo/dog.jpg", "jpg", null);
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void test_fastDfs_util() throws Exception {
        FastDFSClient client =new FastDFSClient("classpath:fastDfs_client.conf");
        String result = client.uploadFile("F:/photo/gx.gif");
        System.out.println(result);
    }
}
