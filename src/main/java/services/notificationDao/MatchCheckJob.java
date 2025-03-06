package services.notificationDao;

import model.Item;
import org.quartz.*;
import services.EmailSender;
import services.ItemDao;

import java.time.LocalDate;
import java.util.List;

public class MatchCheckJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("🔄 Checking for lost & found matches...");

        List<Item> lostItems = ItemDao.getLostItems();
        List<Item> foundItems = ItemDao.getFoundItems();

        for (Item lostItem : lostItems) {
            for (Item foundItem : foundItems) {
                if (isPotentialMatch(lostItem, foundItem)) {
                    Integer userId = lostItem.getUserId();
                    String email =ItemDao.getUserEmail(lostItem);
                    String message = "🔔 Possible match for your lost object: " +
                            foundItem.getName() + " found at " + foundItem.getLocation() + ".";

                    // 1️⃣ Send WebSocket notification if user is online
                    WebsocketNotification.sendNotificationToUser(userId, message);

                    // 2️⃣ Send email notification regardless of online status
                    EmailSender.sendNotifEmail(email, "Lost & Found Match", message);
                }
            }
        }
    }



    private boolean isPotentialMatch(Item lost, Item found) {
        return lost.getCategory().equals(found.getCategory()) &&
                lost.getLocation().equals(found.getLocation()) &&
                isWithinTimeRange(lost.getDatefound(), found.getDatefound());
    }

    private boolean isWithinTimeRange(LocalDate lostDate, LocalDate foundDate) {
        // Implement logic to check if found date is close to lost date
        return true;
    }
}
