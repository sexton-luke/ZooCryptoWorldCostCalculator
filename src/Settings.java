import java.util.HashMap;

public class Settings {
    // Zoo Account Attributes
    public final double ACCOUNT_MINING_POWER = 331;
    public final double KEY_GENERATION_PER_DAY = 1;
    public final double MINING_RATE_PER_100_ZOO = 4;

    // Token Prices
    public final double ZOO_PRICE = 0.4;
    public final double KEY_PRICE = 8;

    // Card Attributes
    public final int EPIC_CARD_BASE_MINING_POWER = 17;
    public final int[] CARD_COSTS = {5, 10, 35, 140}; // Junk, Normal, Rare, Epic
    public final double TEAM_BONUS_MINING_POWER = 50;
    public final double TEAM_MINING_POWER_WITHOUT_EPIC_CARD = 6;

    // Data Containers
    private final HashMap<Integer, Integer> miningPowerConsumptionLevels = new HashMap<>();
    private final HashMap<String, Integer> cardUpgradeRequirements = new HashMap<>();


    public Settings() {
        setCardUpgradeQuantities();
        setMiningPowerConsumptionLevels();
    }

    public HashMap<Integer, Integer> getMiningPowerConsumptionLevels() {
        return this.miningPowerConsumptionLevels;
    }

    public HashMap<String, Integer> getCardUpgradeRequirements() {
        return this.cardUpgradeRequirements;
    }

    private void setCardUpgradeQuantities() {
        cardUpgradeRequirements.put("Junk: ", 2);
        cardUpgradeRequirements.put("Normal:", 2);
        cardUpgradeRequirements.put("Rare:", 1);
    }

    private void setMiningPowerConsumptionLevels() {
        /* Calculate mining power consumed from upgrading epic card.
            - Example: Start Level 1 - End Level 2 = 1 Mining Power (Junk Card)
        */
        int startLevel = 2;
        int totalLevel = 5;

        for (int level = startLevel; level <= totalLevel; level++) {
            int consumedMiningPower = level - 1;
            miningPowerConsumptionLevels.put(level, consumedMiningPower);
        }
    }
}
