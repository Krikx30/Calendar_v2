package containers;

import java.awt.*;

public class TopMiddleContainer extends ContainerClass{
    public TopMiddleContainer(Container parent_to_add, String ID, int width, int height){
        super(parent_to_add, ID, width, height);
    }

    @Override
    protected void setContainerLayout() {
        super.setContainerLayout();
        BorderLayout bl = new BorderLayout(0,0);
        setLayout(bl);
    }
}
