package containers;

import java.awt.*;

public class MainContainer extends ContainerClass{
    public MainContainer(Container parent_to_add, String ID, int width, int height){
        super(parent_to_add, ID, width, height);
    }

    @Override
    protected void setContainerLayout() {
        super.setContainerLayout();
        FlowLayout mainFlowLayout = new FlowLayout(FlowLayout.LEFT,0,0);
        this.setLayout(mainFlowLayout);
    }
}
