import java.util.HashMap;
import java.util.Map;

public class CostCalculator {
    private final double teamCost;
    private final double upgradeCost;
    private double totalCost;
    private double ROI;
    private double ROIWithKeyToken;

    private final double dailyZooRewardsInDollars;
    private final double dailyKeyRewardsInDollars;

    public CostCalculator(int[] cardCosts, HashMap<String, Integer> cardUpgradeRequirements,
                          double accountMiningPower,
                          double miningRate,
                          double keyGenerationPerDay,
                          double zooTokenPrice,
                          double keyTokenPrice) {
        teamCost = calculateTeamCost(cardCosts);
        upgradeCost = calculateUpgradeCost(cardCosts, cardUpgradeRequirements);

        double zooGenerationPerDay = calculateZooGenerationPerDay(accountMiningPower, miningRate);

        calculateTotalCost();

        dailyZooRewardsInDollars = calculateDailyRewardsInDollars(zooTokenPrice, zooGenerationPerDay);
        dailyKeyRewardsInDollars = calculateDailyRewardsInDollars(keyTokenPrice, keyGenerationPerDay);

        calculateROI();
        calculateROIWithKeyToken();
    }

    private double calculateZooGenerationPerDay(double accountMiningPower, double miningRate) {
        double multiplier = accountMiningPower / 100;
        return miningRate * multiplier;
    }


    private void calculateROI() {
        ROI = totalCost / dailyZooRewardsInDollars;
    }


    private void calculateROIWithKeyToken() {
        double totalDailyRewardsInDollars = dailyZooRewardsInDollars + dailyKeyRewardsInDollars;
        ROIWithKeyToken = totalCost / totalDailyRewardsInDollars;
    }

    private double calculateDailyRewardsInDollars(double tokenPrice, double tokenGenerationPerDay) {
        return tokenPrice * tokenGenerationPerDay;
    }


    private void calculateTotalCost() {
        totalCost = teamCost + upgradeCost;
    }

    private double calculateUpgradeCost(int[] cardCosts, HashMap<String, Integer> cardUpgradeRequirements) {
        double upgradeCost = 0;

        for (Map.Entry<String, Integer> set : cardUpgradeRequirements.entrySet()) {
            int quantity = set.getValue();
            switch (set.getKey()) {
                case "Junk":
                    upgradeCost += (quantity * cardCosts[0]);
                    break;
                case "Normal":
                    upgradeCost += (quantity * cardCosts[1]);
                    break;
                default:
                    upgradeCost += (quantity * cardCosts[2]);
            }
        }
        return upgradeCost;
    }

    private double calculateTeamCost(int[] cardCosts) {
        double teamCost = 0;
        for (int position = 0; position < cardCosts.length - 1; position++) {
            teamCost += cardCosts[position];
        }
        return teamCost;
    }


    public double getTeamCost() {
        return this.teamCost;
    }

    public double getUpgradeCost() {
        return this.upgradeCost;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public double getROI() {
        return this.ROI;
    }

    public double getROIWithKeyToken() {
        return this.ROIWithKeyToken;
    }

    public double getDailyZooRewardsInDollars() {
        return this.dailyZooRewardsInDollars;
    }

    public double getDailyKeyRewardsInDollars() {
        return this.dailyKeyRewardsInDollars;
    }
}
