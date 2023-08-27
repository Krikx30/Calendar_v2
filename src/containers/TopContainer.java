package containers;

import java.awt.*;

public class TopContainer extends ContainerClass{
    public TopContainer(Container parent_to_add, String ID, int width, int height){
        super(parent_to_add, ID, width, height);
    }

    @Override
    protected void setContainerLayout() {
        super.setContainerLayout();
        BorderLayout topBorderLayout = new BorderLayout(0,0);
        this.setLayout(topBorderLayout);
    }
}
