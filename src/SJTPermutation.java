public class SJTPermutation {
    private int[] data;
    private int[] dirs;

    public SJTPermutation(int n) {
        data = new int[n];
        dirs = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i + 1;  // Initialize with the first permutation
            dirs[i] = -1;     // All directions are initially left (-1)
        }
    }

    private void swap(int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }

    public boolean findNextPermutation() {
        int n = data.length;
        int mobile = -1;
        int mobileIndex = -1;

        // Find the largest mobile integer
        for (int i = 0; i < n; i++) {
            int nextIndex = i + dirs[i];
            if (nextIndex >= 0 && nextIndex < n && data[i] > data[nextIndex] && data[i] > mobile) {
                mobile = data[i];
                mobileIndex = i;
            }
        }

        if (mobile == -1) return false;  // No more permutations

        // Swap the mobile integer with the adjacent integer it is directed towards
        int swapIndex = mobileIndex + dirs[mobileIndex];
        swap(mobileIndex, swapIndex);

        // Change the directions of all integers larger than the current mobile integer
        for (int i = 0; i < n; i++) {
            if (data[i] > mobile) {
                dirs[i] = -dirs[i];
            }
        }

        return true;
    }

    public int[] getCurrentPermutation() {
        return data.clone();  // Return a copy to prevent external modification
    }
}
