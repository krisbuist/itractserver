package models;

import com.avaje.ebean.annotation.EnumValue;

public enum TripMatchState {
    @EnumValue("OPEN") OPEN, 
    @EnumValue("POTENTIAL") POTENTIAL,
    @EnumValue("CONFIRMED_POTENTIAL") CONFIRMED_POTENTIAL,
    @EnumValue("DECLINED_POTENTIAL") DECLINED_POTENTIAL,
    @EnumValue("CONFIRMED_MATCH") CONFIRMED_MATCH,
    @EnumValue("DECLINED_MATCH") DECLINED_MATCH,
}
