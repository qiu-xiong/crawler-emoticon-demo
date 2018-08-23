/**
 * @Author: xiong.qiu
 * @Date: 2018/8/23 13:36
 * @Description: 表情图爬虫启动类
 */
public class CrawlerEmoticonPackageStart {

    public static void main(String[] args) {
        EmoticonPackagePageProcessor emoticonPackagePageProcessor = new EmoticonPackagePageProcessor();
        emoticonPackagePageProcessor.start();
    }
}
