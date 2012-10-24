package workers;

import java.util.List;

import play.Logger;

import models.TripMetaData;

public class StatisticsGenerator {

    private static double crowFlyDistanceOverhead = 1.3;
    private static double distanceToTravelTimeRatio = 20.0;

    public static void updateStatistics() {
	Logger.info("Generating statisctics... again");
	List<TripMetaData> calculatedMetaData = TripMetaData.find.where().ne("calculated_duration", 0).findList();
	double totalDistance = 0;
	double totalCrowFlyDistance = 0;
	double totalTravelTime = 0;
	for (TripMetaData tmd : calculatedMetaData) {
	    totalDistance += tmd.getDirectionsDistance();
	    totalCrowFlyDistance += tmd.getCrowFliesDistance();
	    totalTravelTime += tmd.getCalculatedDuration();
	}

	double newOverhead = totalDistance / totalCrowFlyDistance;

	if (newOverhead != crowFlyDistanceOverhead) {
	    crowFlyDistanceOverhead = totalDistance / totalCrowFlyDistance;
	    Logger.info("Overhead changed from " + crowFlyDistanceOverhead + " to " + newOverhead);
	}

	double newRatio = totalDistance / totalTravelTime;
	if (newRatio != distanceToTravelTimeRatio) {
	    Logger.info("Ratio changed from " + distanceToTravelTimeRatio + " to " + newRatio);
	    distanceToTravelTimeRatio = totalDistance / totalTravelTime;
	}
    }

    public static double getCrowFlyDistanceOverhead() {
	return crowFlyDistanceOverhead;
    }

    public static double getDistanceToTravelTimeRatio() {
	return distanceToTravelTimeRatio;
    }

}
