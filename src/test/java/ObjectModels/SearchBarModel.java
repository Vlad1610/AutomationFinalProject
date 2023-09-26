package ObjectModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchBarModel {
    private String browserName;
    private String searchKeyWord;
    private String searchVisibleElement;
    private String noMatchSearchError;

    @JsonCreator
    public SearchBarModel(@JsonProperty("browserName") String browserName,
                          @JsonProperty("product") String searchKeyWord, @JsonProperty("searchVisibleElement") String firstVisibleElement,
                          @JsonProperty("noMatchMessage") String noMatchSearchError) {
        this.browserName = browserName;
        this.searchKeyWord = searchKeyWord;
        this.searchVisibleElement = firstVisibleElement;
        this.noMatchSearchError = noMatchSearchError;
    }
    public String getBrowserName() {
        return browserName;
    }
    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public String getSearchVisibleElement() {
        return searchVisibleElement;
    }

    public String getNoMatchSearchError() {
        return noMatchSearchError;
    }

    @Override
    public String toString() {
        return "SerachBar Data{" +
                "searchKeyWord used: " + searchKeyWord +
                "}, firstVisibleElement= '" + searchVisibleElement + '\'' +
                ", noMatchSearchError= '" + noMatchSearchError + '\'' +
                '}';
    }
}
