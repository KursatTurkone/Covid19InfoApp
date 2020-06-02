package sample.model;

public class RecordModel {

    private String dateRep;
    private int day;
    private int year;
    private int month;
    private int cases;
    private int deaths;
    private String countriesAndTerritories;
    private String geoId;
    private String countryTerritoryCode;
    private int popData2018;
    private String continentExp;


    public String getCountryTerritoryCode() {
        return countryTerritoryCode;
    }

    public void setCountryTerritoryCode(String countryTerritoryCode) {
        this.countryTerritoryCode = countryTerritoryCode;
    }



    public int getMonth() {

        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDateRep() {
        return dateRep;
    }

    public void setDateRep(String dateRep) {
        this.dateRep = dateRep;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getCountriesAndTerritories() {
        return countriesAndTerritories;
    }

    public void setCountriesAndTerritories(String countriesAndTerritories) {
        this.countriesAndTerritories = countriesAndTerritories;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }


    public int getPopData2018() {
        return popData2018;
    }

    public void setPopData2018(int popData2018) {
        this.popData2018 = popData2018;
    }

    public String getContinentExp() {
        return continentExp;
    }

    public void setContinentExp(String continentExp) {
        this.continentExp = continentExp;
    }


}
