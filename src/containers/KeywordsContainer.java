package containers;

import javax.swing.*;
import java.awt.*;

public class KeywordsContainer extends ContainerClass{

    private String borderHexColor;
    public KeywordsContainer(Container parent_to_add, String ID, int width, int height){
        super(parent_to_add, ID, width, height);
    }

    @Override
    protected void setContainerLayout() {

        super.setContainerLayout();

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(fl);

        String borderColor = "#807D75";
        this.borderHexColor = borderColor;

        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.decode(borderColor)));
    }
    private String getBorderHexColor(){
        return borderHexColor;
    }
    private void setBorderHexColor(String hexadecimalColor){
        borderHexColor = hexadecimalColor;
    }
}
