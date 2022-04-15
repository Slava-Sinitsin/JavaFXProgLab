package Lab1Package;

import java.util.ArrayList;

public class MotoAI extends BaseAI {
    public MotoAI(ArrayList<Transport> transportArr) {
        super(transportArr);
    }

    @Override
    public void run() {
        while (check()) {
            synchronized (transportArr) {
                for (Transport transport : transportArr) {
                    if (transport instanceof Moto) {
                        if (transport.imageView.getLayoutY() != 10 && transport.imageView != null) {
                            transport.imageView.setLayoutY((transport.imageView.getLayoutY()) - 5);
                        }
                    }
                }
            }
            mySleep(50);
        }
    }
}