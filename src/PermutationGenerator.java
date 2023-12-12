import java.util.Arrays;

public class PermutationGenerator {

    // Function to find the largest mobile integer in a given permutation
    private static int findLargestMobile(int[] perm, int[] dir) {
        int maxMobile = 0;
        int mobileIndex = -1;
        int size = perm.length;

        for (int i = 0; i < size; ++i) {
            if (((dir[i] == -1) && (i > 0) && (perm[i] > perm[i - 1])) ||
                    ((dir[i] == 1) && (i < size - 1) && (perm[i] > perm[i + 1]))) {
                if (perm[i] > maxMobile) {
                    maxMobile = perm[i];
                    mobileIndex = i;
                }
            }
        }

        return mobileIndex;
    }

    // Function to generate the next permutation using the Steinhaus–Johnson–Trotter algorithm
    private static void generateNextPermutation(int[] permutation) {
        int size = permutation.length;
        int[] direction = new int[size];
        Arrays.fill(direction, -1); // -1 represents left direction

        // Find the largest mobile integer
        int mobileIndex = findLargestMobile(permutation, direction);

        if (mobileIndex != -1) {
            int mobileElement = permutation[mobileIndex];
            int dir = direction[mobileIndex];

            // Swap the mobile element with the adjacent element in its direction
            int temp = permutation[mobileIndex];
            permutation[mobileIndex] = permutation[mobileIndex + dir];
            permutation[mobileIndex + dir] = temp;

            temp = direction[mobileIndex];
            direction[mobileIndex] = direction[mobileIndex + dir];
            direction[mobileIndex + dir] = temp;

            // Reverse the direction of all elements greater than the mobile element
            for (int i = 0; i < size; ++i) {
                if (permutation[i] > mobileElement) {
                    direction[i] *= -1;
                }
            }
        }
    }

    // Function to print a permutation
    private static void printPermutation(int[] permutation) {
        for (int num : permutation) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] permutation = {1, 3, 4, 2}; // Change this permutation as needed

        System.out.print("Current permutation: ");
        printPermutation(permutation);

        generateNextPermutation(permutation);

        System.out.print("Next permutation: ");
        printPermutation(permutation);
    }
}
