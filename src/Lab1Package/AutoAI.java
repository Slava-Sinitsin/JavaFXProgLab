package Lab1Package;

import java.util.ArrayList;

public class AutoAI extends BaseAI {
    public AutoAI(ArrayList<Transport> transportArr) {
        super(transportArr);
    }

    @Override
    public void run() {
        while (check()) {
            synchronized (transportArr) {
                for (Transport transport : transportArr) {
                    if (transport instanceof Auto) {
                        if (transport.imageView.getLayoutX() != 1240 && transport.imageView != null) {
                            transport.imageView.setLayoutX((transport.imageView.getLayoutX()) + 5);
                        }
                    }
                }
            }
            mySleep(50);
        }
    }
}