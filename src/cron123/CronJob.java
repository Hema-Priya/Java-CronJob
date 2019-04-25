
package cron123;

/*import java.io.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;


public class CronJob{
	public static void main(String[] args)
	{
	try
	{
	FileInputStream fis=new FileInputStream("E:\\Workshop\\eclipse\\cron\\src\\cron123\\123.docx");
	   XWPFDocument document = new XWPFDocument(fis);
       List<XWPFParagraph> paragraphs = document.getParagraphs();
	String[] fileData=extractor.getParagraphText();
	
	for(String paragraph:fileData)
	{
	System.out.println(paragraph);
	}
	}
	catch(Exception exep)
	{
	exep.printStackTrace();
	}
	}
}*/

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.lang.String;


import javax.swing.text.DateFormatter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
 
public class CronJob
{
	static int flag=0;
public static void main(String[] args) {
            try {
                File file = new File("E:\\Workshop\\eclipse\\cron\\src\\cron123\\123.docx");
                FileInputStream fis = new FileInputStream(file);

                XWPFDocument document = new XWPFDocument(fis);
                List<XWPFParagraph> paragraphs = document.getParagraphs();

           Date x=new Date();
                MyTask k=new MyTask(paragraphs,x);
              
              Timer t = new Timer();          
            	  t.scheduleAtFixedRate(k, 0, 20000);
            	  
              //  fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
}
}

class MyTask extends TimerTask{
	static int flag1=0;
	List<XWPFParagraph> paragraphs;
	String[] l;
	 LocalTime o = LocalTime.of(00, 00,00);
String l1,g;
Date x;
	MyTask(List<XWPFParagraph> j,Date r)
	{
		paragraphs=j;
			x=r;	
	}
public void run() {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");  
	Calendar c = Calendar.getInstance();
	c.setTime(new Date());
	String output = sdf.format(c.getTime());
    SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
    String r=sdf.format(c.getTime());
    String z=sdf.format(x);
    if(!(r.equals(z)))
	  { CronJob.flag=0;
	  x=c.getTime();
	  System.out.println("lock");
	  try {
			Thread.sleep(24*60*60*1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
	  }
    for(XWPFParagraph para : paragraphs) {
    	
    	 String e=para.getText();
    	 l=e.split(" ");
    	 if(l.length>1)
    	 {
    		 l1=l[1];
    		 g=l[2];
    	 
     	Date dateValue=new Date(); 
           try {
			dateValue= input.parse(l1);
           }
           
           catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           c.setTime(dateValue);
           c.add(Calendar.DAY_OF_MONTH, -1);
     	  String h=sdf.format(c.getTime());
     	  
     	 

 LocalTime localTime = LocalTime.of(23, 30,00);
 LocalTime time = LocalTime.now();

if(flag1<paragraphs.size())
{
	CronJob.flag=0;
}
 if(h.equals(output)&& time.isAfter(localTime)&&time.isBefore(o)&&CronJob.flag==0)
 { 
	System.out.println("Mail sent to"+l1);
Mail x=new Mail(g);

x.performTask();
	CronJob.flag=1;
 }  	
    }
    	 flag1++;
            } 
}

	}



