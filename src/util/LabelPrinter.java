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

        g2d.setFont(new Font("Nirmala UI", Font.BOLD, 12));
        g2d.drawString("වර්ගය: " + productName, 10, 20);

        g2d.setFont(new Font("Nirmala UI", Font.PLAIN, 10));
        g2d.drawString("ප්‍රමාණය : " + quantity , 10, 35);
        g2d.drawString("මිල: රු." + price + "/-", 10, 50);

        // MFD & EXP
        g2d.setFont(new Font("Nirmala UI", Font.PLAIN, 10));
        g2d.drawString("|  නි. දිනය : " + packedDate, 100, 35);
        g2d.drawString("|  ක.ඉ.දිනය : " + expireDate, 100, 50);
        g2d.drawString("____________________________________________________", 10, 51);
        g2d.drawString("ප්‍රේමසිරි ට්‍රේඩ් සෙන්ටර්,", 10, 63);
        g2d.drawString("උනගස්වැව, අනුරාදපුර.", 10, 75);
        g2d.drawString("077 41 49 513  |  071 17 47 559", 10, 87);
        g2d.drawString("ලියාපදින්චි අංකය : NCP001783", 10, 99);
        

        g2d.drawRect(5, 5, 220, 100); // border

        return PAGE_EXISTS;
    }

    public void printLabel() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
