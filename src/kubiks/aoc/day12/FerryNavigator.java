package kubiks.aoc.day12;

import java.util.List;

public class FerryNavigator {
    int shipX = 0, shipY = 0;
    int shipHeadingX = 1, shipHeadingY = 0;
    int wayPointPositionX = 10, wayPointPositionY = 1;
    int wayPointXHeadingX = 1, wayPointXHeadingY = 0, wayPointYHeadingX = 0, wayPointYHeadingY = 1;

    public int getWayPointPositionX() {
        return wayPointPositionX;
    }

    public int getWayPointPositionY() {
        return wayPointPositionY;
    }


    public int getShipX() {
        return shipX;
    }

    public int getShipY() {
        return shipY;
    }

    public double getShipHeadingX() {
        return shipHeadingX;
    }

    public double getShipHeadingY() {
        return shipHeadingY;
    }

    public int getManhattanDistance() {
        return Math.abs(shipX) + Math.abs(shipY);
    }

    public void executeProgramForShip(List<String> program) throws Exception {
        int newShipHeadingX, newShipHeadingY;
        for (String s : program) {
            char command = s.charAt(0);
            int value = Integer.valueOf(s.substring(1));
            switch (command) {
                case 'R':
                    newShipHeadingX = (int)Math.round(Math.sin(Math.atan2(shipHeadingX, shipHeadingY) + Math.PI * ((double)value)/180));
                    newShipHeadingY = (int)Math.round(Math.cos(Math.atan2(shipHeadingX, shipHeadingY) + Math.PI * ((double)value)/180));
                    shipHeadingX = newShipHeadingX;
                    shipHeadingY = newShipHeadingY;
                    break;
                case 'L':
                    newShipHeadingX = (int)Math.round(Math.sin(Math.atan2(shipHeadingX, shipHeadingY) - Math.PI * ((double)value)/180));
                    newShipHeadingY = (int)Math.round(Math.cos(Math.atan2(shipHeadingX, shipHeadingY) - Math.PI * ((double)value)/180));
                    shipHeadingX = newShipHeadingX;
                    shipHeadingY = newShipHeadingY;
                    break;
                case 'N': shipY += value; break;
                case 'S': shipY -= value; break;
                case 'E': shipX += value; break;
                case 'W': shipX -= value; break;
                case 'F': shipX += value * shipHeadingX; shipY += value * shipHeadingY; break;
                default: throw new Exception("Unexpected command "+ s);
            }
        }
    }

    public void executeProgramForWaypoint(List<String> program) throws Exception {
        int newWaypointPositionX, newWaypointPositionY;
        for (String s : program) {
            char command = s.charAt(0);
            int value = Integer.valueOf(s.substring(1));
            switch (command) {
                case 'R':
                    wayPointXHeadingX = (int)Math.round(Math.sin(Math.PI/2 - Math.PI * ((double)value)/180));
                    wayPointXHeadingY = (int)Math.round(Math.cos(Math.PI/2 - Math.PI * ((double)value)/180));
                    wayPointYHeadingX = (int)Math.round(Math.sin(- Math.PI * ((double)value)/180));
                    wayPointYHeadingY = (int)Math.round(Math.cos(- Math.PI * ((double)value)/180));
                    //I4*E5+J4*F5
                    newWaypointPositionX = wayPointPositionX * wayPointXHeadingX + wayPointPositionY * wayPointXHeadingY;
                    newWaypointPositionY = wayPointPositionX * wayPointYHeadingX + wayPointPositionY * wayPointYHeadingY;
                    wayPointPositionX = newWaypointPositionX;
                    wayPointPositionY = newWaypointPositionY;
                    break;
                case 'L':
                    wayPointXHeadingX = (int)Math.round(Math.sin(Math.PI/2 + Math.PI * ((double)value)/180));
                    wayPointXHeadingY = (int)Math.round(Math.cos(Math.PI/2 + Math.PI * ((double)value)/180));
                    wayPointYHeadingX = (int)Math.round(Math.sin(Math.PI * ((double)value)/180));
                    wayPointYHeadingY = (int)Math.round(Math.cos(Math.PI * ((double)value)/180));
                    newWaypointPositionX = wayPointPositionX * wayPointXHeadingX + wayPointPositionY * wayPointXHeadingY;
                    newWaypointPositionY = wayPointPositionX * wayPointYHeadingX + wayPointPositionY * wayPointYHeadingY;
                    wayPointPositionX = newWaypointPositionX;
                    wayPointPositionY = newWaypointPositionY;
                    break;
                case 'N': wayPointPositionY += value; break;
                case 'S': wayPointPositionY -= value; break;
                case 'E': wayPointPositionX += value; break;
                case 'W': wayPointPositionX -= value; break;
                case 'F': shipX += value * wayPointPositionX; shipY += value * wayPointPositionY; break;
                default: throw new Exception("Unexpected command "+ s);
            }
        }
    }


}
