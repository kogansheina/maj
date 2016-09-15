import java.awt.*;
import java.io.File;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Random;

class type_1_layout
{
    private int tileWidth;
    private int tileHeight;
    private String[] usedFiles;
    private int countTiles = 0;
    
    public type_1_layout(ArrayList<String> files, ArrayList<levelOfTiles> levels, 
                         int tileWidth, int tileHeight, int xStart, int yStart, int debug)
    {
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        
        int numberOfTiles = 154;
        /* 
           usedFiles is an array by half size of total number of tiles in the game
           - this to ensure that each image has an even number of appearances
           the array is filled - randomally - with names from the list of images
        */
        usedFiles = new String[numberOfTiles/2];
        Random rand = new Random();
        for(int t=0; t<numberOfTiles/2; t++)
        {
            int ind = t%24;//rand.nextInt(files.size());
            if (files.get(ind).length() > 1)
                usedFiles[t] = files.get(ind);
        }
        { /* level 0 */ 
            levelOfTiles level = new levelOfTiles(0,debug);
            { /* line 0, 12 columns */
                MyTile mytile;
                int high = yStart;
                lineOfTiles line = new lineOfTiles(0,0,debug);
                for(int t = 0; t < 12; t++)
                {
                    mytile = doTile(new Point(xStart + t*tileWidth,high),debug);                            
                    if (t < 11) mytile.setRight(new Location(0,0,t+1),true);
                    if (t > 0) mytile.setLeft(new Location(0,0,t-1),true);
                    if ((t >= 2) && (t <= 9)) mytile.setBottom(new Location(0,1,t-2),true); 
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* line 1, 8 columns */
                MyTile mytile;
                int high = yStart + tileHeight;
                int width = xStart + 2*tileWidth;
                lineOfTiles line = new lineOfTiles(1,0,debug);
                for(int t = 0; t < 8; t++)
                {
                    mytile = doTile(new Point(width + t*tileWidth,high),debug); 
                    if (t < 7 ) mytile.setRight(new Location(0,1,t+1),true);
                    if (t > 0) mytile.setLeft(new Location(0,1,t-1),true);
                    mytile.setBottom(new Location(0,2,t+1),true);
                    mytile.setTop(new Location(0,0,t+2),true);
                    if ((t >= 1) && (t <= 6)) 
                    {
                        mytile.visible = false;
                        mytile.setUp(new Location(1,0,t-1),true); 
                    }
                    if (t == 7) mytile.rightcrop = true;
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* line 2, 10 columns */
                MyTile mytile;
                int high = yStart + 2*tileHeight;
                int width = xStart + tileWidth;
                lineOfTiles line = new lineOfTiles(2,0,debug);
                for(int t = 0; t < 10; t++)
                {
                    mytile = doTile(new Point(width + t*tileWidth,high),debug); 
                    if (t < 9 ) mytile.setRight(new Location(0,2,t+1),true);
                    if (t > 0) mytile.setLeft(new Location(0,2,t-1),true);
                    mytile.setBottom(new Location(0,3,t+1),true);
                    if (t == 1) mytile.topcrop = true;
                    if ((t >= 1) && (t <= 8)) 
                        mytile.setTop(new Location(0,1,t-1),true);
                    if ((t > 1) && (t < 8)) 
                    {
                        mytile.setUp(new Location(1,1,t-2),true);
                        mytile.visible = false;
                    }
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* line 3, 12 columns */
                MyTile mytile;
                int high = yStart + 3*tileHeight;
                int width = xStart;
                lineOfTiles line = new lineOfTiles(3,0,debug);
                for(int t = 0; t < 12; t++)
                {
                    mytile = doTile(new Point(width + t*tileWidth,high),debug); 
                    if (t < 11 ) mytile.setRight(new Location(0,3,t+1),true);
                    if (t > 0) mytile.setLeft(new Location(0,3,t-1),true);
                    mytile.setBottom(new Location(0,4,t),true);
                    if (t == 0) mytile.topcrop = true;
                    if ((t >= 1) && (t <= 10)) mytile.setTop(new Location(0,2,t-1),true);
                    if ((t >= 3) && (t < 9)) 
                    {
                        mytile.visible = false;
                        mytile.setUp(new Location(1,2,t-3),true); 
                    }
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* line 4, 12 columns */
                MyTile mytile;
                int high = yStart + 4*tileHeight;
                int width = xStart;
                lineOfTiles line = new lineOfTiles(4,0,debug);
                for(int t = 0; t < 12; t++)
                {
                    mytile = doTile(new Point(width + t*tileWidth,high),debug); 
                    if (t < 11 ) mytile.setRight(new Location(0,4,t+1),true);
                    if (t > 0) mytile.setLeft(new Location(0,4,t-1),true);
                    if ((t >= 1) && (t <= 10)) mytile.setBottom(new Location(0,5,t-1),true);
                    mytile.setTop(new Location(0,3,t),true);
                    if ((t >= 3) && (t < 9))
                    {
                        mytile.visible = false;
                        mytile.setUp(new Location(1,3,t-3),true); 
                    }
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* line 5, 10 columns */
                MyTile mytile;
                int high = yStart + 5*tileHeight;
                int width = xStart + tileWidth;
                lineOfTiles line = new lineOfTiles(5,0,debug);
                for(int t = 0; t < 10; t++)
                {
                    mytile = doTile(new Point(width + t*tileWidth,high),debug); 
                    if (t < 9 ) mytile.setRight(new Location(0,5,t+1),true);
                    if (t == 9 ) mytile.rightcrop=true;
                    if (t > 0) mytile.setLeft(new Location(0,5,t-1),true);
                    mytile.setTop(new Location(0,4,t+1),true);
                    if ((t >= 1) && (t < 9)) mytile.setBottom(new Location(0,6,t-1),true);
                    if ((t > 1) && (t < 8))
                    {
                        mytile.visible = false;
                        mytile.setUp(new Location(1,4,t-2),true); 
                    }
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* line 6, 8 columns */
                MyTile mytile;
                int high = yStart + 6*tileHeight;
                int width = xStart + 2*tileWidth;
                lineOfTiles line = new lineOfTiles(6,0,debug);
                for(int t = 0; t < 8; t++)
                {
                    mytile = doTile(new Point(width + t*tileWidth,high),debug); 
                    if (t < 7 ) mytile.setRight(new Location(0,6,t+1),true);
                    if (t == 7 ) mytile.rightcrop=true;
                    if (t > 0) mytile.setLeft(new Location(0,6,t-1),true);
                    mytile.setBottom(new Location(0,7,t+2),true);
                    mytile.setTop(new Location(0,5,t+1),true);
                    if ((t >= 1) && (t <= 6))
                    {
                        mytile.visible = false;
                        mytile.setUp(new Location(1,5,t-1),true); 
                    }
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* line 7, 12 columns */
                MyTile mytile;
                int high = yStart + 7*tileHeight;
                int width = xStart;
                lineOfTiles line = new lineOfTiles(7,0,debug);
                for(int t = 0; t < 12; t++)
                {
                    mytile = doTile(new Point(width + t*tileWidth,high),debug); 
                    if (t < 11 ) mytile.setRight(new Location(0,7,t+1),true);
                    if (t > 0) mytile.setLeft(new Location(0,7,t-1),true);
                    if ((t >= 2) && (t <= 9)) mytile.setTop(new Location(0,6,t-2),true);
                    if (t == 1) mytile.topcrop=true; 
                    line.addTile(mytile);                             
                }
                level.addLine(line);
            }
            { /* specials - no line */
                MyTile mytile;
                int high = yStart + 4*tileHeight - tileHeight/2;
                int width = xStart - tileWidth;
                mytile = doTile(new Point(width,high),debug); 
                mytile.setRight(new Location(0,3,0),true);
                mytile.setRight(new Location(0,4,0),true);
                mytile.topcrop=true;
                level.addTile(mytile); /* the most left */
                width = xStart + 12*tileWidth;
                mytile = doTile(new Point(width,high),debug); 
                mytile.setLeft(new Location(0,3,11),true);
                mytile.setLeft(new Location(0,4,11),true);
                mytile.bottomcrop = true;
                level.addTile(mytile);  /* the right */
                mytile = doTile(new Point(width + tileWidth,high),debug); 
                mytile.setLeft(new Location(0,-1,1),true);
                level.addTile(mytile);  /* the most right */
            }
            levels.add(level);
        }
        { /* level 1 */
            levelOfTiles level = new levelOfTiles(1,debug);
            { /* 6 line, 6 columns */
                MyTile mytile;
                int width = xStart + 3*tileWidth;
                for (int l=0; l < 6; l++)
                {
                    lineOfTiles line = new lineOfTiles(l,1,debug);
                    int high = yStart + (l+1)*tileHeight;
                    for (int t=0; t < 6; t++)
                    {
                        mytile = doTile(new Point(width + t*tileWidth,high),debug);
                        if (l < 5) mytile.setBottom(new Location(1,l+1,t),true);
                        if (l > 0) mytile.setTop(new Location(1,l-1,t),true);
                        if (t > 0) mytile.setLeft(new Location(1,l,t-1),true);
                        if (t < 5) mytile.setRight(new Location(1,l,t+1),true);
                        if (((l > 0) && (l < 5)) && ((t>0) && (t<5))) 
                        {
                            mytile.setUp(new Location(2,l-1,t-1),true);
                            mytile.visible = false;
                        }
                        if ((l==0) || (l==5))
                            mytile.setDown(new Location(0,l+1,t+1),true);
                        if ((l==1) || (l==4))
                            mytile.setDown(new Location(0,l+1,t+2),true);
                        if ((l==2) || (l==3))
                            mytile.setDown(new Location(0,l+1,t+3),true);
                        line.addTile(mytile); 
                    }
                    level.addLine(line);
                }
            }
            levels.add(level);
        }
        { /* level 2 */
            levelOfTiles level = new levelOfTiles(2,debug);
            { /* 4 line, 4 columns */
                MyTile mytile;
                int width = xStart + 4*tileWidth;
                for (int l=0; l < 4; l++)
                {
                    lineOfTiles line = new lineOfTiles(l,2,debug);
                    int high = yStart + (l+2)*tileHeight;
                    for (int t=0; t < 4; t++)
                    {
                        mytile = doTile(new Point(width + t*tileWidth,high),debug);
                        if (l < 3) mytile.setBottom(new Location(2,l+1,t),true);
                        if (t > 0) mytile.setLeft(new Location(2,l,t-1),true);
                        if (t < 3) mytile.setRight(new Location(2,l,t+1),true);
                        if (l > 0) mytile.setTop(new Location(2,l-1,t),true);
                        if (((l > 0) && (l < 3)) && ((t>0) && (t<3))) 
                        {
                            mytile.setUp(new Location(3,l-1,t-1),true);
                            mytile.visible = false;
                        }
                        mytile.setDown(new Location(1,l+1,t+1),true);
                        line.addTile(mytile); 
                    }
                    level.addLine(line);
                }
            }
            levels.add(level);
        }
        { /* level 3 */
            levelOfTiles level = new levelOfTiles(3,debug);
            { /* 2 line, 2 columns */
                MyTile mytile;
                int width = xStart + 5*tileWidth;
                for (int l=0; l < 2; l++)
                {
                    lineOfTiles line = new lineOfTiles(l,3,debug);
                    int high = yStart + (l+3)*tileHeight;
                    for (int t=0; t < 2; t++)
                    {
                        mytile = doTile(new Point(width + t*tileWidth,high),debug);
                        if (l == 0) mytile.setBottom(new Location(3,l+1,t),true);
                        if (t == 1) mytile.setLeft(new Location(3,l,t-1),true);
                        if (t == 0) mytile.setRight(new Location(3,l,t+1),true);
                        if (l == 1) mytile.setTop(new Location(3,l-1,t),true);
                        mytile.setUp(new Location(4,-1,0),true);
                        mytile.setDown(new Location(2,l+1,t+1),true);
                        line.addTile(mytile); 
                    }
                    level.addLine(line);
                }
            }
            levels.add(level);
        }
        { /* level 4 1- tile */
            levelOfTiles level = new levelOfTiles(4,debug);
            int width = xStart + 5*tileWidth + tileWidth/2;
            int high = yStart + 4*tileHeight - tileHeight/2;
            MyTile mytile = doTile(new Point(width,high),debug); 
            mytile.setDown(new Location(3,0,0),true);
            mytile.setDown(new Location(3,0,1),true);
            mytile.setDown(new Location(3,1,0),true);
            mytile.setDown(new Location(3,1,1),true);
            level.addTile(mytile);  /* the most up */
            levels.add(level);
        }
    }
    /* choose an image and create a tile with it */
    private MyTile doTile(Point p, int debug)
    {
        countTiles++;
        if (countTiles == usedFiles.length/2)
            countTiles = 1;
        return new MyTile(usedFiles[countTiles-1],p,tileWidth,tileHeight, debug);
    }
    
}

class Location
{
	/* true = free */
    public int level;
    public int line;
    public int column;
    
    public Location(int l, int n, int c)
    {
        level = l;
        line = n;
        column = c;
    }
    public void info(String text)
    {
        if (text != null)
            if (text.length() > 0) System.out.println(text);
        System.out.println("Location: ( "+Integer.toString(level) +","+Integer.toString(line) +","+Integer.toString(column)+" )");
    }
    public boolean equalTo(Location l)
    {
        if ((level == l.level) && (line == l.line) && (column == l.column))
            return true;
        return false;
    }
}
class MyTile extends JComponent
{
    /* constants */
    private static final int offset = 8;
    private static final int offset1 = 10;
	private BufferedImage img = null;
    private int tileWidth, tileHeight;
    
	public Point position;
    public ArrayList<Location> left;
    public ArrayList<Location> right;
    public ArrayList<Location> top;
    public ArrayList<Location> bottom;
    public ArrayList<Location> up;
    public ArrayList<Location> down;
    public String name;
    public boolean marked = false;
    public boolean possibility = false;
    public boolean visible = true;
    public boolean topcrop = false;
    public boolean rightcrop = false;
    public boolean bottomcrop = false;
    public Location index;
    private int debug;
    
    public MyTile(String name, Point position, int tileWidth, int tileHeight, int debug)
    {
        super();
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.position = position; 
        this.name = name;
        setLocation(position.x, position.y);
	    img = loadImage(name);
        left = new ArrayList<>();
        right = new ArrayList<>();
        top = new ArrayList<>();
        bottom = new ArrayList<>();
        up = new ArrayList<>();
        down = new ArrayList<>();
        this.debug = debug;
    }
    public void setLeft(Location loc, boolean occupy)
    {
        if (occupy)
            left.add(loc);
        else
        {
            Location temp;
            if (debug > 0) index.info("From ");
            for(int y=0; y <left.size(); y++)
            {
                temp = left.get(y);
                if ((temp.level == loc.level) && (temp.line == loc.line) && (temp.column == loc.column))
                {
                    if (debug > 0) temp.info("Left Removed");
                    if (!left.remove(temp))
                        System.out.println("ERROR removing a left side");
                    break;
                }
            }
        }
    }
    public void setRight(Location loc, boolean occupy)
    {
        if (occupy)
            right.add(loc);
        else
        {
            Location temp;
            if (debug > 0) index.info("From ");
            for(int y=0; y <right.size(); y++)
            {
                temp = right.get(y);
                if ((temp.level == loc.level) && (temp.line == loc.line) && (temp.column == loc.column))
                {
                    if (debug > 0) temp.info("Right Removed");
                    if (!right.remove(temp))
                        System.out.println("ERROR removing a right side");
                    break;
                }
            }
            if (right.size() == 0)
                rightcrop = false;
        }
    }
    public void setBottom(Location loc, boolean occupy)
    {
        if (occupy)
            bottom.add(loc);
        else
        {
            Location temp;
            if (debug > 0) index.info("From ");
            for(int y=0; y <bottom.size(); y++)
            {
                temp = bottom.get(y);
                if ((temp.level == loc.level) && (temp.line == loc.line) && (temp.column == loc.column))
                {
                    if (debug > 0) temp.info("Bottom Removed");
                    if (!bottom.remove(temp))
                        System.out.println("ERROR removing a bottom side");
                    break;
                }
            }
            if (bottom.size() == 0)
                bottomcrop = false;
        }
    }
    public void setTop(Location loc, boolean occupy)
    {
        if (occupy)
            top.add(loc);
        else
        {
            Location temp;
            if (debug > 0) index.info("From ");
            for(int y=0; y <top.size(); y++)
            {
                temp = top.get(y);
                if ((temp.level == loc.level) && (temp.line == loc.line) && (temp.column == loc.column))
                {
                    if (debug > 0) temp.info("Top Removed");
                    if (!top.remove(temp))
                        System.out.println("ERROR removing a top side");
                    break;
                }
            }
            if (top.size() == 0)
                topcrop = false;
        }
    }
    public void setUp(Location loc, boolean occupy)
    {
        if (occupy)
            up.add(loc);
        else
        {
            Location temp;
            if (debug > 0) index.info("From ");
            for(int y=0; y <up.size(); y++)
            {
                temp = up.get(y);
                if ((temp.level == loc.level) && (temp.line == loc.line) && (temp.column == loc.column))
                {
                    if (debug > 0) temp.info("Up Removed");
                    if (!up.remove(temp))
                        System.out.println("ERROR removing a up side");
                    break;
                }
            }
        }
    }
    public void setDown(Location loc, boolean occupy)
    {
        if (occupy)
            down.add(loc);
    }
	private BufferedImage loadImage(String name) 
    {
        BufferedImage img = null;
        try 
        {
            img = ImageIO.read(new File(name));
        } 
        catch (Exception e) 
        {
            System.out.println("cannot read image : "+name);
        }
        return img;
    } 
    public void info(String text)
    {
        System.out.println("\n######");
        if (text.length() > 0) System.out.println(text);
        //System.out.println("Name="+name+"\tvisible="+Boolean.toString(visible));
        //System.out.println("Position\tx="+Integer.toString(position.x) +" y="+Integer.toString(position.y));
        index.info(null);
        System.out.println("Left "+Integer.toString(left.size()));
        for (int i=0; i < left.size(); i++)
            left.get(i).info(null);
        System.out.println("Right "+Integer.toString(right.size()));
        for (int i=0; i < right.size(); i++)
            right.get(i).info(null);
        System.out.println("Top "+Integer.toString(top.size()));
        for (int i=0; i < top.size(); i++)
            top.get(i).info(null);
        System.out.println("Bottom "+Integer.toString(bottom.size()));
        for (int i=0; i < bottom.size(); i++)
            bottom.get(i).info(null);
        System.out.println("Up "+Integer.toString(up.size()));
        for (int i=0; i < up.size(); i++)
            up.get(i).info(null);
        System.out.println("Down "+Integer.toString(down.size()));
        for (int i=0; i < down.size(); i++)
            down.get(i).info(null);
        System.out.println("@@@@@@\n");
    }
    /*
     draw as 3D margin
    */
    private void margin(Graphics g, Color color, Color border, Point p0, Point p1, Point p2, Point p3)
    {
        g.setColor(color);	
        int[] a = {p0.x,p1.x,p2.x,p3.x};
        int[] b = {p0.y,p1.y,p2.y,p3.y};					
        g.fillPolygon(a, b, 4);
        g.setColor(border);	
        g.drawPolygon(a, b, 4);
    }
    /*
     set coordinates for right side margin
    */
    private void rightSide(Graphics g, Point position)
    {
        Point p0,p1,p2,p3;

        p0 = new Point(position.x + tileWidth, position.y);
        p1 = new Point(p0.x,p0.y + tileHeight);
        p2 = new Point(p1.x + offset1,p1.y - offset1);
        if (rightcrop)
            p3 = new Point(p0.x + offset1,p2.y - tileHeight + offset1);
        else
            p3 = new Point(p0.x + offset1,p2.y - tileHeight);
        margin(g,Color.lightGray,Color.black,p0,p1,p2,p3);
    }
    /*
     set coordinates for top side margin
    */
    private void topSide(Graphics g, Point position)
    {
        Point p0,p1,p2,p3;

        p0 = new Point(position);
        p1 = new Point(p0.x + tileWidth, p0.y);
        if (topcrop)
            p2 = new Point(p1.x,p1.y - offset1);
        else
            p2 = new Point(p1.x + offset1,p1.y - offset1);
        p3 = new Point(p0.x + offset1,p2.y);
        margin(g,Color.lightGray,Color.black,p0,p1,p2,p3);
    }
    /*
     set coordinates for left side margin
    */
    private void leftSide(Graphics g, Point position)
    {
        Point p0,p1,p2,p3;

        p0 = new Point(position);
        p1 = new Point(p0.x - offset, p0.y + offset);
        p2 = new Point(p1.x,          p1.y + tileHeight);
        p3 = new Point(p0.x,          p0.y + tileHeight);
        margin(g,Color.yellow,Color.lightGray,p0,p1,p2,p3);
        p0 = new Point(p1);
        p3 = new Point(p2);
        p1 = new Point(p0.x - offset, p0.y + offset);
        p2 = new Point(p1.x,          p1.y + tileHeight); 
        margin(g,Color.green,Color.lightGray,p0,p1,p2,p3);
    }
    /*
     set coordinates for bottom side margin
    */
    private void bottomSide(Graphics g, Point position)
    {
        Point p0,p1,p2,p3;

        p0 = new Point(position.x,       position.y + tileHeight);
        if (bottomcrop)
            p1 = new Point(p0.x,    p0.y + offset);
        else
            p1 = new Point(p0.x - offset,    p0.y + offset);
        p2 = new Point(p1.x + tileWidth, p1.y);
        p3 = new Point(p0.x + tileWidth, p2.y- offset);
        margin(g,Color.yellow,Color.lightGray,p0,p1,p2,p3);
        p0 = new Point(p1);
        p3 = new Point(p2);
        if (bottomcrop)
            p1 = new Point(p0.x,    p0.y + offset);
        else
            p1 = new Point(p0.x - offset,    p0.y + offset);
        p2 = new Point(p1.x + tileWidth, p1.y); 
        margin(g,Color.green,Color.lightGray,p0,p1,p2,p3);
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        setBackground(Color.white);
        if (visible == true)
        {
            float opacity = 1.0f;
            if (marked)
                opacity = 0.5f;
            else if (possibility)
                opacity = 0.3f;
            Graphics2D g2 = (Graphics2D)g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g.drawImage(img, position.x, position.y, null);
            if (possibility)
            {
                Color c = g.getColor();
                g.setColor(Color.pink);
                g.fillRect(position.x, position.y, tileWidth, tileHeight);
                g.setColor(c);
            }
            if (left.size() == 0)
                leftSide(g, position);
            if (bottom.size() == 0)
                bottomSide(g, position);
            if (top.size() == 0)
                topSide(g, position);
            if (right.size() == 0)
                rightSide(g, position);
            g.drawRect(position.x, position.y, tileWidth, tileHeight);
        }
    }
}
/* a line is an array of tiles */
class lineOfTiles
{
    public ArrayList<MyTile> tiles;
    public int index;
    private int level;
    private int debug;  
    
    public lineOfTiles(int ind, int level, int debug) 
    {
        tiles = new ArrayList<>();
        index = ind;
        this.level = level;
        this.debug = debug;
    }
    public int delTile(MyTile tile) 
    {
        tiles.remove(tile); 
        return tiles.size();
    }
    public int addTile(MyTile tile) 
    {
        tile.index = new Location(level,index,tiles.size());
        tiles.add(tile);
        if (debug > 1) tile.info("");
         
        return tiles.size();
    }
    public MyTile getTile(int ind)
    {
        for (int i=0; i < tiles.size(); i++)
            if (ind == tiles.get(i).index.column)
                return tiles.get(i);
        return null;
    }
}
/* a level is an array of lines
   there are also tiles which are not part of a line */
class levelOfTiles
{
    public ArrayList<lineOfTiles> lines;
    public ArrayList<MyTile> tiles;
    public int index;
    private int debug;  
    
    public levelOfTiles(int ind, int debug) 
    {
        lines = new ArrayList<>();
        tiles = new ArrayList<>();
        index = ind;
        this.debug = debug;
    }
    public int delTile(MyTile tile) 
    {
        tiles.remove(tile); 
        return tiles.size();
    }
    public int delLine(lineOfTiles line) 
    {
        lines.remove(line); 
        return lines.size();
    }
    public int addLine(lineOfTiles line) 
    {
        lines.add(line);                
        return lines.size();
    }
    public lineOfTiles getLine(int ind)
    {
        for (int i=0; i < lines.size(); i++)
            if (ind == lines.get(i).index)
                return lines.get(i);
        return null;
    }
    public int addTile(MyTile tile) 
    {
        tile.index = new Location(index,-1,tiles.size());
        tiles.add(tile); 
        if (debug > 1) tile.info("");
        
        return tiles.size();
    }
    public MyTile getTile(int ind)
    {
        for (int i=0; i < tiles.size(); i++)
            if (ind == tiles.get(i).index.column)
                return tiles.get(i);
        return null;
    }
}
/* store the first marked tile */
class Marked
{
    public MyTile tile;
    public levelOfTiles level;
    public lineOfTiles line;
    
    public Marked(MyTile tile, levelOfTiles level, lineOfTiles line)
    {
        this.tile = tile;
        this.level = level;
        this.line = line;
    }
}
public class maj extends JPanel implements MouseListener 
{	
    /* data base */
    private ArrayList<String> files = new ArrayList<>(); // list of tfiles for tiles' images
    private ArrayList<levelOfTiles> levels = new ArrayList<>();  
    private ArrayList<MyTile> possibities = new ArrayList<>();  
    /* parameters */
    private int type;
    /* calculated from images */
    private int tileWidth, tileHeight;
    /* coordiantes of the first tile to be drawn */
    private int xStart, yStart;
    /* store the first marked tile */
    private Marked marked = null;
    private int debug = 0;
	
	public maj(String[] args,int screenW, int screenH)
	{    
        if (initFiles(args)) /* read file of tiles images */
        {
            xStart = (screenW - 15*tileWidth)/2 + 100;
            yStart = (screenH - 9*tileHeight)/2 + 100;
            if (args.length > 2)
                debug = Integer.valueOf(args[2]).intValue();
            initLayout("begin");   /* create the layout, according to input parameter */
            addMouseListener(this); 
        }
        JButton btnh = createHelp();
        btnh.setLocation(screenW+50,screenH - 50);
        this.add(btnh);
        JButton btnr = restart();
        btnr.setLocation(screenW+100,screenH - 50);
        this.add(btnr);
	}
    private boolean initFiles(String[] args)
    {
        String filename = args[0];
        try 
        {
            String line = null;
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(filename);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String current = new java.io.File( "." ).getCanonicalPath();
            while((line = bufferedReader.readLine()) != null) 
            {
                files.add(current+"/img/"+line);
            }   
            // Always close files.
            bufferedReader.close(); 
            if (files.size() == 0)
            {
                System.out.println("File '" + filename + "' is empty"); 
                return false;                 
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'"); 
            return false;               
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'"); 
            return false;                 
        }
        try
        {
            /* suppose all images have the same size - eat the first one */
            BufferedImage img = ImageIO.read(new File(files.get(0))); 
            tileWidth = img.getWidth();
            tileHeight = img.getHeight();
        }
        catch (Exception e) 
        {
            System.out.println("cannot read image : "+files.get(0));
            return false;                 
        }
        type = Integer.valueOf(args[1]).intValue(); /* layout number */
        return true;
    }
    /*
     check the level is empty - if yes, remove it and check if there are more
    */
    private void endLevel(levelOfTiles level)
    {
        if ((level.lines.size() == 0) && (level.tiles.size() == 0))
            levels.remove(level);
        if (levels.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "  GATA !!!!!!!!!");
            //System.out.println("gata !!!!!!!!!!");
            //System.exit(0);
        }
    }
    /*
     called from mouse listener - if the clecked tile is visible
     if it is up free and, at least, one other side is free, check if it is the first marked tile
     if yes - store it and repaint it
     otherwise - it is the second click - check if it has the same image as the marked one
     if yes - delete both tiles : the current and the marked one
     update the freedom of adjiacent tiles for both
    */
    private void foundClicked(MyTile mytile, levelOfTiles level, lineOfTiles line)
    {
        if (freeTile(mytile))
        {
            if (marked == null)
            {
                marked = new Marked(mytile,level,line);
                mytile.marked = true;
                repaint(mytile.position.x,mytile.position.y,tileWidth,tileHeight);
            }
            else
            {
                if (mytile.name.equals(marked.tile.name) && (!mytile.index.equalTo(marked.tile.index)))
                {
                    /* delete current tile */
                    updateNeighbors(mytile);
                    if (line != null)
                    {
                        if (line.delTile(mytile) == 0)
                        {
                            if (level.delLine(line) == 0)
                                endLevel(level);
                        }
                    }
                    else
                    {
                        if (level.delTile(mytile) == 0)
                            endLevel(level);
                    }
                    /* end delete current tile */
                    /* delete marked tile */
                    levelOfTiles levelm = marked.level;
                    updateNeighbors(marked.tile);
                    if (marked.line != null)
                    {
                        lineOfTiles linem = marked.line;
                        if (linem.delTile(marked.tile) == 0)
                                endLevel(levelm);
                    }
                    else
                    {
                        if (levelm.delTile(marked.tile) == 0)
                            endLevel(levelm);
                    }
                    /* end delete marked tile */
                    marked = null;
                    repaint();
                }
                else
                {
	                /* remove the opaque from the previous tile */
	                marked.tile.marked = false;
	                repaint(marked.tile.position.x,marked.tile.position.y,tileWidth,tileHeight); 
	                /* mark the new one */
                	marked = new Marked(mytile,level,line);                                           
                	mytile.marked = true;                                                             
                	repaint(mytile.position.x,mytile.position.y,tileWidth,tileHeight);                
            	}
            }
        }
    }
    private MyTile getTileToUpdate(Location loc)
    {
        levelOfTiles level = levels.get(loc.level);
        if (loc.line >=0)
        {
            lineOfTiles temp = level.getLine(loc.line);
            if (temp == null) 
            {
                if (debug > 0) System.out.println("getTileToUpdate: line null");
                return null;
            }
            return temp.getTile(loc.column);
        }
        return level.getTile(loc.column);
    }
    /* input - the tile to be deleted, actual level, actual line */
    private void updateNeighbors(MyTile tile)
    {
        /* if side is not free, update to free the corespondent side of the adjacent tile */
        /* left not free, set the right side of the previous tile in line */
        MyTile temptile;
        if (debug > 0) System.out.println(" update ( "+
                           Integer.toString(tile.index.level)+","+Integer.toString(tile.index.line)+
                           ","+Integer.toString(tile.index.column)+" )");
        for (int i=0; i<tile.left.size(); i++)
        {
            /* for each tile in my left */
            Location loc = tile.left.get(i);
            temptile = getTileToUpdate(loc);
            /* remove me from the right list */
            temptile.setRight(tile.index,false);
        }
        
        for (int i=0; i<tile.right.size(); i++)
        {
            Location loc = tile.right.get(i);
            temptile = getTileToUpdate(loc);
            temptile.setLeft(tile.index,false);
        }
        
        for (int i=0; i<tile.top.size(); i++)
        {
            Location loc = tile.top.get(i);
            temptile = getTileToUpdate(loc);
            temptile.setBottom(tile.index,false);
        }
        for (int i=0; i<tile.bottom.size(); i++)
        {
            Location loc = tile.bottom.get(i);
            temptile = getTileToUpdate(loc);
            temptile.setTop(tile.index,false);
        }
        for (int i=0; i<tile.down.size(); i++)
        {
            Location loc = tile.down.get(i);
            temptile = getTileToUpdate(loc);
            temptile.setUp(tile.index,false);
            if (temptile.up.size() == 0)
                temptile.visible = true;
            else
            {
                if (debug > 0) 
                {
                    for (int t=0; t<temptile.up.size(); t++)
                    {
                        Location r = temptile.up.get(t);
                        r.info("UP");
                    }
                }
            }
        }
            
        setPossibilities();
    }
    /* 
       click tile - run along the entire data base and check coordinates
       if the tile is found and it is visible, proceed
       the tile is searched into direct list and in level list
    */
    public void mouseClicked(MouseEvent e)
    {
        boolean found = false;
        MyTile mytile = null;

        for (int i=0; (i<levels.size()) && !found; i++) 
        {
            levelOfTiles level = levels.get(i);
            for (int j=0; (j<level.lines.size()) && !found; j++) 
            {
                lineOfTiles line = level.lines.get(j);
                for (int k=0; (k<line.tiles.size()) && !found; k++) 
                {
                    mytile = line.tiles.get(k);
                    if ((e.getX() >= mytile.position.x) & (e.getX() <= mytile.position.x + tileWidth))
                    {
                        if ((e.getY() >= mytile.position.y) & (e.getY() <= mytile.position.y + tileHeight))
                        {
                            if (mytile.visible && (mytile.up.size() == 0))
                            {                            
	                            found = true;
                                foundClicked(mytile,level,line);
                            }
                        }
                    }
                }
            }
        }

        for (int i=0; (i<levels.size()) && !found; i++) 
        {
            levelOfTiles level = levels.get(i);
            for (int k=0; (k<level.tiles.size()) && !found; k++) 
            {
                mytile = level.tiles.get(k);
                if ((e.getX() >= mytile.position.x) & (e.getX() <= mytile.position.x + tileWidth))
                {
                    if ((e.getY() >= mytile.position.y) & (e.getY() <= mytile.position.y + tileHeight))
                    {
                        if (mytile.visible && (mytile.up.size() == 0))
                        {
	                        found = true;
                            foundClicked(mytile,level,null);
                        }
                    }
                }
            }
        }

    }
    public void mouseReleased(MouseEvent event){ }
    public void mousePressed(MouseEvent event){ }
    public void mouseEntered(MouseEvent event){ }
    public void mouseExited(MouseEvent event){ }			

    /*
     create layout of the game
    */
    private void initLayout(String t)
    {
        System.out.println(t);
        if (type == 1)
        {
            levels.clear();
            possibities.clear();
            new type_1_layout(files,levels,tileWidth,tileHeight,xStart,yStart,debug);
            setPossibilities();
        }
    }
    /*
     paint the panel - all levels
    */
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        setBackground(Color.white);
        
        for (int i=0; i<levels.size(); i++) 
        {
            levelOfTiles level = levels.get(i);
            for (int j=0; j<level.lines.size(); j++) 
            {
                lineOfTiles line = level.lines.get(j);
                for (int k=0; k<line.tiles.size(); k++) 
                {
                    line.tiles.get(k).paintComponent(g);
                }
            }
        } 
        for (int i=0; i<levels.size(); i++) 
        {
            levelOfTiles level = levels.get(i);
            for (int k=0; k<level.tiles.size(); k++) 
            {
                level.tiles.get(k).paintComponent(g);
            }
        }
    }
    private boolean freeTile(MyTile mytile)
    {
        if (mytile.up.size()==0)
        {
            if ((mytile.left.size()==0) || (mytile.right.size()==0))
                return true;
            return false;
        }
        return false;
    }
    /*
    clear the list of possibilities and build it again
    */
    private void setPossibilities()
    {
        ArrayList<MyTile> temp = new ArrayList<>();  
        possibities.clear();
        for (int i=0; i<levels.size(); i++) 
        {
            levelOfTiles level = levels.get(i);
            for (int j=0; j<level.lines.size(); j++) 
            {
                lineOfTiles line = level.lines.get(j);
                for (int k=0; k<line.tiles.size(); k++) 
                {
                    MyTile mytile = line.tiles.get(k);
                    mytile.possibility = false;
                    if (freeTile(mytile))
                        temp.add(mytile);
                }
            }
        }
        for (int i=0; (i<levels.size()); i++) 
        {
            levelOfTiles level = levels.get(i);
            for (int k=0; k<level.tiles.size(); k++) 
            {
                MyTile mytile = level.tiles.get(k);
                mytile.possibility = false;
                if (freeTile(mytile))
                    temp.add(mytile);
            }
        }
        for (int i=0; i < temp.size(); i++)
        {
            String s = temp.get(i).name;
            for (int j=0; j < temp.size(); j++)
            {
                if (i==j) continue;
                if (s.equals(temp.get(j).name)) 
                {
                    possibities.add(temp.get(j));                    
                    boolean found = false;
                    for (int k=0; (k < possibities.size()) && !found; k++)
                    {
                        if (possibities.get(k) == temp.get(i))
                            found = true;
                    }
                    if (!found)
                        possibities.add(temp.get(i));
                    temp.remove(j);
                    break;
                }
            }
        }
        if (possibities.size()==0)
        {
            JOptionPane.showMessageDialog(null, "  NO more posibilities !!!!!!!!!");
            //System.out.println("NO more posibilities");
            //System.exit(0);
        }
        else System.out.println("Posibilities = "+Integer.toString(possibities.size()));
    }
    private JButton createHelp()
    {
        JButton askButton = new JButton("Help");
        askButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            // when the button is pressed
            for (int i=0; i < possibities.size(); i++)
            {
                MyTile tile = possibities.get(i);
                //tile.info("possibities");
                tile.possibility = true;
                repaint(tile.position.x,tile.position.y,tileWidth,tileHeight);
            }
          }
        });
        return askButton;
    }
    private JButton restart()
    {
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            // when the button is pressed
              initLayout("Restart");   /* create the layout, according to input parameter */
            //System.out.println("Restart");
          }
        });
        return restartButton;
    }
    /*
    main - create frame and game instance
    */
    public static void main(String[] args)
    {   
        if (args.length < 2)
        {
            System.out.println("Not enough arguments");
            System.out.println("Parameters: ");
            System.out.println("\tname of the file with images for tiles");
            System.out.println("\ttype of puzzle");
            System.out.println("\t\t1");
            System.out.println("\t\t2");
            return;
        }
        
        JFrame frame = new JFrame("My Mahjong");
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        int hight = (int)(d.height*0.90);
        int width = (int)(d.width*0.90);
        frame.setSize(width,hight);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(new maj(args,width,hight));
        frame.setVisible(true);
    }
}

