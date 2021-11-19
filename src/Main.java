/**
 * Title: Zoo Crypto World - Team Mining Power Cost Calculator
 * Author: Luke Sexton
 * GitHub: https://github.com/valentina-valentine
 * <p>
 * This program calculates the following:
 * -   Total mining power of a completed team with a level 5 epic card and team bonus.
 * -   Cost and ROI based on the total mining power of the account.
 * <p>
 **/

public class Main {


    public static void main(String[] args) {
        Settings settings = new Settings();
        EpicCard epicCard = new EpicCard(settings.EPIC_CARD_BASE_MINING_POWER);

        CardUpgradeCalculator cardUpgradeCalculator = new CardUpgradeCalculator(settings.EPIC_CARD_BASE_MINING_POWER,
                settings.getMiningPowerConsumptionLevels());

        double level5EpicCardMiningPower = calculateLevel5MiningPower(epicCard, cardUpgradeCalculator);

        MiningPowerCalculator miningPowerCalculator = new MiningPowerCalculator(
                level5EpicCardMiningPower,
                settings.TEAM_BONUS_MINING_POWER,
                settings.TEAM_MINING_POWER_WITHOUT_EPIC_CARD,
                settings.ACCOUNT_MINING_POWER);

        CostCalculator costCalculator = new CostCalculator(
                settings.CARD_COSTS,
                settings.getCardUpgradeRequirements(),
                miningPowerCalculator.getNewAccountMiningPower(),
                settings.MINING_RATE_PER_100_ZOO,
                settings.KEY_GENERATION_PER_DAY,
                settings.ZOO_PRICE,
                settings.KEY_PRICE);

        displayTeamCost(settings.CARD_COSTS, costCalculator.getTeamCost(), miningPowerCalculator.getTotalTeamMiningPower());
        displayUpgradeCost(costCalculator.getUpgradeCost());
        displayTotalCost(costCalculator.getTotalCost());
        displayROI(costCalculator.getROI(), costCalculator.getROIWithKeyToken(),
                settings.ACCOUNT_MINING_POWER,
                miningPowerCalculator.getNewAccountMiningPower());

        displayNewDailyRewards(costCalculator.getDailyZooRewardsInDollars(),
                costCalculator.getDailyKeyRewardsInDollars());
    }

    private static double calculateLevel5MiningPower(EpicCard epicCard, CardUpgradeCalculator cardUpgradeCalculator) {
        // Build mining power data from Epic card levels 1 to 5
        int startLevel = 2;
        int totalLevel = 5;

        for (int level = startLevel; level <= totalLevel; level++) {
            double upgradedMiningPower = cardUpgradeCalculator.calculateNewLevelMiningPower(epicCard.getMiningPower(), level);

            epicCard.setLevel(level);
            epicCard.setMiningPower(upgradedMiningPower);

            System.out.println(epicCard);
        }

        return epicCard.getMiningPower();
    }

    private static void displayTeamCost(int[] cardCosts, double teamCost, double teamMiningPower) {
        System.out.println("\nTEAM COST");
        System.out.println("\tJunk Card Cost:\t\t\t\t\t" + cardCosts[0]);
        System.out.println("\tNormal Card Cost:\t\t\t\t" + cardCosts[1]);
        System.out.println("\tRare Card Cost:\t\t\t\t\t" + cardCosts[2]);
        System.out.println("\tEpic Card Cost:\t\t\t\t\t" + cardCosts[3]);

        System.out.println("\n\tTotal Team Cost:\t\t\t\t" + teamCost);
        System.out.println("\tTotal Team Mining Power:\t\t" + teamMiningPower);
    }

    private static void displayUpgradeCost(double upgradeCost) {
        System.out.println("\nEPIC CARD UPGRADE COST TO LEVEL 5");
        System.out.println("\tUpgrade Cost:\t\t\t\t\t" + upgradeCost);
    }

    private static void displayTotalCost(double totalCost) {
        System.out.println("\nTOTAL COST");
        System.out.println("\tTotal Cost:\t\t\t\t\t\t" + totalCost);
    }

    private static void displayROI(double ROI, double ROIWithKeyToken, double accountMiningPower,
                                   double newAccountMiningPower) {
        System.out.println("\nRETURN ON INVESTMENT");
        System.out.println("\tROI (Zoo Only):\t\t\t\t\t" + ROI + " days.");
        System.out.println("\tROI (With Key Contributions):\t" + ROIWithKeyToken + " days.");

        System.out.println("\n\tCurrent Account Mining Power:\t" + accountMiningPower);
        System.out.println("\tNew Account Mining Power:\t\t" + newAccountMiningPower);
    }

    private static void displayNewDailyRewards(double dailyZooRewardsInDollars,
                                               double dailyKeyRewardsInDollars) {

        System.out.println("\nNEW DAILY REWARDS");

        System.out.println("\n\tDaily Zoo Rewards in Dollars:\t" + dailyZooRewardsInDollars);
        System.out.println("\tDaily Key Rewards in Dollars:\t" + dailyKeyRewardsInDollars);

        double totalRewards = dailyZooRewardsInDollars + dailyKeyRewardsInDollars;
        System.out.println("\tTotal Rewards in Dollars:\t\t" + totalRewards);

    }
}
