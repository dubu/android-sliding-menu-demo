package grimbo.android.demo.slidingmenu;

/**
 * User: Administrator
 * Date: 13. 2. 4
 * Time: 오전 9:40
 */
public class ListItemData {
    private  String titleText;
    private  String discriptionText;
    private  int imageResourceId;

    public  void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public  void setDiscriptionText(String discriptionText) {
        this.discriptionText = discriptionText;
    }

    public  void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }


    public  String getTitleText() {
        return titleText;
    }

    public  String getDiscriptionText() {
        return discriptionText;
    }

    public  int getImageResourceId() {
        return imageResourceId;
    }
}
