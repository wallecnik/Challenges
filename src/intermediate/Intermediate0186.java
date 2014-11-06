package intermediate;

/**
 * DO NOT WORK
 *
 * Created by Wallecnik on 02.11.14.
 */
public class Intermediate0186 {

    private static Planet[] PLANETS = {
        new Planet (0.0, 0.0, "Sun"),
        new Planet (0.241, 0.387, "Mercury"),
        new Planet (0.615, 0.723, "Venus"),
        new Planet (1.0, 1.0, "Earth"),
        new Planet (1.881, 1.524, "Mars"),
        new Planet (11.862, 5.204, "Jupiter"),
        new Planet (29.457, 9.582, "Saturn"),
        new Planet (84.017, 19.189, "Uranus"),
        new Planet (164.795, 30.071, "Neptune"),
    };

    public Intermediate0186 () {}

    public void syzygyOccurence (double time) {

        double xPlanets[] = new double[9];
        double yPlanets[] = new double[9];

        //create point for every planet
        for (int i = 0; i < 9; i++) {
            xPlanets[i] =
                    Math.pow(PLANETS[i].getRadius(), 2) * // r^2
                    Math.pow(Math.cos(time / PLANETS[i].getPeriod() * 2 * Math.PI), 2);

            yPlanets[i] =
                    Math.pow(PLANETS[i].getRadius(), 2) * // r^2
                    Math.pow(Math.sin(time / PLANETS[i].getPeriod() * 2 * Math.PI), 2);
        }


        for (int i = 0; i < 8; i++) {
            for (int j = i+1; j < 9; j++) {

                boolean kontr = false;

                StringBuffer sb = new StringBuffer();
                Vector vect1 = new Vector(Math.abs(xPlanets[i] - xPlanets[j]), Math.abs(yPlanets[i] - yPlanets[j]));
                sb.append(PLANETS[i].getName() + "-" + PLANETS[j].getName());

                for (int k = 0; k < 8; k++) {

                    if (k==i || k==j) continue;
                    Vector vect2 = new Vector(Math.abs(xPlanets[k] - xPlanets[j]), Math.abs(yPlanets[k] - yPlanets[j]));
                    if ((Math.abs(vect1.angle(vect2))) < 1.0 ) {
                        // Syzygy occurs
                        sb.append("-" + PLANETS[k].getName());
                        kontr = true;
                    }

                }

                if (kontr) {
                    System.out.println(sb);
                }

            }
        }

    }



    private static class Planet {
        private double period;
        private double radius;
        private String name;
        private Planet(double period, double radius, String name) {
            this.period = period;
            this.radius = radius;
            this.name = name;
        }
        private double getPeriod() { return period; }
        private double getRadius() { return radius; }
        private String getName()   { return name; }
    }

    private class Vector {
        private double x;
        private double y;
        public Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }
        private double angle(Vector line) { // in degrees
            return Math.acos((this.x * line.x + this.y * line.y) / (this.length() * line.length())) / Math.PI * 180.0;
        }
        private double length() {
            return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        }
        @Override
        public String toString () {
            return "(" + x + "," + y + ")";
        }
    }

}
