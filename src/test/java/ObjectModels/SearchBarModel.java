package ObjectModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchBarModel {
    private String browserName;
    private String searchKeyWord;
    private String firstVisibleElement;
    private String secondVisibleElement;
    private String noMatchSearchError;

    @JsonCreator
    public SearchBarModel(@JsonProperty("browserName") String browserName,
                          @JsonProperty("product") String searchKeyWord, @JsonProperty("firstVisibleElement") String firstVisibleElement,
                          @JsonProperty("secondVisibleElement") String secondVisibleElement,
                          @JsonProperty("noMatchMessage") String noMatchSearchError) {
        this.browserName = browserName;
        this.searchKeyWord = searchKeyWord;
        this.firstVisibleElement = firstVisibleElement;
        this.secondVisibleElement = secondVisibleElement;
        this.noMatchSearchError = noMatchSearchError;
    }
    public String getBrowserName() {
        return browserName;
    }
    public String getSearchKeyWord() {
        return searchKeyWord;
    }
    @XmlElement
    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }
    public String getFirstVisibleElement() {
        return firstVisibleElement;
    }
    @XmlElement
    public void setFirstVisibleElement(String firstVisibleElement) {
        this.firstVisibleElement = firstVisibleElement;
    }
    public String getSecondVisibleElement() {
        return secondVisibleElement;
    }
    @XmlElement
    public void setSecondVisibleElement(String secondVisibleElement) {
        this.secondVisibleElement = secondVisibleElement;
    }

    public String getNoMatchSearchError() {
        return noMatchSearchError;
    }
    @XmlElement
    public void setNoMatchSearchError(String noMatchSearchError) {
        this.noMatchSearchError = noMatchSearchError;
    }

    @Override
    public String toString() {
        return "SerachBar Data{" +
                "searchKeyWord used: " + searchKeyWord +
                "}, firstVisibleElement= '" + firstVisibleElement + '\'' +
                ", secondVisibleElement= '" + secondVisibleElement + '\'' +
                ", noMatchSearchError= '" + noMatchSearchError + '\'' +
                '}';
    }
}
