package labels;

import eventHandlers.EventHandlerClass;
import interfaces.*;
import mainPackage.Application;

import javax.swing.*;
import java.util.ArrayList;

public class DosageLabel extends LabelClass implements canReactMouseWheel, canReactMouseClick {

    private String currentValue;
    private final String defaultFollowUpValue = " mg";
    private final String upChangeFollowUpValue = " +";
    private final String downChangeFollowUpValue = " -";
    private String currentFollowUpValue;
    public DosageLabel(Application app, JPanel parent_to_add, String ID, String styleID, String text, ArrayList<LabelClass> allLabel){
        super(app, parent_to_add, ID, styleID, text, allLabel);
        addListeners();
    }
    private String getCurrentValue(){
        return getText().split(" ")[0];
    }
    private String getCurrentFollowUpValue(){
        String returnValue = "";
        if(getText().split(" ").length > 1){
            returnValue = getText().split(" ")[1];
        }

        return returnValue;
    }
    private void addListeners(){
        this.addMouseWheelListener(new EventHandlerClass(app));
        this.addMouseListener(new EventHandlerClass(app));
    }
    @Override
    public void mouseWheelUpReact() {

        currentValue = getCurrentValue();
        currentFollowUpValue = getCurrentFollowUpValue();

        if(currentValue.equals(UNSET_VALUE)){
            currentValue = VALUE_NOT_KNOWN;
        }
        else if (currentValue.equals(VALUE_NOT_KNOWN)){
            currentValue = "0" + defaultFollowUpValue;
        }
        else{
            int intCurrentValue = Integer.parseInt(currentValue);
            intCurrentValue++;
            currentValue = intCurrentValue + defaultFollowUpValue;
        }
        
        setText(currentValue);
    }

    @Override
    public void mouseWheelDownReact() {

        currentValue = getCurrentValue();
        currentFollowUpValue = getCurrentFollowUpValue();

        if(!(currentValue.equals(UNSET_VALUE) || currentValue.equals(VALUE_NOT_KNOWN))){ // if neither

            int intCurrentValue = Integer.parseInt(currentValue);

            if (intCurrentValue > 0){

                intCurrentValue--;
                currentValue = intCurrentValue + defaultFollowUpValue;

            }else if (intCurrentValue == 0){
                currentValue = VALUE_NOT_KNOWN;
            }

        }else{
            if(currentValue.equals(VALUE_NOT_KNOWN))
                currentValue = UNSET_VALUE;
        }

        setText(currentValue);
    }

    @Override
    public void mouseClickReact() {

    }
}
