package emoticonpackage;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;

/**
 * @Author: xiong.qiu
 * @Date: 2018/8/23 13:40
 * @Description: 表情包主要爬取核心逻辑
 * PageProcessor的定制分为三个部分，分别是爬虫的配置、页面元素的抽取和链接的发现。
 */
public class EmoticonPackagePageProcessor implements PageProcessor {

    private static final String CRAWLER_URL = "https://www.doutula.com/article/detail/9474535";

    /**
     * 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    /**
     * @param page
     * @Description: 定制爬虫url逻辑部分（爬取内容） process是定制爬虫逻辑的核心接口
     * @Author: Xiong.Qiu
     * @Date: 2018/8/22 11:17
     * @return: void
     */
    @Override
    public void process(Page page) {
        EmoticonPackageModel model = new EmoticonPackageModel();
        model.setCrawlerTime(new Date());
        model.setCrawlerUrl(CRAWLER_URL);
        model.setImgTitle(page.getHtml().xpath("h1/a/text()").toString());
        model.setImgUrls(page.getHtml().xpath("table//img/@src").all());
        page.putField("model",model);
    }

    @Override
    public Site getSite() {
        return site;
    }


    /**
     * @param
     * @Description: 程序启动
     * @Author: Xiong.Qiu
     * @Date: 2018/8/22 11:12
     * @return: void
     */
    public void start() {
        Spider.create(new EmoticonPackagePageProcessor())
                //爬取url
                .addUrl(CRAWLER_URL)
                //启动线程数
                .thread(5)
                //存放路径
                .addPipeline(new FilePipeLine())
                //真正启动方法
                .run();
    }
}
