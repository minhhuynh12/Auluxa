package net.dirox.auluxa.utils.sizes;

import java.io.Serializable;

/**
 * Created by an on 6/13/2017.
 */

public class Size implements Serializable {

    private static final long serialVersionUID = 1L;
    public double width;
    public double height;

    public Size () {
        width = height = 1.0;
    }

    public Size(double w, double h) {
        width = w; height = h;
    }
}

