package ObjectModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CartModel {
    private String browserName;
    private String productOneName;
    private String productTwoName;
    private String itemRemovedFromCartMsg;
    private String emptyCartMsg;

    @JsonCreator
    public CartModel(@JsonProperty("browserName") String browserName,
                     @JsonProperty("productOneName") String productOneName,
                     @JsonProperty("productTwoName") String productTwoName,
                     @JsonProperty("itemRemovedFromCartMsg") String itemRemovedFromCartMsg,
                     @JsonProperty("emptyCartMsg") String emptyCartMsg) {
        this.browserName = browserName;
        this.productOneName = productOneName;
        this.productTwoName = productTwoName;
        this.itemRemovedFromCartMsg = itemRemovedFromCartMsg;
        this.emptyCartMsg = emptyCartMsg;
    }
    public String getBrowserName() {
        return browserName;
    }
    public String getProductOneName() {
        return productOneName;
    }
    @XmlElement
    public void setProductOneName(String productOneName) {
        this.productOneName = productOneName;
    }
    public String getProductTwoName() {
        return productTwoName;
    }
    @XmlElement
    public void setProductTwoName(String productTwoName) {
        this.productTwoName = productTwoName;
    }
    public String getItemRemovedFromCartMsg() {
        return itemRemovedFromCartMsg;
    }
    @XmlElement
    public void setItemRemovedFromCartMsg(String itemRemovedFromCartMsg) {
        this.itemRemovedFromCartMsg = itemRemovedFromCartMsg;
    }

    public String getEmptyCartMsg() {
        return emptyCartMsg;
    }
    @XmlElement
    public void setEmptyCartMsg(String emptyCartMsg) {
        this.emptyCartMsg = emptyCartMsg;
    }

    @Override
    public String toString() {
        return "Cart Data{" +
                "productOne: " + productOneName + ", productTwo: " + productTwoName +
                ", itemRemovedFromCartMsg= '" + itemRemovedFromCartMsg + '\'' +
                ", emptyCartMsg= '" + emptyCartMsg + '\'' +
                '}';
    }

}
