package DataStructures;

import org.json.simple.JSONObject;

public class ConnectionData {

    private Boolean isDouble = false;
    private Long x;
    private Long y;

    public ConnectionData(Boolean isDouble, NodeData next) {
        this.isDouble = isDouble;
        this.x = next.getX();
        this.y = next.getY();
    }

    public ConnectionData(Boolean isDouble, Long x, Long y) {
        this.isDouble = isDouble;
        this.x = x;
        this.y = y;
    }

    public JSONObject toJSON(){

        JSONObject js = new JSONObject();


        js.put("x", this.x);
        js.put("y", this.y);

        js.put("isDouble", isDouble);

        return js;

    }

    public Boolean getDouble() {
        return isDouble;
    }

    public void setDouble(Boolean aDouble) {
        isDouble = aDouble;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
