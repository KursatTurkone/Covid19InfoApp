package sample.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.model.CountryListModel;
import sample.model.RecordModel;
import sample.model.TableModel;


import javax.management.Notification;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Controller {
    @FXML
    private StackedBarChart<?, ?> CaseChart, DeathChart;
    @FXML
    private CategoryAxis x, x2;
    @FXML
    private NumberAxis y, y2;
    @FXML
    private ListView<String> countryList;
    @FXML
    private TextField URLtxt;
    @FXML
    private TableView<TableModel> table;
    @FXML
    private LineChart<?, ?> deathLineChart, CaseLineChart;
    public List<RecordModel> recordList = new ArrayList<>();
    public ObservableList<TableModel> tableDataGeneralStats = FXCollections.observableArrayList();
    public ObservableList<CountryListModel> items = FXCollections.observableArrayList();
    @FXML
    void GetDataBTN(ActionEvent event) {
        CaseChart.getData().clear();
        DeathChart.getData().clear();
        CaseLineChart.getData().clear();
        deathLineChart.getData().clear();
        //eğer bir daha butona basılmış ve veriler çağrılmışsa tabloları önce temizler.
        String urLTxtText = URLtxt.getText();
        Transactions transactions = new Transactions();
        recordList = transactions.okuma(urLTxtText);
      /*  Collections.sort(recordList, new Comparator<RecordModel>() {
            @Override
            public int compare(RecordModel recordModel, RecordModel t1) {
               int result = recordModel.getCountriesAndTerritories().compareTo(t1.getCountriesAndTerritories());
               if(result==0) {
                   result = recordModel.getDateRep().compareTo(t1.getDateRep());
               } else{
                       result=-result;
                   }
                   return result;
               }

        });
       // Bu kısım sort gerektiğinde açılabilir. Datasetin bozulduğu gün için ben yapmıştım fakat veriler eski haline döndüğü için bu işlemi yapmaya

      //  ve programı yavaşlatmaya gerek olmadığını düşünüyorum.
        */
        Runnable r = () -> {
            tableDataGeneralStats.addAll(getData(recordList));
        };

        new Thread(r).start();

        tableDataGeneralStats.addListener((ListChangeListener<TableModel>) change -> {


        });
        table.setItems(tableDataGeneralStats);
        countryList.getItems().addAll(setItems(recordList));
        getDeaths(recordList, "America");
        getDeaths(recordList, "Europe");
        getDeaths(recordList, "Asia");
        getDeaths(recordList, "Oceania");
        getDeaths(recordList, "Africa");
        getCases(recordList, "America");
        getCases(recordList, "Europe");
        getCases(recordList, "Asia");
        getCases(recordList, "Oceania");
        getCases(recordList, "Africa");


    }

    @FXML
    void GetCountryName(ActionEvent event) {
        //Ülke isimlerini butonla alma
        ObservableList<String> selectedItem = countryList.getSelectionModel().getSelectedItems();
        for (int i = 0; i < selectedItem.size(); i++) {
            getDeathLine(recordList, selectedItem.get(i));

            getCasesLine(recordList, selectedItem.get(i));

        }
    }

    @FXML
    void ClearBtn(ActionEvent event) {
        //lineChart temizleme
        CaseLineChart.getData().clear();
        deathLineChart.getData().clear();
    }

    private List<String> setItems(List<RecordModel> recordList) {
        ArrayList countryList = new ArrayList<>();
        Set<RecordModel> set = new LinkedHashSet<>();
        for (RecordModel record : recordList) {
            countryList.add(record.getCountriesAndTerritories());
        }
        set.addAll(countryList);
        countryList.clear();
        countryList.addAll(set);
        return countryList;
    }

    private List<TableModel> getData(List<RecordModel> recordList) {

//çekilen verileri tabloya ekleme işlemleri
        List<TableModel> tableModelList = new ArrayList<>();


        int totalCases = 0, totalDeaths = 0;
        String oldCountry = null;
        RecordModel oldCountryModel = null;
        RecordModel firstCountryModel = null;
        boolean isFirstTime = true;
        boolean isCountryChanged = true;
        boolean isLastCountry=false;
        int Counter= 0;


        for (RecordModel record : recordList) {
           if(recordList.size()-1==Counter) {
               isLastCountry=true;
               //son veriye gelindiğinde else kısmına girmediği için bu yapıyı kurdum.
           }


            if (!isFirstTime) {
                if (record.getCountriesAndTerritories().equals(oldCountry)&&!isLastCountry) {
                    totalCases += record.getCases();
                    totalDeaths += record.getDeaths();

                } else {
                    SimpleStringProperty countryName = new SimpleStringProperty();
                    countryName.setValue(oldCountryModel.getCountriesAndTerritories());

                    SimpleIntegerProperty population = new SimpleIntegerProperty();
                    population.setValue(oldCountryModel.getPopData2018());

                    SimpleIntegerProperty newCases = new SimpleIntegerProperty();
                    newCases.setValue(firstCountryModel.getCases());

                    SimpleIntegerProperty newDeaths = new SimpleIntegerProperty();
                    newDeaths.setValue(firstCountryModel.getDeaths());

                    SimpleIntegerProperty totalCasesSIP = new SimpleIntegerProperty();
                    totalCasesSIP.setValue(totalCases);

                    SimpleIntegerProperty totalDeathsSIP = new SimpleIntegerProperty();
                    totalDeathsSIP.setValue(totalDeaths);

                    SimpleDoubleProperty mortalityRate = new SimpleDoubleProperty();
                    mortalityRate.setValue(calculateMortality(totalDeaths, totalCases));

                    SimpleDoubleProperty attackRate = new SimpleDoubleProperty();
                    attackRate.setValue(calculateAttackRate(totalCases, oldCountryModel.getPopData2018()));


                    tableModelList.add(new TableModel(countryName, population, newCases, newDeaths, totalCasesSIP, totalDeathsSIP, mortalityRate, attackRate));

                    totalCases = record.getCases();
                    totalDeaths = record.getDeaths();
                    isCountryChanged = true;
                }

            }

            if (isCountryChanged) {
                firstCountryModel = record;
                isCountryChanged = false;
            }


            oldCountry = record.getCountriesAndTerritories();

            oldCountryModel = record;

            isFirstTime = false;

            Counter++;
        }


        return tableModelList;


    }

    private double calculateMortality(double totalDeaths, double totalCases) {
        return totalDeaths / totalCases;
    }

    private double calculateAttackRate(double totalCases, double population) {
        if (population == 0)
            population = 1;
        return totalCases / population;
    }

    private void getCasesLine(List<RecordModel> recordList, String Country) {
        int Cases = 0;
        String Date = null;
        XYChart.Series set1 = new XYChart.Series<>();
        set1.setName(Country);
        int Day = 0;
        boolean Daylimit = true;

        for (RecordModel recordModel : recordList) {
            if (recordModel.getCountriesAndTerritories().equals(Country))
                Cases += recordModel.getCases();
        }
        for (RecordModel records : recordList) {
            Date = records.getDateRep();
            if (Daylimit) {
                for (RecordModel record : recordList) {
                    if (record.getDateRep().equals(Date) && record.getCountriesAndTerritories().equals(Country)) {
                        Cases = Cases - record.getCases();
                    }
                }
                set1.getData().add(new XYChart.Data<String, Number>(Date, Cases));
            }
            if (Day == 30) {
                Daylimit = false;
            }
            Day++;
        }
        CaseLineChart.getData().add(set1);
        CaseLineChart.setAnimated(false);
    }

    private void getDeathLine(List<RecordModel> recordList, String Country) {
        int Deaths = 0;
        String Date = null;
        XYChart.Series set1 = new XYChart.Series<>();
        set1.setName(Country);
        int Day = 0;
        boolean Daylimit = true;
        for (RecordModel recordModel : recordList) {
            if (recordModel.getCountriesAndTerritories().equals(Country))
                Deaths += recordModel.getDeaths();
        }
        for (RecordModel records : recordList) {
            if (Daylimit) {
                Date = records.getDateRep();
                for (RecordModel record : recordList) {


                    if (record.getDateRep().equals(Date) && record.getCountriesAndTerritories().equals(Country)) {

                        Deaths = Deaths - record.getDeaths();
                    }
                }
                set1.getData().add(new XYChart.Data<String, Number>(Date, Deaths));
            }
            if (Day == 30) {
                Daylimit = false;
            }
            Day++;
        }
        deathLineChart.getData().add(set1);
        deathLineChart.setAnimated(false);
    }

    private void getDeaths(List<RecordModel> recordList, String Continent) {
        int Deaths = 0;
        String Date = null;
        XYChart.Series set1 = new XYChart.Series<>();
        set1.setName(Continent);
        int Day = 0;
        boolean Daylimit = true;


        for (RecordModel records : recordList) {
            if (Daylimit) {
                Date = records.getDateRep();
                for (RecordModel record : recordList) {


                    if (record.getDateRep().equals(Date) && record.getContinentExp().equals(Continent)) {

                        Deaths += record.getDeaths();
                    }
                }
                set1.getData().add(new XYChart.Data<String, Number>(Date, Deaths));
                Deaths = 0;
            }
            if (Day == 20) {
                Daylimit = false;
            }

            Day++;
        }
        DeathChart.getData().add(set1);
        DeathChart.setAnimated(false);
    }

    private void getCases(List<RecordModel> recordList, String Continent) {
        int Cases = 0;
        String Date = null;
        String Case = null;
        XYChart.Series set2 = new XYChart.Series<>();
        set2.setName(Continent);
        int Day = 0;
        boolean Daylimit = true;


        for (RecordModel records : recordList) {
            if (Daylimit) {
                Date = records.getDateRep();

                for (RecordModel record : recordList) {


                    if (record.getDateRep().equals(Date) && record.getContinentExp().equals(Continent)) {

                        Cases += record.getCases();
                    }
                }
                set2.getData().add(new XYChart.Data<String, Number>(Date, Cases));
                Cases = 0;
            }
            if (Day == 20) {
                Daylimit = false;
            }

            Day++;
        }
        CaseChart.getData().add(set2);
        CaseChart.setAnimated(false);

    }

}






