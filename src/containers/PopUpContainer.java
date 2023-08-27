package containers;

import mainPackage.Application;

import javax.swing.*;

abstract class PopUpContainer extends JPanel {

    private Application app;

    public PopUpContainer(Application app){
        this.app = app;

    }

    abstract void initProperties();
    abstract void initPosition();
    abstract void setLayout();
}
