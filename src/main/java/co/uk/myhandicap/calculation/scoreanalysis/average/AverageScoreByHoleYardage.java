package main.java.co.uk.myhandicap.calculation.scoreanalysis.average;

import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Average Score By Hole Yardage
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/09/2014
 * @project MyHandicapApp
 */
@Component
public class AverageScoreByHoleYardage extends AbstractCalculateAverage implements AverageScore{

    @Override
    public String execute(User user, String averageRequested) {
        return calculateAverage(new ArrayList<ScoreCard>(), averageRequested);
    }

    @Override
    public String calculateAverage(List<ScoreCard> scoreCardList, String averageRequested) {

        // averageRequested equals holeYardage parameter requested by the user

        return null;
    }

    @Override
    String iterateOverRoundsOfGolf(List<ScoreCard> scoreCardList, String averageRequested) {
        return null;
    }
}
