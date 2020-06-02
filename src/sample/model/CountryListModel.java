package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class CountryListModel {
    private final SimpleStringProperty countryList;

    public CountryListModel(SimpleStringProperty country) {
        countryList = country;
    }

    public String getCountryList() {
        return countryList.get();
    }

    public SimpleStringProperty countryListProperty() {
        return countryList;
    }

    public void setCountryList(String countryList) {
        this.countryList.set(countryList);
    }
}
