package net.dirox.auluxa.data.sample;

/**
 * Created by MyPC on 18/07/2017.
 */

public class ActivityLogItems {
    private int image;
    private String txtheader;
    private String txtContent;
    private int imageView;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTxtheader() {
        return txtheader;
    }

    public void setTxtheader(String txtheader) {
        this.txtheader = txtheader;
    }

    public String getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public ActivityLogItems(int image, String txtheader, String txtContent , int imageView) {
        this.image = image;
        this.txtheader = txtheader;
        this.txtContent = txtContent;
        this.imageView = imageView;
    }

}
