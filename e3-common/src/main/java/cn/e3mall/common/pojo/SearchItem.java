package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * 搜索结果item
 * Created by ZX on 2017/8/1 13:13.
 */
public class SearchItem implements Serializable {
    private static final long serialVersionUID = 8753086963465458318L;
    private long id;
    private String title;
    private String sell_point;
    private String image;
    private long price;
    private String category_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String[] getImages() {
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
