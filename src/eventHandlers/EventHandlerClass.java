package eventHandlers;

import labels.DosageLabel;
import labels.LabelClass;
import labels.QuantityLabel;
import labels.YearLabel;
import mainPackage.Application;
import mainPackage.IDStorage;

import java.awt.event.*;
import java.util.regex.Matcher;

public class EventHandlerClass implements MouseListener, KeyListener, MouseWheelListener {

    private Application app;

    public EventHandlerClass(Application app){
        this.app = app;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Matcher mc; // for ID regex check
        String sourceClassName = this.getMouseEventSourceClass(e);
        Object sourceClass = e.getSource();

        if(sourceClassName.equals("MonthLabel")){ // the class
            LabelClass currentLabel = ((LabelClass) sourceClass);

            String labelID = currentLabel.getID();
            String labelText = currentLabel.getText();

            mc = IDStorage.MONTHS_ID_PATTERN.matcher(labelID);

            if(mc.find()){
                app.selectMonth(labelID);
                app.loadCurrentMonthData();
            }
        }

        if(sourceClassName.equals("QuantityLabel") || sourceClassName.equals("DosageLabel")){
            Matcher mc1, mc2;
            LabelClass currentLabel = (LabelClass) sourceClass;

            ////////////////If it's a quantity or a dosage label///////////////////
            mc1 = IDStorage.MONTH_DAY_QUANTITY_ID_PATTERN.matcher(currentLabel.getID());
            mc2 = IDStorage.MONTH_DAY_DOSAGE_ID_PATTERN.matcher(currentLabel.getID());

            if(mc1.find()){
                QuantityLabel ql = (QuantityLabel) currentLabel;
                ql.mouseClickReact();

            }else if(mc2.find()){
                DosageLabel dl = (DosageLabel) currentLabel;
                dl.mouseClickReact();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private String getMouseEventSourceClass(MouseEvent e){
        return e.getSource().getClass().getName().split("\\.")[1];
    }
    private String getKeyboardEventSourceClass(KeyEvent e){
        return null;
    }
    private String getMouseScrollEventSourceClass(MouseWheelEvent e){
        return e.getSource().getClass().getName().split("\\.")[1];
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        String sourceClassName = this.getMouseScrollEventSourceClass(e);
        Object sourceClass = e.getSource();
        int wheelRotation = e.getWheelRotation(); // -1 -> UP, 1 -> DOWN
        int upRotation = -1;
        int downRotation = 1;

        //////////////////////////////YEAR LABEL///////////////////////////////

        if(sourceClassName.equals("YearLabel")){
            YearLabel currentLabel = ((YearLabel) sourceClass);

            if(wheelRotation == upRotation){ //UP
                currentLabel.mouseWheelUpReact();

            }else{ // DOWN
                currentLabel.mouseWheelDownReact();
            }

            app.selectedYear = currentLabel.getText();
            app.loadCurrentMonthData();
        }


        ///////////////////////Month days quantity and dosage//////////////////////

        if(sourceClassName.equals("QuantityLabel") || sourceClassName.equals("DosageLabel")){
            Matcher mc1, mc2;
            LabelClass currentLabel = (LabelClass) sourceClass;

            ////////////////If it's a quantity or a dosage label///////////////////
            mc1 = IDStorage.MONTH_DAY_QUANTITY_ID_PATTERN.matcher(currentLabel.getID());
            mc2 = IDStorage.MONTH_DAY_DOSAGE_ID_PATTERN.matcher(currentLabel.getID());

            if(mc1.find()){
                QuantityLabel ql = (QuantityLabel) currentLabel;
                if(wheelRotation == upRotation)
                    ql.mouseWheelUpReact();
                else
                    ql.mouseWheelDownReact();

            }else if(mc2.find()){
                DosageLabel dl = (DosageLabel) currentLabel;

                if(wheelRotation == upRotation)
                    dl.mouseWheelUpReact();
                else
                    dl.mouseWheelDownReact();
            }

        }
    }
}
