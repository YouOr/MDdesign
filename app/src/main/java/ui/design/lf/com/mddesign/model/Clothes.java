package ui.design.lf.com.mddesign.model;

/**
 * Created by Administrator on 2017/8/1.
 */
public class Clothes {
    private String name;
    private int imageid;

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public Clothes(String name, int imageid) {
        this.name = name;
        this.imageid = imageid;
    }


}
