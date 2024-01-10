package src.classes;

import jmetal.util.JMException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.File;

import jmetal.core.Problem;
import jmetal.core.SolutionType;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.core.Solution;

public class SensorDeployment extends Problem {

    public SensorDeployment(String solution_type, Integer number_of_variables) {

        // Check the solution type
        if (solution_type.compareTo("Real") == 0) {

            solutionType_ = new RealSolutionType(this);
        } else {
            System.out.println("Error, solution type : " + solution_type + "invalid");
        }

        numberOfVariables_ = number_of_variables;
        numberOfConstraints_ = 0;
        problemName_ = "SensorDeployment";
        numberOfObjectives_ = 2;

        // Decision variables limits
        lowerLimit_ = new double[numberOfVariables_];
        upperLimit_ = new double[numberOfVariables_];

        for (int i = 0; i < numberOfVariables_; i++) {

            this.lowerLimit_[i] = 0.0;
            this.upperLimit_[i] = 100.0;

        }
    }

    // Compute objective functions
    public void evaluate(Solution solution) throws JMException {

        Variable[] decisionVariables = solution.getDecisionVariables();
        double[] x = new double[this.numberOfVariables_];
        int[][] cibles = {
                { 50, 50 },
                { 41, 50 },
                { 90, 90 },
                { 86, 89 },
                { 10, 10 },
                { 15, 95 },
        };
        int[] nb_couvertures = new int[6];

        // Get decision variables
        for (int i = 0; i < this.numberOfVariables_; i++) {
            x[i] = decisionVariables[i].getValue();

        }

        // Initialiser nombre de couvertures
        for (int i = 0; i < nb_couvertures.length; i++) {
            nb_couvertures[i] = 0;
        }

        int f1 = 0;
        int f2 = 0;
        int j = 0;

        // Somme nombre de cibles couvertes
        for (int[] row : cibles) {

            for (int i = 0; i < this.numberOfVariables_; i += 2) {
                double capt_x = (double) x[i];
                double capt_y = (double) x[i + 1];

                double dist = Math.pow((row[0] - capt_x), 2) + Math.pow((row[1] - capt_y), 2);
                dist = Math.sqrt(dist);
                // System.out.println(i);

                if (dist < 10.0) {
                    // Nombre de couvertures par cible
                    nb_couvertures[j] = nb_couvertures[j] + 1;
                }
            }
            j += 1;
        }

        // f1
        // Cibles couvertes ou non
        for (int i = 0; i < nb_couvertures.length; i++) {
            if (nb_couvertures[i] > 0) {

                f1 = f1 + 1;

            }
        }

        // f2
        // Trouver le nombre de couvertures minimum
        f2 = 100;
        for (int i = 1; i < nb_couvertures.length; i++) {
            if (nb_couvertures[i] < f2) {
                f2 = nb_couvertures[i];
            }

        }

        solution.setObjective(0, -f1);
        solution.setObjective(1, -f2);

    }

}
