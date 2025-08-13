package ttrpg.encounterGen.util;

public class AliasTable {
    private int[] alias;
    private double[] probability;

    public AliasTable(int size) {
        alias = new int[size];
        probability = new double[size];
    }

    public int[] getAlias() { return alias; }
    public void setAlias(int[] alias) { this.alias = alias; }

    public double[] getProbability() { return probability; }
    public void setProbability(double[] probability) { this.probability = probability; }
}
