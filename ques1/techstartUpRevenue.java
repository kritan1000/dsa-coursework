
import java.util.*;

public class techstartUpRevenue {

    static class Project {
        int investment;
        int revenue;

        public Project(int investment, int revenue) {
            this.investment = investment;
            this.revenue = revenue;
        }
    }

    public static int maximizeCapital(int k, int c, int[] revenues, int[] investments) {
        int n = revenues.length;

        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            projects.add(new Project(investments[i], revenues[i]));
        }

        projects.sort(Comparator.comparingInt(p -> p.investment));

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;

        for (int j = 0; j < k; j++) {
            while (i < n && projects.get(i).investment <= c) {
                maxHeap.offer(projects.get(i).revenue);
                i++;
            }

            if (maxHeap.isEmpty()) break;

            c += maxHeap.poll();
        }

        return c;
    }

    public static void main(String[] args) {
        int[] revenues1 = {2, 5, 8};
        int[] investments1 = {0, 2, 3};
        System.out.println("Max Capital: " + 
            maximizeCapital(2, 0, revenues1, investments1));

        int[] revenues2 = {3, 6, 10};
        int[] investments2 = {1, 3, 5};
        System.out.println("Max Capital: " + 
            maximizeCapital(3, 1, revenues2, investments2));
    }
}