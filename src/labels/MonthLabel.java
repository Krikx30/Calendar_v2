package labels;

import mainPackage.Application;

import javax.swing.*;
import java.util.ArrayList;

public class MonthLabel extends LabelClass{
    public MonthLabel(Application app, JPanel parent_to_add, String ID, String styleID, String text, ArrayList<LabelClass> allLabel){
        super(app, parent_to_add, ID, styleID, text, allLabel);
    }
}
