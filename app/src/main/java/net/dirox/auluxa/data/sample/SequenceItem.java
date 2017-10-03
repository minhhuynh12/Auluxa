package net.dirox.auluxa.data.sample;

/**
 * Created by MyPC on 30/06/2017.
 */

public class SequenceItem {
    private int id;
    private String sequenceFirst;
    private String sequenceSecond;
    private String sequenceSetDelay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSequenceFirst() {
        return sequenceFirst;
    }

    public void setSequenceFirst(String sequenceFirst) {
        this.sequenceFirst = sequenceFirst;
    }

    public String getSequenceSecond() {
        return sequenceSecond;
    }

    public void setSequenceSecond(String sequenceSecond) {
        this.sequenceSecond = sequenceSecond;
    }

    public String getSequenceSetDelay() {
        return sequenceSetDelay;
    }

    public void setSequenceSetDelay(String sequenceSetDelay) {
        this.sequenceSetDelay = sequenceSetDelay;
    }

    public SequenceItem(int id, String sequenceFirst, String sequenceSecond, String sequenceSetDelay) {
        this.id = id;
        this.sequenceFirst = sequenceFirst;
        this.sequenceSecond = sequenceSecond;
        this.sequenceSetDelay = sequenceSetDelay;
    }

    public SequenceItem() {
    }
}
