package util;

import java.awt.*;
import java.awt.print.*;
import java.util.Date;

public class LabelPrinter implements Printable {

    private String productName;
    private String quantity;
    private double price;
    private String packedDate;
    private String expireDate;

    public LabelPrinter(String productName, String quantity, double price, String mfdStr, String expStr) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.packedDate = mfdStr;
        this.expireDate = expStr;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Border rectangle (slightly smaller than page size)
        g2d.drawRect(1, 1, 161, 111);

        // Fonts
        Font bold12 = new Font("Nirmala UI", Font.BOLD, 10);
        Font plain10 = new Font("Nirmala UI", Font.PLAIN, 8);

        int y = 15;

        // Product name
        g2d.setFont(bold12);
        g2d.drawString("වර්ගය: " + productName, 5, y);

        // Quantity & Price
        y += 15;
        g2d.setFont(plain10);
        g2d.drawString("නි.දි : " + packedDate, 5, y);
        g2d.drawString("ප්‍රමාණය: " + quantity, 85, y);
        

        // Packed & Expire dates
        y += 15;
        g2d.drawString("ක.ඉ.දි : " + expireDate, 5, y);
        g2d.drawString("මිල: රු." + price + "/-", 85, y);

        // Separator line
        y += 5;
        g2d.drawLine(5, y, 155, y);

        // Footer info
        y += 12;
        g2d.drawString("ප්‍රේමසිරි ට්‍රේඩ් සෙන්ටර්,", 5, y);
        y += 12;
        g2d.drawString("උනගස්වැව, අනුරාදපුර.", 5, y);
        y += 12;
        g2d.drawString("0770127996", 5, y);
        y += 12;
        g2d.drawString("ලියාපදිංචි අංකය : NCP001783", 5, y);

        return PAGE_EXISTS;
    }

    public void printLabel() {
        PrinterJob job = PrinterJob.getPrinterJob();

        // ---- Set custom label size (57.5mm x 40mm) ----
        Paper paper = new Paper();
        double width = 163;   // points (57.5 mm)
        double height = 105;  // points (40 mm)
        paper.setSize(width, height);
        paper.setImageableArea(0, 0, width, height);

        PageFormat pf = job.defaultPage();
        pf.setPaper(paper);

        job.setPrintable(this, pf);

        // ---- Show print dialog and print ----
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
