public class MiningPowerCalculator {
    double newAccountMiningPower;
    double totalTeamMiningPower;

    public MiningPowerCalculator(double epicCardMiningPower, double teamBonusMiningPower,
                                 double teamMiningPowerWithoutEpicCard, double accountMiningPower) {
        calculateTotalTeamMiningPower(epicCardMiningPower, teamBonusMiningPower, teamMiningPowerWithoutEpicCard);
        calculateAccountMiningPowerWithNewTeam(accountMiningPower);
    }

    private void calculateTotalTeamMiningPower(double epicCardMiningPower,
                                               double teamBonusMiningPower,
                                               double teamMiningPowerWithoutEpicCard) {
        totalTeamMiningPower = epicCardMiningPower + teamBonusMiningPower + teamMiningPowerWithoutEpicCard;
    }

    private void calculateAccountMiningPowerWithNewTeam(double accountMiningPower) {
        newAccountMiningPower = accountMiningPower + totalTeamMiningPower;
    }

    public double getTotalTeamMiningPower() {
        return totalTeamMiningPower;
    }

    public double getNewAccountMiningPower() {
        return newAccountMiningPower;
    }

}
