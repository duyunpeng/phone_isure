package phoneisure.application.picture.command;

/**
 * Created by YJH on 2016/4/12.
 */
public class CreatePictureCommand {

    private String picPath;
    private String miniPicPath;
    private String mediumPicPath;

    private Double size;
    private String name;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getMiniPicPath() {
        return miniPicPath;
    }

    public void setMiniPicPath(String miniPicPath) {
        this.miniPicPath = miniPicPath;
    }

    public String getMediumPicPath() {
        return mediumPicPath;
    }

    public void setMediumPicPath(String mediumPicPath) {
        this.mediumPicPath = mediumPicPath;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
