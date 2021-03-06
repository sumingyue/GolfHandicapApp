package main.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.user.User;

import java.util.List;

/**
 * Hole Dao Interface.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/09/2014
 * @project MyHandicapApp
 */
public interface HoleDao extends GenericDAO<Hole, Long> {

    List<Hole> retrieveHoleAverageByHolePar(User user, String holePar);

    List<Hole> retrieveHoleAverageByHoleYardage(User user, String averageRequested);
}
