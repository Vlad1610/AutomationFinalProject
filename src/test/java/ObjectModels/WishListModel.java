package ObjectModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WishListModel {
    private String browserName;
    private String firstFavItem;
    private String secondFavItem;
    private String itemRemovedMessage;
    private String emptyCartMessage;


    @JsonCreator
    public WishListModel(@JsonProperty("browserName") String browserName,
                         @JsonProperty("firstFavItemName") String firstFavItem,
                         @JsonProperty("secondFavItemName") String secondFavItem,
                         @JsonProperty("itemRemovedMessage") String itemRemovedMessage,
                         @JsonProperty("emptyCartMessage")String emptyCartMessage) {
        this.browserName = browserName;
        this.firstFavItem = firstFavItem;
        this.secondFavItem = secondFavItem;
        this.itemRemovedMessage = itemRemovedMessage;
        this.emptyCartMessage = emptyCartMessage;
    }

    public String getBrowserName() {
        return browserName;
    }
    public String getFirstFavItem() {
        return firstFavItem;
    }
    @XmlElement
    public void setFirstFavItem(String firstFavItem) {
        this.firstFavItem = firstFavItem;
    }
    public String getSecondFavItem() {
        return secondFavItem;
    }
    @XmlElement
    public void setSecondFavItem(String secondFavItem) {
        this.secondFavItem = secondFavItem;
    }
    public String getItemRemovedMessage() {
        return itemRemovedMessage;
    }
    @XmlElement
    public void setItemRemovedMessage(String itemRemovedMessage) {
        this.itemRemovedMessage = itemRemovedMessage;
    }
    public String getEmptyCartMessage() {
        return emptyCartMessage;
    }
    @XmlElement
    public void setEmptyCartMessage(String emptyCartMessage) {
        this.emptyCartMessage = emptyCartMessage;
    }
    @Override
    public String toString() {
        return "WishList Data{" +
                "firstFavItem: " + firstFavItem + ", secondFavItem: " + secondFavItem +
                "}, itemRemovedMessage= '" + itemRemovedMessage + '\'' +
                ", emptyCartMessage= '" + emptyCartMessage + '\'' +
                '}';
    }

}
