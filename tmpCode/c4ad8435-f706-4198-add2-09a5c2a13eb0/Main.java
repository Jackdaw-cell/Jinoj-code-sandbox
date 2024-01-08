
public class Main {

    public static void main(String[] args) {
        if (args.length<=2){
            System.out.println("");
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (String i:
                args) {
            list.add(Integer.parseInt(i));
        }
        Integer target = list.remove(list.size() - 1);
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        int[] sum = twoSum(arr, target);
        for (int item:
                sum) {
            System.out.print(item+" ");
        }
    }
   

 public static int[] twoSum(int[] nums, int target) {
    int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
 }
}