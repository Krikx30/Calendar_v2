package containers;

import java.awt.*;

public class MiddleContainer extends ContainerClass{
    public MiddleContainer(Container parent_to_add, String ID, int width, int height){
        super(parent_to_add, ID, width, height);
    }

    @Override
    protected void setContainerLayout() {
        super.setContainerLayout();
        GridLayout midGridLayout = new GridLayout(4,8,0,0);
        setLayout(midGridLayout);
    }
}
