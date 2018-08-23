import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiong.qiu
 * @Date: 2018/8/23 13:42
 * @Description: 表情包爬取内容
 */
public class EmoticonPackageModel implements Serializable {


    /**
     * 图片标题
     */
    private String imgTitle;
    /**
     * 爬取图片url字符串集合
     */
    private List<String> imgUrls;
    /**
     * 爬取网页
     */
    private String crawlerUrl;
    /**
     * 爬取时间
     */
    private Date crawlerTime;


    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getCrawlerUrl() {
        return crawlerUrl;
    }

    public void setCrawlerUrl(String crawlerUrl) {
        this.crawlerUrl = crawlerUrl;
    }

    public Date getCrawlerTime() {
        return crawlerTime;
    }

    public void setCrawlerTime(Date crawlerTime) {
        this.crawlerTime = crawlerTime;
    }

    @Override
    public String toString() {
        return "EmoticonPackageModel{" +
                "imgTitle='" + imgTitle + '\'' +
                ", imgUrls=" + imgUrls +
                ", crawlerUrl='" + crawlerUrl + '\'' +
                ", crawlerTime=" + crawlerTime +
                '}';
    }
}
