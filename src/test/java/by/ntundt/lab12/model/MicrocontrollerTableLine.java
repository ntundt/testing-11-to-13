package by.ntundt.lab12.model;

public class MicrocontrollerTableLine {
    private String name;
    private int frequency;
    private double ram;
    private int flash;

    public MicrocontrollerTableLine(String name, int frequency, double ram, int flash) {
        this.name = name;
        this.frequency = frequency;
        this.ram = ram;
        this.flash = flash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public int getFlash() {
        return flash;
    }

    public void setFlash(int flash) {
        this.flash = flash;
    }
}
