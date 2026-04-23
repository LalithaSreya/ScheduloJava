import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarManager {

    // Returns: day → number of tasks on that day
    public Map<Integer, Integer> getTasksByDate(List<Task> tasks) {
        Map<Integer, Integer> result = new HashMap<>();

        for (Task t : tasks) {
            try {
                LocalDate date = LocalDate.parse(t.getDeadline());

                int day = date.getDayOfMonth();
                result.put(day, result.getOrDefault(day, 0) + 1);

            } catch (Exception e) {
                // If deadline is empty or invalid, skip
            }
        }

        return result;
    }
}
