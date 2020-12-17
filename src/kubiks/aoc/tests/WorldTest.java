package kubiks.aoc.tests;

import kubiks.aoc.day17.World;
import kubiks.aoc.utils.FileReaderUtils;
import kubiks.aoc.utils.IntVector3D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    List<String> getTestData() {
        List<String> data = new ArrayList<>();
        data.add(".#.");
        data.add("..#");
        data.add("###");
        return data;
    }

    @Test
    void calculateNeighbours() {
        World world = new World(getTestData());
        world.calculateNeighbours();
        assertEquals(1, world.getNumNeighbours(0,0,0));
        assertEquals(1, world.getNumNeighbours(1,0,0));
        assertEquals(2, world.getNumNeighbours(2,0,0));
        assertEquals(2, world.getNumNeighbours(2,0,1));
        assertEquals(2, world.getNumNeighbours(2,0,-1));
        world.setCellState(0,0,1, World.CellState.ACTIVE);
        world.setCellState(1,1,-1, World.CellState.ACTIVE);
        world.calculateNeighbours();
        assertEquals(3, world.getNumNeighbours(0,0,0));
        assertEquals(3, world.getNumNeighbours(1,0,0));
        assertEquals(3, world.getNumNeighbours(2,0,0));
        assertEquals(2, world.getNumNeighbours(2,0,1));
        assertEquals(3, world.getNumNeighbours(2,0,-1));
    }

    @Test
    void cellIdFromXYZ() {
        assertEquals("123:5:42", World.cellIdFromXYZ(123, 5, 42));
    }

    @Test
    void posFromCellId() {
        int[] v = World.posFromCellId("123:5:42");
        assertEquals(123, v[0]);
        assertEquals(5, v[1]);
        assertEquals(42, v[2]);
    }

    @Test
    void getCellState() {
        World world = new World(getTestData());
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,0,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(1,0,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(2,0,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,1,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,1,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(2,1,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(0,2,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(1,2,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(2,2,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,0,1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,0,-1));
    }

    @Test
    void getNumNeighbours() {
        World world = new World(getTestData());
        assertEquals(0, world.getNumNeighbours(0,0,0));
        assertEquals(0, world.getNumNeighbours(0,0,1));
    }

    @Test
    void runCycle() {
        World world = new World(getTestData());
        world.runCycle();
        assertEquals(World.CellState.ACTIVE, world.getCellState(0,1,-1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,1,-1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(2,1,-1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,2,-1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,2,-1));
        assertEquals(World.CellState.ACTIVE, world.getCellState(2,2,-1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,3,-1));
        assertEquals(World.CellState.ACTIVE, world.getCellState(1,3,-1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(2,3,-1));

        assertEquals(World.CellState.ACTIVE, world.getCellState(0,1,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,1,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(2,1,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,2,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(1,2,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(2,2,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,3,0));
        assertEquals(World.CellState.ACTIVE, world.getCellState(1,3,0));
        assertEquals(World.CellState.INACTIVE, world.getCellState(2,3,0));

        assertEquals(World.CellState.ACTIVE, world.getCellState(0,1,1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,1,1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(2,1,1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,2,1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,2,1));
        assertEquals(World.CellState.ACTIVE, world.getCellState(2,2,1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(0,3,1));
        assertEquals(World.CellState.ACTIVE, world.getCellState(1,3,1));
        assertEquals(World.CellState.INACTIVE, world.getCellState(2,3,1));

        world.runCycle();
        assertEquals(World.CellState.ACTIVE, world.getCellState(1,2,-2));
        assertEquals(World.CellState.INACTIVE, world.getCellState(1,3,-2));
    }

    @Test
    void countActiveCells() {
        World world = new World(getTestData());
        assertEquals(5, world.countActiveCells());
        world.runCycle();
        assertEquals(11, world.countActiveCells());
        world.runCycle();
        assertEquals(21, world.countActiveCells());
        world.runCycle();
        assertEquals(38, world.countActiveCells());
    }

    @Test
    void runCycles() {
        World world = new World(getTestData());
        world.runCycles(6);
        assertEquals(112, world.countActiveCells());
    }

    @Test
    void runCycles4d() {
        World world = new World(getTestData(), true);
        world.runCycles(6);
        assertEquals(848, world.countActiveCells());
    }


    @Test
    void day17Part1() {
        World world = new World(FileReaderUtils.readStringListFromFile("resources/day17_input.txt"));
        world.runCycles(6);
        assertEquals(386, world.countActiveCells());
    }

}