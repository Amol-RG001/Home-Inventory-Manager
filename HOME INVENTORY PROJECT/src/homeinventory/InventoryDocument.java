package homeinventory;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import javax.swing.ImageIcon;

class InventoryDocument implements Printable {
  public int print(Graphics g, PageFormat pf, int pageIndex) {
      Graphics2D g2D = (Graphics2D) g;
      if ((pageIndex + 1) > Home_Inventory_Manager.lastPage) {
          return NO_SUCH_PAGE;
      }
      int i, iEnd;
//here you decide what goes on each page and draw it
//header
      g2D.setFont(new Font("Arial", Font.BOLD, 14));
      g2D.drawString("Home Inventory Items - Page " + String.valueOf(pageIndex + 1),
              (int) pf.getImageableX(), (int) (pf.getImageableY() + 25));
//get starting y
      int dy = (int) g2D.getFont().getStringBounds("S",
              g2D.getFontRenderContext()).getHeight();
      int y = (int) (pf.getImageableY() + 4 * dy);
      iEnd = Home_Inventory_Manager.entriesPerPage * (pageIndex + 1);
      if (iEnd > Home_Inventory_Manager.numberEntries)
          iEnd = Home_Inventory_Manager.numberEntries;
      for (i = 0 + Home_Inventory_Manager.entriesPerPage * pageIndex; i < iEnd; i++) {
//dividing line
          Line2D.Double dividingLine = new
                  Line2D.Double(pf.getImageableX(), y, pf.getImageableX() + pf.getImageableWidth(), y);
          g2D.draw(dividingLine);
          y += dy;
          g2D.setFont(new Font("Arial", Font.BOLD, 12));
          g2D.drawString(Home_Inventory_Manager.myInventory[i].description, (int) pf.getImageableX(), y);
          y += dy;
          g2D.setFont(new Font("Arial", Font.PLAIN, 12));
          g2D.drawString("Location: " + Home_Inventory_Manager.myInventory[i].location, (int)
                  (pf.getImageableX() + 25), y);
          y += dy;
          if (Home_Inventory_Manager.myInventory[i].marked)
              g2D.drawString("Item is marked with identifying information.", (int)
                      (pf.getImageableX() + 25), y);
          else
              g2D.drawString("Item is NOT marked with identifying information.", (int)
                      (pf.getImageableX() + 25), y);
          y += dy;
          g2D.drawString("Serial Number: " +
                  Home_Inventory_Manager.myInventory[i].serialNumber, (int) (pf.getImageableX() + 25), y);
          y += dy;
          g2D.drawString("Price: $" + Home_Inventory_Manager.myInventory[i].purchasePrice + ", Purchased on: " + Home_Inventory_Manager.myInventory[i].purchaseDate, (int) (pf.getImageableX() +
                  25), y);
          y += dy;
          g2D.drawString("Purchased at: " +
                  Home_Inventory_Manager.myInventory[i].purchaseLocation, (int) (pf.getImageableX() + 25), y);
          y += dy;
          g2D.drawString("Note: " + Home_Inventory_Manager.myInventory[i].note, (int)
                  (pf.getImageableX() + 25), y);
          y += dy;
          try {
//maintain original width/height ratio
              Image inventoryImage = new
                      ImageIcon(Home_Inventory_Manager.myInventory[i].photoFile).getImage();
              double ratio = (double) (inventoryImage.getWidth(null)) / (double)
                      inventoryImage.getHeight(null);
              g2D.drawImage(inventoryImage, (int) (pf.getImageableX() + 25), y, (int) (100 *
                      ratio), 100, null);
          } catch (Exception ex) {
//have place to go in case image file doesn't open
          }
          y += 2 * dy + 100;
      }
      return PAGE_EXISTS;
  }
}

