# FractionMatrixSolverAndCalculator
A Light-Weight General Use Case Matrix Solver and Calculator that uses Fractions

## Usage
There are two main uses of this program are as a solver and a calculator. The main program file is MatrixProgram.java.

### Matrix Solver
#### Matrix Solver General
The matrix solver is a light-weight solver program that can solve and show work for an augmented square matrix with the following methods:
* Gaussian Elimination with Back Substitution
* Gauss-Jordan Elimination
* Matrix Equations(Ax=B)
* Cramer's Rule

#### Matrix Solver Usage
* Determine the size of matrix without the size of the augmented matrix row.
* Enter all Fractions one by one as prompted either as a whole integer(2, -4, 42, etc.) or a fraction(1/2, -31/3, -3/7, etc.). Make sure that augmented matrix is on opposite side as other variables. In other words, get all equations into x+y+z+...=3 form.
* Select your choice of solving the matrix(see supported methods in above section).
* Results will be display in Fraction form!

### Matrix Calculator
#### Matrix Calculator General
The matrix calculator is a matrix operation environment with a 10-matrix storage space. These matrices can be operated on and stored as a result. Matrix properties such as determinants can also be determined. Operations supported include:
* Creating/Updating a Matrix
* Determinant of a Matrix
* Multiplicative Inverses
* Matrix Addition and Multiplication
* Scalar Multiplication

#### Matrix Calculator Usage
* When first opened, program will prompt you to enter first matrix to operate on in the environment.
* Enter all Fractions one by one as prompted either as a whole integer(2, -4, 42, etc.) or a fraction(1/2, -31/3, -3/7, etc.).
* This will be stored in the environment matrix space provided.
* You can next choose if you would like to create/update a matrix, compute a determinant, or perform some basic matrix math.
* You can also quit whenever prompted in this main menu.
Note that all operations and computations will show their work step by step in Fraction form!

#### Matrix Math
Matrix Math only allows you to perform one operation at a time, however, it's power lies in the fact that this result can be stored in matrix space. Successive matrix math operations can be used to do more complex operations. A basic matrix operation parser may be a upcoming feature for future releases.
