package cn.jin.web.controller.test.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shujin.ding
 * @version 1.0
 * @Type MovieResourcePO
 * @Desc 电影资源pojo
 * @Date 2017-09-18 9:57
 */
@Data
public class MovieResourcePO {

    /**
     * url所在的行
     */
    private String url;

    /**
     * url对应的标题
     */
    private String title;

    /**
     * played
     */
    private String played = "*played*0";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ResponseBody
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovieResourcePO that = (MovieResourcePO) o;
        if(that.getUrl().equals(getUrl())){
            return true;
        }
        return false;
    }

    @Override public int hashCode() {
        return url.hashCode();
    }
}
