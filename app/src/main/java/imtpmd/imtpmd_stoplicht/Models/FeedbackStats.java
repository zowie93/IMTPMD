package imtpmd.imtpmd_stoplicht.Models;

public class FeedbackStats {
    private int blij;
    private int neutraal;
    private int verdrietig;

    public FeedbackStats(int blij, int neutraal, int verdrietig) {
        this.blij = blij;
        this.neutraal = neutraal;
        this.verdrietig = verdrietig;
    }

    public FeedbackStats() {
        this.blij = 0;
        this.neutraal = 0;
        this.verdrietig = 0;
    }

    public int getBlij() {
        return blij;
    }

    public void setBlij(int blij) {
        this.blij = blij;
    }

    public int getNeutraal() {
        return neutraal;
    }

    public void setNeutraal(int neutraal) {
        this.neutraal = neutraal;
    }

    public int getVerdrietig() {
        return verdrietig;
    }

    public void setVerdrietig(int verdrietig) {
        this.verdrietig = verdrietig;
    }


}
