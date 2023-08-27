package containers;

import java.awt.*;

public class TopLeftContainer extends ContainerClass{
    public TopLeftContainer(Container parent_to_add, String ID, int width, int height){
        super(parent_to_add, ID, width, height);
    }

    @Override
    protected void setContainerLayout() {
        super.setContainerLayout();
        BorderLayout bl = new BorderLayout(0,0);
        setLayout(bl);
    }
}
