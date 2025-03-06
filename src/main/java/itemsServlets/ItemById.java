package itemsServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;
import model.ItemStatus;
import model.ItemType;
import services.ItemDao;

import java.io.IOException;
import java.time.LocalDate;

public class ItemById extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer itemId=Integer.parseInt(request.getParameter( "itemId"));
        ItemDao itemDao=new ItemDao();
        Item item=itemDao.getItem(itemId);
        request.setAttribute("item", item);
        request.getRequestDispatcher("/EditItem.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer itemId= Integer.valueOf(request.getParameter("itemId"));
        String itemName=request.getParameter("itemName");
        String itemDescription=request.getParameter("itemDescription");
        String itemCategory=request.getParameter("itemCategory");
        String itemLocation=request.getParameter("itemLocation");
        String itemType=request.getParameter("itemType");
        String itemStatus="ACCEPTED";
        String itemDate=request.getParameter("itemDate");
        String itemImage=request.getParameter("itemImage");
        Integer itemUserId=Integer.parseInt(request.getParameter("itemUserId"));
        Item item=new Item(itemId,itemName,itemDescription,itemCategory,itemLocation, LocalDate.parse(itemDate), ItemType.valueOf(itemType), ItemStatus.valueOf(itemStatus),itemDate,itemImage,itemUserId);
        ItemDao itemDao=new ItemDao();
        itemDao.updateItem(item);
        getServletContext().getRequestDispatcher("/MyPosts.jsp").forward(request, response);


    }
}
