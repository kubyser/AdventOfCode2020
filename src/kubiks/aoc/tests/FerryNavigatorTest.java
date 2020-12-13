package kubiks.aoc.tests;

import kubiks.aoc.day12.FerryNavigator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FerryNavigatorTest {

    @Test
    void executeProgramForShip() throws Exception {
        List<String> program = new ArrayList<>();
        program.add("F10");
        program.add("N3");
        program.add("F7");
        program.add("R90");
        program.add("F11");
        FerryNavigator navigator = new FerryNavigator();
        navigator.executeProgramForShip(program);
        assertEquals(25, navigator.getManhattanDistance());
    }

    @Test
    void testShipRotation() throws Exception {
        List<String> program = new ArrayList<>();
        program.add("R90");
        FerryNavigator navigator = new FerryNavigator();
        assertEquals(1, navigator.getShipHeadingX());
        assertEquals(0, navigator.getShipHeadingY());
        navigator.executeProgramForShip(program);
        assertEquals(0, navigator.getShipHeadingX());
        assertEquals(-1, navigator.getShipHeadingY());
        navigator.executeProgramForShip(program);
        assertEquals(-1, navigator.getShipHeadingX());
        assertEquals(0, navigator.getShipHeadingY());
        navigator.executeProgramForShip(program);
        assertEquals(0, navigator.getShipHeadingX());
        assertEquals(1, navigator.getShipHeadingY());
        program.clear();
        program.add("L90");
        navigator.executeProgramForShip(program);
        assertEquals(-1, navigator.getShipHeadingX());
        assertEquals(0, navigator.getShipHeadingY());
        navigator.executeProgramForShip(program);
        assertEquals(0, navigator.getShipHeadingX());
        assertEquals(-1, navigator.getShipHeadingY());
        navigator.executeProgramForShip(program);
        assertEquals(1, navigator.getShipHeadingX());
        assertEquals(0, navigator.getShipHeadingY());
        navigator.executeProgramForShip(program);
        assertEquals(0, navigator.getShipHeadingX());
        assertEquals(1, navigator.getShipHeadingY());
    }

    @Test
    void executeProgramForWaypoint() throws Exception {
        List<String> program = new ArrayList<>();
        program.add("R90");
        FerryNavigator navigator = new FerryNavigator();
        assertEquals(10, navigator.getWayPointPositionX());
        assertEquals(1, navigator.getWayPointPositionY());
        navigator.executeProgramForWaypoint(program);
        assertEquals(1, navigator.getWayPointPositionX());
        assertEquals(-10, navigator.getWayPointPositionY());
        navigator.executeProgramForWaypoint(program);
        assertEquals(-10, navigator.getWayPointPositionX());
        assertEquals(-1, navigator.getWayPointPositionY());
        navigator.executeProgramForWaypoint(program);
        assertEquals(-1, navigator.getWayPointPositionX());
        assertEquals(10, navigator.getWayPointPositionY());
        navigator.executeProgramForWaypoint(program);
        assertEquals(10, navigator.getWayPointPositionX());
        assertEquals(1, navigator.getWayPointPositionY());
        program.clear();
        program.add("L90");
        navigator.executeProgramForWaypoint(program);
        assertEquals(-1, navigator.getWayPointPositionX());
        assertEquals(10, navigator.getWayPointPositionY());
        navigator.executeProgramForWaypoint(program);
        assertEquals(-10, navigator.getWayPointPositionX());
        assertEquals(-1, navigator.getWayPointPositionY());
        navigator.executeProgramForWaypoint(program);
        assertEquals(1, navigator.getWayPointPositionX());
        assertEquals(-10, navigator.getWayPointPositionY());
        navigator.executeProgramForWaypoint(program);
        assertEquals(10, navigator.getWayPointPositionX());
        assertEquals(1, navigator.getWayPointPositionY());
        program.clear();
        program.add("L180");
        navigator.executeProgramForWaypoint(program);
        assertEquals(-10, navigator.getWayPointPositionX());
        assertEquals(-1, navigator.getWayPointPositionY());
        program.clear();
        program.add("F10");
        program.add("N3");
        program.add("F7");
        program.add("R90");
        program.add("F11");
        navigator = new FerryNavigator();
        navigator.executeProgramForWaypoint(program);
        assertEquals(286, navigator.getManhattanDistance());
    }

}