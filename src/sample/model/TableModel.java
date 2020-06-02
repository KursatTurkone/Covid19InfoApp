package sample.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableModel {

    private final SimpleStringProperty Country;
    private final SimpleIntegerProperty Population;
    private final SimpleIntegerProperty NewCases;
    private final SimpleIntegerProperty NewDeaths;
    private final SimpleIntegerProperty TotalCases;
    private final SimpleIntegerProperty TotalDeaths;
    private final SimpleDoubleProperty MortalityRate;
    private final SimpleDoubleProperty AttackRate;

    public TableModel(SimpleStringProperty country, SimpleIntegerProperty population, SimpleIntegerProperty newCases, SimpleIntegerProperty newDeaths, SimpleIntegerProperty totalCases, SimpleIntegerProperty totalDeaths, SimpleDoubleProperty mortalityRate, SimpleDoubleProperty attackRate) {
        Country = country;
        Population = population;
        NewCases = newCases;
        NewDeaths = newDeaths;
        TotalCases = totalCases;
        TotalDeaths = totalDeaths;
        MortalityRate = mortalityRate;
        AttackRate = attackRate;
    }

    public String getCountry() {
        return Country.get();
    }

    public SimpleStringProperty countryProperty() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country.set(country);
    }

    public int getPopulation() {
        return Population.get();
    }

    public SimpleIntegerProperty populationProperty() {
        return Population;
    }

    public void setPopulation(int population) {
        this.Population.set(population);
    }

    public int getNewCases() {
        return NewCases.get();
    }

    public SimpleIntegerProperty newCasesProperty() {
        return NewCases;
    }

    public void setNewCases(int newCases) {
        this.NewCases.set(newCases);
    }

    public int getNewDeaths() {
        return NewDeaths.get();
    }

    public SimpleIntegerProperty newDeathsProperty() {
        return NewDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.NewDeaths.set(newDeaths);
    }

    public int getTotalCases() {
        return TotalCases.get();
    }

    public SimpleIntegerProperty totalCasesProperty() {
        return TotalCases;
    }

    public void setTotalCases(int totalCases) {
        this.TotalCases.set(totalCases);
    }

    public int getTotalDeaths() {
        return TotalDeaths.get();
    }

    public SimpleIntegerProperty totalDeathsProperty() {
        return TotalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.TotalDeaths.set(totalDeaths);
    }

    public double getMortalityRate() {
        return MortalityRate.get();
    }

    public SimpleDoubleProperty mortalityRateProperty() {
        return MortalityRate;
    }

    public void setMortalityRate(double mortalityRate) {
        this.MortalityRate.set(mortalityRate);
    }

    public double getAttackRate() {
        return AttackRate.get();
    }

    public SimpleDoubleProperty attackRateProperty() {
        return AttackRate;
    }

    public void setAttackRate(double attackRate) {
        this.AttackRate.set(attackRate);
    }
}
