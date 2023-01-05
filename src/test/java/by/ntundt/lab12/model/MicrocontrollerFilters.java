package by.ntundt.lab12.model;

public class MicrocontrollerFilters {
    private int frequencyMinMhz;
    private int frequencyMaxMhz;
    private double ramMinKb;
    private double ramMaxKb;
    private int flashMinKb;
    private int flashMaxKb;

    public MicrocontrollerFilters(int frequencyMinMhz, int frequencyMaxMhz, double ramMinKb, double ramMaxKb, int flashMinKb, int flashMaxKb) {
        this.frequencyMinMhz = frequencyMinMhz;
        this.frequencyMaxMhz = frequencyMaxMhz;
        this.ramMinKb = ramMinKb;
        this.ramMaxKb = ramMaxKb;
        this.flashMinKb = flashMinKb;
        this.flashMaxKb = flashMaxKb;
    }

    public int getFrequencyMinMhz() {
        return frequencyMinMhz;
    }

    public void setFrequencyMinMhz(int frequencyMinMhz) {
        this.frequencyMinMhz = frequencyMinMhz;
    }

    public int getFrequencyMaxMhz() {
        return frequencyMaxMhz;
    }

    public void setFrequencyMaxMhz(int frequencyMaxMhz) {
        this.frequencyMaxMhz = frequencyMaxMhz;
    }

    public double getRamMinKb() {
        return ramMinKb;
    }

    public void setRamMinKb(double ramMinKb) {
        this.ramMinKb = ramMinKb;
    }

    public double getRamMaxKb() {
        return ramMaxKb;
    }

    public void setRamMaxKb(double ramMaxKb) {
        this.ramMaxKb = ramMaxKb;
    }

    public int getFlashMinKb() {
        return flashMinKb;
    }

    public void setFlashMinKb(int flashMinKb) {
        this.flashMinKb = flashMinKb;
    }

    public int getFlashMaxKb() {
        return flashMaxKb;
    }

    public void setFlashMaxKb(int flashMaxKb) {
        this.flashMaxKb = flashMaxKb;
    }
}
