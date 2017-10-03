package net.dirox.auluxa.data.sample;

/**
 * Created by MyPC on 21/07/2017.
 */

public class PinItem {
    private String txtFirst;
    private String txtSecond;
    private int img;
    private int imgView;
    private String txtheader;

    public String getTxtFirst() {
        return txtFirst;
    }

    public void setTxtFirst(String txtFirst) {
        this.txtFirst = txtFirst;
    }

    public String getTxtSecond() {
        return txtSecond;
    }

    public void setTxtSecond(String txtSecond) {
        this.txtSecond = txtSecond;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
    public int getImgView(){
        return imgView;
    }
    public void setImgView(int imgView){
        this.imgView = imgView;
    }
    public String getTxtheader(){
        return txtheader;
    }

    public void setTxtheader(String txtheader) {
        this.txtheader = txtheader;
    }

    public PinItem(String txtFirst, String txtSecond, int img , int imgView) {
        this.txtFirst = txtFirst;
        this.txtSecond = txtSecond;
        this.img = img;
        this.imgView = imgView;
//        this.txtheader = txtheader;
    }
}
