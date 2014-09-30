package main.java.co.uk.myhandicap.controllers.myHandicap;

import main.java.co.uk.myhandicap.calculation.handicap.Handicap;
import main.java.co.uk.myhandicap.calculation.handicap.HandicapCalculation;
import main.java.co.uk.myhandicap.controllers.IAppController;
import main.java.co.uk.myhandicap.exceptions.UserNotFoundException;
import main.java.co.uk.myhandicap.model.handicap.ScoreCard;
import main.java.co.uk.myhandicap.model.user.User;
import main.java.co.uk.myhandicap.service.ScoreCardService;
import main.java.co.uk.myhandicap.service.UserService;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

import static java.lang.String.format;

/**
 * My Handicap Controller.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/07/2014
 * @project MyHandicapApp
 */
@Controller
@RequestMapping(value="/myHandicap")
public class MyHandicapController implements IAppController {

    private final XLogger logger = XLoggerFactory.getXLogger(MyHandicapController.class);

    @Value("${logging.info}")
    private String logInfoMsg;

    @Autowired
    private HandicapCalculation handicapCalculation;

    @Autowired
    private ScoreCardService scoreCardService;

    @Autowired
    private UserService userService;

    @Value("${calculateHandicap.noPlayerScoreCards}")
    private String noPlayerScoreCards;

    /**
     * handleRequest for myHandicap/history (GET).
     *
     * @param mav
     * @param principal
     * @return
     */
    @Override
    @RequestMapping(value="/history")
    public ModelAndView handleRequest(ModelAndView mav, Principal principal) {
        final String METHOD_NAME = ".handleRequest()";

        // set view name
        mav.setViewName("myHandicap/history");

        try {
            User user = userService.findUserByUsername(principal.getName());

            logger.info(format(logInfoMsg, this.getClass().getName(), METHOD_NAME, format("retrieve handicap score for user, %s ...", user.getUsername())));
            Handicap handicap = handicapCalculation.calculateUserHandicapScore(user.getId());

            logger.info(format(logInfoMsg, this.getClass().getName(), METHOD_NAME, format("retrieve ScoreCards for user, %s ...", user.getUsername())));
            List<ScoreCard> scoreCardList = scoreCardService.retrieveUserScoredCardsById(user);

            // output message to user if there are no registered score cards
            if(scoreCardList.isEmpty()) {
                mav.addObject("noPlayerScoreCards", noPlayerScoreCards);
                logger.info(format(logInfoMsg, this.getClass().getName(), METHOD_NAME, format("there are no registered scores for user, %s ...", user.getUsername())));
            }

            mav.addObject("playerHandicap", handicap);
            mav.addObject("playerScoreCards", scoreCardList);

        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        return mav;
    }

}
