import java.util.Arrays;

public class SJTAlgorithm {
    private int[] dir;

    public SJTAlgorithm(int n) {
        dir = new int[n];
        for (int i = 0; i < n; i++) {
            dir[i] = -1;
        }
    }
    // Function to check if an integer is mobile
    private static boolean isMobile(int[] permutation, int[] dir, int index) {
        int nextIndex = index + dir[index];
        System.out.println("Next index is " + nextIndex);
        boolean isMobile = nextIndex >= 0 && nextIndex < permutation.length && permutation[index] > permutation[nextIndex];
        System.out.println("isMobile? " + isMobile);
        return isMobile;
    }

    private static int getMobileIndex(int[] permutation, int[] dir) {
        int mobilePrev = 0, mobileIndex = -1;
        for (int i = 0; i < permutation.length; i++) {
            if (isMobile(permutation, dir, i) && permutation[i] > mobilePrev) {
                mobilePrev = permutation[i];
                mobileIndex = i;
            }
        }
        System.out.println("This is the mobile index: "+ mobileIndex);
        System.out.println("This is the mobile number: "+ permutation[mobileIndex]);
        System.out.println("This is the permutation: "+ Arrays.toString(permutation));

        return mobileIndex;
    }

    public static int[] nextPermutation(int[] permutation) {
        int n = permutation.length;
        int[] dir = new int[n];

        // Initially, all directions are set to -1 (left).
        Arrays.fill(dir, -1);

        // Find the largest mobile integer in the permutation.
        int mobileIndex = getMobileIndex(permutation, dir);
        if (mobileIndex == -1) { // No mobile integer means this is the last permutation.
            return null;
        }

        // Swap the mobile integer with the adjacent integer in its current direction.
        int swapIndex = mobileIndex + dir[mobileIndex];
        int temp = permutation[mobileIndex];
        permutation[mobileIndex] = permutation[swapIndex];
        permutation[swapIndex] = temp;

        // Change the direction of all integers larger than the current mobile integer.
        for (int i = 0; i < n; i++) {
            if (permutation[i] > permutation[swapIndex]) {
                dir[i] = -dir[i];
            }
        }

        return permutation;
    }

    // Main function to test the algorithm
    public static void main(String[] args) {
        int[] permutation = {4, 2, 1, 3};
        SJTAlgorithm sjt = new SJTAlgorithm(permutation.length); // Initialize the SJTAlgorithm instance
        int[] nextPerm = sjt.nextPermutation(permutation);

        if (nextPerm != null) {
            System.out.println("Next permutation:");
            for (int num : nextPerm) {
                System.out.print(num + " ");
            }
        } else {
            System.out.println("No next permutation available (last permutation reached).");
        }
    }
}
