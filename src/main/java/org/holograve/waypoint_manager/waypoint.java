package org.holograve.waypoint_manager;

import java.time.LocalDateTime;

public record waypoint(int id, String name, String desc, Double xCoord, Double yCoord, Double zCoord, LocalDateTime updateTime){}
