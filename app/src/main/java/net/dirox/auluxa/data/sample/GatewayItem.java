package net.dirox.auluxa.data.sample;

/**
 * Created by trungnq on 14/06/2017.
 */

public class GatewayItem {
    private int id;
    private String gatewayName;
    private boolean gatewayState;

    public GatewayItem(int id, String gatewayName, boolean gatewayState) {
        this.id = id;
        this.gatewayName = gatewayName;
        this.gatewayState = gatewayState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public boolean isGatewayState() {
        return gatewayState;
    }

    public void setGatewayState(boolean gatewayState) {
        this.gatewayState = gatewayState;
    }
}
