package com.saptakdas.chemistry.chemicalequation.version4;

/**
 * Simple Algebra class to solve equations from simplified matrix from gaussian elimination. Note that AlgebraEquation.setSize(Integer size) must be used before any AlgebraEquation instances can be made.
 */
public class AlgebraEquation {
    public static Fraction[] variables;
    public static Integer size;
    public Fraction[] equation;

    /**
     * @param size Number of Variables in Matrix System
     */
    public static void setSize(Integer size){
        AlgebraEquation.size = size;
        AlgebraEquation.variables = new Fraction[size];
    }

    /**
     * AlgebraEquation Initializer in the form of a Fraction array. Equation solving is automatic. Answers to each variable are saved in AlgebraEquation.variables for future solving and final results.
     * @param equationSide Fraction Array of variable coefficients(matrix row). Note since matrix is not augmented, other side values are assumed to be 0.
     */
    public AlgebraEquation(Fraction[] equationSide) {
        this.equation = equationSide;
        this.solve();
    }

    /**
     * Simple solving algorithm to represent all variables in terms of last variable. Note this needs to substitute any unknown variables. As a result, AlgebraEquations need to be made from the bottom to top of matrix(given that solving method is like Reduced Row Echelon or Gaussian Elimination where the diagonal is from top left to bottom right).
     */
    public void solve() {
        //Making all other variables into final variable form.
        Integer firstIndex = null;
        for(int i=0; i<this.equation.length-1; i++) {
            if(!this.equation[i].equals(new Fraction(0, 1))) {
                //Fraction is non zero
                if (this.equation[i].equals(new Fraction(1, 1)) && firstIndex == null) {
                    //Fraction is nonzero, but on one diagonal. Doesn't need to be replaced.
                    firstIndex = i;
                } else {
                    //Needs to replaced. Check value in class for value.
                    Fraction variableReplacement = AlgebraEquation.variables[i];
                    this.equation[AlgebraEquation.size-1] = Fraction.add(this.equation[AlgebraEquation.size-1], Fraction.multiply(variableReplacement, this.equation[i]));
                }
            }
        }
        AlgebraEquation.variables[firstIndex] = Fraction.negate(this.equation[AlgebraEquation.size-1]);
    }
}
