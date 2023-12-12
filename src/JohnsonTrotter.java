public class JohnsonTrotter {
    // Direction constants
    public static final int LEFT = -1;
    public static final int RIGHT = 1;

    // Function to print a permutation
    public static void printPermutation(int[] permutation) {
        for (int i = 0; i < permutation.length; i++) {
            System.out.print(permutation[i] + " ");
        }
        System.out.println();
    }

    // Function to get the index of the largest mobile integer
    public static int getMobile(int[] permutation, int[] direction) {
        int mobilePrev = 0;
        int mobile = -1; // -1 when no mobile integer is found
        for (int i = 0; i < permutation.length; i++) {
            // Direction is LEFT and the element has a valid left position
            if (direction[i] == LEFT && i != 0) {
                if (permutation[i] > permutation[i - 1] && permutation[i] > mobilePrev) {
                    mobile = permutation[i];
                    mobilePrev = mobile;
                }
            }

            // Direction is RIGHT and the element has a valid right position
            if (direction[i] == RIGHT && i != permutation.length - 1) {
                if (permutation[i] > permutation[i + 1] && permutation[i] > mobilePrev) {
                    mobile = permutation[i];
                    mobilePrev = mobile;
                }
            }
        }
        return mobile;
    }

    // Function to swap elements at indices i and j in permutation and direction arrays
    public static void swap(int[] permutation, int[] direction, int i, int j) {
        int temp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = temp;

        temp = direction[i];
        direction[i] = direction[j];
        direction[j] = temp;
    }

    // Function to search for the position of k in the permutation array
    public static int searchArr(int[] permutation, int n, int mobile) {
        for (int i = 0; i < n; i++)
            if (permutation[i] == mobile)
                return i + 1; // 1-based index
        return 0;
    }

    // Function to generate permutations using the Johnson-Trotter algorithm
    public static void johnsonTrotter(int n) {
        int[] permutation = new int[n];
        int[] direction = new int[n];

        // Initialize the first permutation
        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
            direction[i] = LEFT;
        }

        // Print the first permutation
        printPermutation(permutation);

        // Generate and print all the other permutations
        int mobile = getMobile(permutation, direction);
        while (mobile != -1) { // While there is a mobile integer
            int pos = searchArr(permutation, n, mobile);

            // Swap the mobile integer according to its direction
            if (direction[pos - 1] == LEFT) {
                swap(permutation, direction, pos - 1, pos - 2);
            } else if (direction[pos - 1] == RIGHT) {
                swap(permutation, direction, pos - 1, pos);
            }

            // Reverse the direction of all integers larger than the current mobile integer
            for (int i = 0; i < n; i++) {
                if (permutation[i] > mobile) {
                    direction[i] = direction[i] == LEFT ? RIGHT : LEFT;
                }
            }

            printPermutation(permutation);
            mobile = getMobile(permutation, direction);
        }
    }

    // Main method to test the Johnson-Trotter algorithm
    public static void main(String[] args) {
        int N = 5; // Change N to generate permutations for a different size
        johnsonTrotter(N);
    }
}
