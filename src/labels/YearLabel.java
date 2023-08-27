package labels;

import eventHandlers.EventHandlerClass;
import mainPackage.Application;
import interfaces.*;

import javax.swing.*;
import java.util.ArrayList;

public class YearLabel extends LabelClass implements canReactMouseWheel{

    public YearLabel(Application app, JPanel parent_to_add, String ID, String styleID, String text, ArrayList<LabelClass> allLabel){
        super(app, parent_to_add, ID, styleID, text, allLabel);
        addMouseWheelListener(new EventHandlerClass(app));
    }

    @Override
    public void mouseWheelUpReact() {
        int currentValue = Integer.parseInt(this.getText());

        if(currentValue < app.maxYear){
            this.setText(String.valueOf(++currentValue));
        }
    }

    @Override
    public void mouseWheelDownReact() {
        int currentValue = Integer.parseInt(this.getText());
        this.setText(String.valueOf(--currentValue));
    }
}
