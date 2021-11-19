public class EpicCard {
    private final double baseMiningPower;
    private int level;
    private double miningPower;

    public EpicCard(double baseMiningPower) {
        this.baseMiningPower = baseMiningPower;
        this.miningPower = this.baseMiningPower;
        this.level = 1;
    }

    public double getMiningPower() {
        return this.miningPower;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMiningPower(double miningPower) {
        this.miningPower = miningPower;
    }

    @Override
    public String toString() {
        return "\nEpic Card Details:" +
                "\n\tBase Mining Power:\t\t" + baseMiningPower +
                "\n\tLevel:\t\t\t\t\t" + level +
                "\n\tCurrent mining Power:\t" + miningPower;
    }
}
