package mainPackage;


import java.util.regex.Pattern;
public class IDStorage {

    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////LABELS///////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static final String YEAR_ID = "YearLabel";
    public static final String YEAR_STYLE_ID = "yearLabel";
    public static final Pattern YEAR_ID_PATTERN = Pattern.compile(YEAR_ID);

    public static final String MONTHS_ID_SCHEME = "month_";
    public static final String MONTHS_STYLE_ID = "months";
    public static final Pattern MONTHS_ID_PATTERN = Pattern.compile(MONTHS_ID_SCHEME+"[1-9]{1,2}");

    public static final String MONTH_DAY_NUMBER_ID_SCHEME = "dayNumber_";
    public static final String MONTH_DAY_NUMBER_STYLE_ID = "monthDays_dayNumber";
    public static final Pattern MONTH_DAY_DAYNUMBER_ID_PATTERN = Pattern.compile(MONTH_DAY_NUMBER_ID_SCHEME+"[1-9]{1,2}");

    public static final String MONTH_DAY_COMMENT_ICON_ID_SCHEME = "dayIcon_";
    public static final String MONTH_DAY_COMMENT_ICON_STYLE_ID = "monthDays_commentIcon";
    public static final Pattern MONTH_DAY_COMMENT_ICON_ID_PATTERN = Pattern.compile(MONTH_DAY_COMMENT_ICON_ID_SCHEME+"[1-9]{1,2}");

    public static final String MONTH_DAY_QUANTITY_ID_SCHEME = "dayQuantity_";
    public static final String MONTH_DAY_QUANTITY_STYLE_ID = "monthDays_quantity";
    public static final Pattern MONTH_DAY_QUANTITY_ID_PATTERN = Pattern.compile(MONTH_DAY_QUANTITY_ID_SCHEME+"[1-9]{1,2}");

    public static final String MONTH_DAY_DOSAGE_ID_SCHEME = "dayDosage_";
    public static final String MONTH_DAY_DOSAGE_STYLE_ID = "monthDays_dosage";
    public static final Pattern MONTH_DAY_DOSAGE_ID_PATTERN = Pattern.compile(MONTH_DAY_DOSAGE_ID_SCHEME+"[1-9]{1,2}");
}
