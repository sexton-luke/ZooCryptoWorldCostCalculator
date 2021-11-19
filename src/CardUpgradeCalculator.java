import java.util.HashMap;

public class CardUpgradeCalculator {
    private final HashMap<Integer, Integer> miningPowerConsumptionLevels;
    private final double baseMiningPower;

    public CardUpgradeCalculator(double baseMiningPower, HashMap<Integer, Integer> upgradeMiningConsumptionLevels) {
        this.miningPowerConsumptionLevels = upgradeMiningConsumptionLevels;
        this.baseMiningPower = baseMiningPower;
    }


    public double calculateNewLevelMiningPower(double currentMiningPower, int newLevel) {
        double consumedMiningPower = miningPowerConsumptionLevels.get(newLevel) * 1.2;
        double baseMiningPowerBonus = baseMiningPower * 0.2;

        return currentMiningPower + consumedMiningPower + baseMiningPowerBonus;
    }
}
