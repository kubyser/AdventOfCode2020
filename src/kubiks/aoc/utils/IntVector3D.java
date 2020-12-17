package kubiks.aoc.utils;

import java.util.List;

public class IntVector3D {
        int x, y, z;


        public IntVector3D(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public IntVector3D(List<Integer> xyzList) throws Exception {
            if (xyzList.size() != 3) {
                throw new Exception("xyzList size must be exactly three Integers");
            }
            this.x = xyzList.get(0).intValue();
            this.y = xyzList.get(1).intValue();
            this.z = xyzList.get(2).intValue();
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }
}
