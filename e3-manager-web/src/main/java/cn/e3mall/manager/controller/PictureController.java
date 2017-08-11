package cn.e3mall.manager.controller;

import cn.e3mall.util.FastDFSClient;
import cn.e3mall.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片处理控制类
 * Created by ZX on 2017/7/26 16:06.
 */
@Controller
@RequestMapping("/pic")
public class PictureController {
    @Value("${img_server_url}")
    String IMAGE_SERVER_URL;

    @RequestMapping(value = "/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String upload(MultipartFile uploadFile) {
        Map<String, Object> result = new HashMap<>();
        //把图片上传到文件服务器
        try {
            FastDFSClient client = new FastDFSClient("classpath:fastDfs_client.conf");
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String resultUrl = client.uploadFile(uploadFile.getBytes(), extName);
            String url = IMAGE_SERVER_URL + resultUrl;//上传的图片完整路径
            result.put("error", 0);
            result.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", 0);
            result.put("url", "图片上传出现异常:"+e);
        }
        return JsonUtils.objectToJson(result);
    }
}
