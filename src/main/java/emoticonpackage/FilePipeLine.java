package emoticonpackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import util.FileUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @Author: xiong.qiu
 * @Date: 2018/8/23 13:44
 * @Description: 爬虫内容存储的处理类
 */
public class FilePipeLine implements Pipeline {


    private static final Logger logger = LoggerFactory.getLogger(FilePipeLine.class);
    /**
     * 文件存放根路径
     */
    private String path;
    /**
     * 文件默认存放根路径
     */
    private static final String DEFAULT_PATH = "D:" + File.separator + "CrawlerData";

    public void setPath(String path) {
        this.path = path;
    }

    public FilePipeLine() {
        this.path = DEFAULT_PATH;
    }

    public FilePipeLine(String path) {
        this.path = path;
    }

    /**
     * // ResultItems保存了抽取结果，它是一个Map结构，
     * // 在page.putField(key,value)中保存的数据，可以通过ResultItems.get(key)获取
     *
     * @param resultItems
     * @param task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        Object object = resultItems.get("model");
        if (object instanceof EmoticonPackageModel) {
            EmoticonPackageModel emoticonPackageModel = (EmoticonPackageModel) object;
            String imgTitle = emoticonPackageModel.getImgTitle();
            List<String> imgUrls = emoticonPackageModel.getImgUrls();
            for (String string : imgUrls) {
                if (!"".equals(string)) {
                    //将图片资源保存到本地
                    String filePath = this.path + File.separator + imgTitle + File.separator;
                    String fileName = string.substring(string.lastIndexOf("/") + 1);
                    try {
                        URL url = new URL(string);
                        FileUtil.urlCreateFileImg(filePath, fileName, url);
                        logger.debug(fileName + "生成OK>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    } catch (MalformedURLException e) {
                        logger.error("生成网络资源错误", e);
                    } catch (Exception e) {
                        logger.error("生成网络资源错误", e);
                    }
                }
            }
        }
    }

}
