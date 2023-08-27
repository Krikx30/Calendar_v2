package mainPackage;

import labels.IconLabel;
import labels.LabelClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoadDataClass {

    private Application app;
    private static final String DATASOURCE_PATH = "src/source/calendar_data_source";
    private ArrayList<ArrayList<String>> allMonthsData; // contains all months data

    public LoadDataClass(Application app){
        this.app = app;
        getAllMonthData();
    }
    private void getAllMonthData(){
        File sourceFile = new File(DATASOURCE_PATH);

        try(Scanner sc = new Scanner(sourceFile)){

            allMonthsData = new ArrayList<>();

            while (sc.hasNextLine()){
                String currentLine = sc.nextLine();
                String[] tokens = currentLine.split("-");

                String yearToken, monthToken, dayToken, dayNameToken, quantityToken, dosageToken, commentString;

                if ( tokens.length >= 5){
                    yearToken = tokens[0]; monthToken = tokens[1]; dayToken = tokens[2]; dayNameToken = tokens[3];
                    quantityToken = tokens[4]; dosageToken = tokens[5]; commentString = "";

                    if (tokens.length > 6 ){
                        commentString = tokens[6];

                        // because there are - marks in the comment, which is not a separator, but it's treated as a separator.
                        for (int i = 7; i < tokens.length; i++){
                            commentString += "-"+tokens[i];
                        }
                    }

                    ArrayList<String> tempArrayList = new ArrayList<>();
                    tempArrayList.add(yearToken); tempArrayList.add(monthToken); tempArrayList.add(dayToken);
                    tempArrayList.add(dayNameToken); tempArrayList.add(quantityToken); tempArrayList.add(dosageToken);
                    tempArrayList.add(commentString);

                    allMonthsData.add(tempArrayList);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     *
     * @param year The year, you want to load
     * @param month The month in the year, you want to load
     */
    public void loadMonthData(String year, String month){

        // set the missing ot unset data to "-".
        for (int i = 0; i < app.monthDaysLabels.size(); i++){
            LabelClass currentLabel = app.monthDaysLabels.get(i);

            Matcher mc1, mc2;
            mc1 = IDStorage.MONTH_DAY_QUANTITY_ID_PATTERN.matcher(currentLabel.getID());
            mc2 = IDStorage.MONTH_DAY_DOSAGE_ID_PATTERN.matcher(currentLabel.getID());

            if(mc1.find() || mc2.find()){
                currentLabel.setText("-");
            }
        }

        month = month.toLowerCase(); // because the data source contains lower-case month names

        for (int i = 0; i < allMonthsData.size(); i++){

            ArrayList<String> currentList = allMonthsData.get(i);

            String yearToken, monthToken, dayToken, dayNameToken, quantityToken, dosageToken, commentToken;

            yearToken = currentList.get(0); monthToken = currentList.get(1); dayToken = currentList.get(2);
            dayNameToken = currentList.get(3); quantityToken = currentList.get(4); dosageToken = currentList.get(5);
            commentToken = currentList.get(6);

            LabelClass dosageLabelToSet = app.getLabel(IDStorage.MONTH_DAY_DOSAGE_ID_SCHEME+dayToken);
            LabelClass quantityLabelToSet = app.getLabel(IDStorage.MONTH_DAY_QUANTITY_ID_SCHEME+dayToken);
            IconLabel dayIconCommentToSet = app.getIconLabel(IDStorage.MONTH_DAY_COMMENT_ICON_ID_SCHEME+dayToken);

            if(year.equals(yearToken) && month.equals(monthToken)){
                dosageLabelToSet.setText(dosageToken+" mg");
                quantityLabelToSet.setText(quantityToken);
                String commentToAdd = "Ez egy "+dayNameToken+"i nap.\n\n"+commentToken;
                dayIconCommentToSet.addComment(commentToAdd);
            }
        }
    }

}
