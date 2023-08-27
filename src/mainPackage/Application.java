package mainPackage;

import containers.*;
import eventHandlers.EventHandlerClass;
import labels.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Application{

    public MainFrame mainFrame;
    public MainContainer mainContainer;
    public TopContainer topContainer;
    public MiddleContainer middleContainer;
    public TopLeftContainer topLeftContainer;
    public TopMiddleContainer topMiddleContainer;
    public TopRightContainer topRightContainer;
    public KeywordsContainer keywordsBarContainer;

    private LoadDataClass loadDataClass;
    private EventHandlerClass eventHandler;

    public ArrayList<DaysClass> allDaysContainer = new ArrayList<>(); // contains all day's container from the middleContainer
    public ArrayList<LabelClass> allLabel = new ArrayList<>(); // contains all text label
    public ArrayList<IconLabel> allIconLabel = new ArrayList<>(); // contains all icon label
    public ArrayList<LabelClass> monthDaysLabels = new ArrayList<>(); // contains all month related labels

    public String selectedYear; // the currently shown (selected) year
    public String selectedMonth;
    public static final int MIN_YEAR = 2015; // Because there is no data before this year.
    public int maxYear;


    Application(){

        createMainFrame();
        createContainers();
        createLabels();
        loadData();

        mainFrame.revalidate();
        mainFrame.pack();
        mainFrame.repaint();
    }
    private void createMainFrame(){
        mainFrame = new MainFrame();
    }
    private void createContainers(){

        mainContainer = new MainContainer(mainFrame, "mainContainer", mainFrame.getWidth(), mainFrame.getHeight());

        topContainer = new TopContainer(mainContainer, "topContainer", mainContainer.getWidth(), 100);

        keywordsBarContainer = new KeywordsContainer(mainContainer,"keywordContainer",mainContainer.getWidth(),30);

        middleContainer = new MiddleContainer(mainContainer, "middleContainer", mainContainer.getWidth(),
                                        mainContainer.getHeight() - topContainer.getHeight() - keywordsBarContainer.getHeight());

        topLeftContainer = new TopLeftContainer(topContainer, "topLeftContainer", topContainer.getWidth() / 3, topContainer.getHeight());

        topMiddleContainer = new TopMiddleContainer(topContainer, "topMiddleContainer", topContainer.getWidth() / 3, topContainer.getHeight());

        topRightContainer = new TopRightContainer(topContainer, "topRightContainer", topContainer.getWidth() / 3, topContainer.getHeight());


        topContainer.add(topLeftContainer, BorderLayout.WEST);
        topContainer.add(topMiddleContainer, BorderLayout.CENTER);
        topContainer.add(topRightContainer, BorderLayout.EAST);

        mainContainer.add(topContainer, BorderLayout.NORTH);
        mainContainer.add(keywordsBarContainer, BorderLayout.CENTER);
        mainContainer.add(middleContainer, BorderLayout.SOUTH);

    }
    private void createLabels(){
        createYearLabel();
        createMonthsLabels();
        createMonthDaysLabels();
        createKeywordLabels();
    }
    private void createYearLabel(){

        String yearText = getCurrentDate()[0];
        selectedYear = yearText; // set the year for the current year, when the application starts
        new YearLabel(this, topMiddleContainer,IDStorage.YEAR_ID,IDStorage.YEAR_STYLE_ID,yearText, allLabel);

    }
    private void createMonthsLabels(){
        String[] months = {"Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus",
                            "Szeptember", "Október", "November", "December"};

        Integer currentMonth = Integer.parseInt(getCurrentDate()[1]);

        for (int i = 0; i < months.length; i++){

            String labelID = IDStorage.MONTHS_ID_SCHEME+(i+1);
            String labelText = months[i];
            MonthLabel lc = new MonthLabel(this, topRightContainer,labelID,IDStorage.MONTHS_STYLE_ID,labelText, allLabel);
            addCustomMouseListener(lc);

            // if the month is the current month, change font type, indicate that it is selected.
            if(currentMonth.equals(i+1))
                selectMonth(labelID);

        }
    }
    private void createMonthDaysLabels(){

        int maxDaysInAMonth = 31;
        for (int i = 0; i < maxDaysInAMonth; i++) {

            // Create day container

            String dayContainerID = "day_"+(i+1);
            String dayContainerStyleID = "daysClassContainer";
            DaysClass day = new DaysClass(middleContainer,dayContainerID,dayContainerStyleID);
            day.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.decode("#807D75")));

            // 0 -> day number label, 1 -> comment icon label
            // 2 -> quantity label,   3 -> dosage label
            int from = 0;
            int to = 4;

            for (int j = from; j < to; j++){

                String labelID;
                String labelStyle;
                String labelText;
                Dimension prefSize;

                switch (j){

                    case 0 :
                        labelText = String.valueOf((i+1));
                        labelID = IDStorage.MONTH_DAY_NUMBER_ID_SCHEME+(i+1);
                        labelStyle = IDStorage.MONTH_DAY_NUMBER_STYLE_ID;
                        monthDaysLabels.add(new LabelClass(this, day,labelID,labelStyle,labelText, allLabel));
                        break;

                    case 1 :
                        labelID = IDStorage.MONTH_DAY_COMMENT_ICON_ID_SCHEME+(i+1);
                        labelStyle = IDStorage.MONTH_DAY_COMMENT_ICON_STYLE_ID;
                        prefSize = new Dimension(day.getPreferredSize().width / 6,
                                                day.getPreferredSize().height / 6);
                        new IconLabel(day, labelID,labelStyle,prefSize,allIconLabel);
                        break;

                    case 2 :
                        labelText = "-";
                        labelID = IDStorage.MONTH_DAY_QUANTITY_ID_SCHEME+(i+1);
                        labelStyle = IDStorage.MONTH_DAY_QUANTITY_STYLE_ID;
                        monthDaysLabels.add(new QuantityLabel(this, day,labelID,labelStyle,labelText, allLabel));
                        break;

                    case 3 :
                        labelText = "-";
                        labelID = IDStorage.MONTH_DAY_DOSAGE_ID_SCHEME+(i+1);
                        labelStyle = IDStorage.MONTH_DAY_DOSAGE_STYLE_ID;
                        monthDaysLabels.add(new DosageLabel(this, day,labelID,labelStyle,labelText, allLabel));
                        break;
                }
            }

            allDaysContainer.add(day);
        }
    }
    private void createKeywordLabels(){
        try{
            String keywordsFilePath = "src/source/keyword_list";
            File keywordsFile = new File(keywordsFilePath);
            Scanner sc = new Scanner(keywordsFile);

            while (sc.hasNextLine()){
                String word = sc.nextLine();
                word = word.strip();
                LabelClass lc = new LabelClass(this, this.keywordsBarContainer, word, "keywords", word, allLabel);

                // add empty border, to make a 20 px space between the texts
                lc.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));

                allLabel.add(lc);
            }

            sc.close();
        }catch (IOException e){

        }

    }
    private void loadData(){
        loadDataClass = new LoadDataClass(this);
        loadCurrentMonthData();
        setMaxYear();
    }
    private void setMaxYear(){
        maxYear = Calendar.getInstance().get(Calendar.YEAR); // set the maximum year
    }
    public void loadCurrentMonthData(){
        loadDataClass.loadMonthData(selectedYear,selectedMonth);
    }

    /**
     *
     * @param labelID -> The ID of the label, you want to get
     * @return Returns the label object, or "null", if there is no match
     */
    public LabelClass getLabel(String labelID){

        // from the allLabel array
        for(LabelClass lc : allLabel){
            if(lc.getID().equals(labelID)){
                return lc;
            }
        }
        return null;
    }

    /**
     *
     * @param iconLabelID -> The ID of the iconLabel you want to get
     * @return Return the iconLabel object, or "null", if there is no match
     */
    public IconLabel getIconLabel(String iconLabelID){
        for(IconLabel il : allIconLabel){
            if(il.getID().equals(iconLabelID)){
                return il;
            }
        }
        return null;
    }

    /**
     *
     * @return {currentYear, currentMont} as String, like: 2000, 1
     */
    public String[] getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        String yearText = String.valueOf(calendar.get(Calendar.YEAR));
        String monthText = String.valueOf(calendar.get(Calendar.MONTH)+1); // because its start fom 0

        return new String[]{yearText, monthText};
    }

    /**
     * Select the month label by ID
     * @param monthID -> The month label ID
     */
    public void selectMonth(String monthID){
        for (LabelClass lc : allLabel){
            if (lc.getID().equals(monthID) && !lc.getText().equals(selectedMonth)){

                LabelClass previouslySelectedMonth = null;
                for(LabelClass lcp : allLabel){
                    if (lcp.getText().equals(selectedMonth)){
                        previouslySelectedMonth = lcp;
                    }
                }

                // Because all the months use the same style, so we can get the current month's style
                Font originalFont = lc.getFont();
                Color originalColor = lc.getForeground();

                lc.setFont(new Font("Helvetica", Font.BOLD,lc.getFont().getSize()));
                lc.setBorder(BorderFactory.createLineBorder(Color.red,1));
                lc.setForeground(Color.RED);

                setSelectedMonth(lc.getText()); // set the selected month to current month

                if(previouslySelectedMonth != null){
                    previouslySelectedMonth.setFont(originalFont);
                    previouslySelectedMonth.setForeground(originalColor);
                    previouslySelectedMonth.setBorder(null);
                }
            }
        }
    }
    private void setSelectedMonth(String month){
        selectedMonth = month;
    }
    public void addCustomMouseListener(Component add_to){
        eventHandler = new EventHandlerClass(this);
        add_to.addMouseListener(eventHandler);
    }
}
