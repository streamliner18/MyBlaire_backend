package com.myBlaire.util;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.beans.BeanUtils;

/**
 * 此类提供对日期和日期字符串的操作
 * @author Administrator
 *
 */
public class DateUtil {
	
	/**
	 * 定义日期比较类型.<br />
	 * <p>DAY表示比较两日期相差多少天<br />
	 * MONTH表示比较两日期相差多少月<br />
	 * YEAR表示比较两日期相关多少年</p>
	 */
	public static enum CompareType {
		DAY, MONTH, YEAR
	}
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat formatYMDHSMS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/**
	 * 获取当前时间的字符串(格式:yyyy-MM-dd HH:mm:ss)
	 * @return 当前时间的字符串
	 */
	public static String getCurrentTimeYMDHSM(){
		String str = format.format(new Date());
		return str;
	}
	
	
	/**
	 * 获取当前时间的date(格式:yyyy-MM-dd HH:mm:ss)
	 * @return 当前时间的date
	 */
	public static Date formatDateYMDHSM(String dateStr){
		Date date=null;
		try {
			date=format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * 获取当前时间的字符串(格式:yyyyMMddHHmmssSSS)
	 * @return 当前时间的字符串
	 */
	public static String getCurrentTimeYMDHSMS(){
		String str = formatYMDHSMS.format(new Date());
		return str;
	}
	
	/**
	 * 获取当前时间的字符串(格式:yyyy-MM-dd HH:mm:ss)
	 * @return 当前时间的字符串
	 */
	public static String getCurrentTime(){
		String str = format.format(new Date());
		return str;
	}
	
	/**
	 * 转换时间为字符串  格式yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String dateConvertYMDHMS(Date date){
		String str = format.format(date);
		return str;
	}
	/**
	 * 获取当前时间的字符串（格式：yyyy-MM-dd）
	 * @return 当前时间的字符串
	 */
	public static String getCurrentTimeYMD(){
		String str = format1.format(new Date());
		return str;
	}
	/**
	 * 将指定日期字符串转换为yyyy-MM-dd HH:mm:ss格式的日期
	 * @param dateStr 待转换的日期字符串
	 * @return 转换成功则返回转换后的日期，否则返回null;
	 */
	public static Date parseDateYMDHSM(String dateStr){
		Date date=null;
		try {
			date = format.parse(dateStr);
	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将指定日期字符串转换为yyyy-MM-dd格式的日期
	 * @param dateStr 待转换的日期字符串
	 * @return 转换成功则返回转换后的日期，否则返回null;
	 */
	public static Date parseDateYMD(String dateStr){
		Date date = null;
		try {
			date = format1.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 对输入的@{link Date}格式化为指的时间格式.
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String format(Date date, String dateFormat) {
		SimpleDateFormat formatdate = new SimpleDateFormat(dateFormat);
		String temp = formatdate.format(date);
		return temp;
	}
	
	/**
	 * 将日期格式化成指定格式
	 * @param resouce 要操作的日期
	 * @param dateFormat 日期格式（比如：yyyy-MM-dd HH:mm:ss）
	 * @return 如果操作失败返回null，否则返回格式化后的日期
	 */
	public static Date formatDate(Date resouce,String dateFormat){
		SimpleDateFormat formatdate = new SimpleDateFormat(dateFormat);
		String temp = format.format(resouce);
		Date date = null;
		try {
			date = formatdate.parse(temp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将日期格式化成yyyy-MM-dd格式的字符串
	 * @param date 待转换的日期
	 * @return 转换后的字符串
	 */
	public static String formatDateYMD(Date date){
		String str = format1.format(date);		
		return str;
	}
	

	 /**
     * 获取指定日期day天前的日期
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay,Integer day) {//可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int currday = c.get(Calendar.DATE);
        c.set(Calendar.DATE, currday - day);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }
    
    
	
    /**
    * 获取指定日期day天前的日期
    * 
    * @param specifiedDay
    * @return
    * @throws Exception
    */
   public static String getSpecifiedDayBeforeYMDHMD(String specifiedDay,Integer day) {//可以用new Date().toLocalString()传递参数
       Calendar c = Calendar.getInstance();
       Date date = null;
       try {
           date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
       } catch (ParseException e) {
           e.printStackTrace();
       }
       c.setTime(date);
       int currday = c.get(Calendar.DATE);
       c.set(Calendar.DATE, currday - day);

       String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
               .getTime());
       return dayBefore;
   }
   

    /**
     * 获取指定日期day天后的日期
     * 
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay,Integer day) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int currday = c.get(Calendar.DATE);
        c.set(Calendar.DATE, currday + day);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }

	/**
	 * 用于复制对象属性，创建新对象
	 * @param oldObj
	 * @param newObj
	 */
	public static void copyProperties(Object oldObj,Object newObj)
	{

		BeanUtils.copyProperties(oldObj, newObj);
		
	}
	
	/**
	 * 乱码时用于转码
	 * @param str
	 * @return
	 */
	public static String subStr(String str){
		try {
			str=new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 获取json配置对象
	 * @return
	 */
	public static JsonConfig getJsonConfig(String []str)
	{
		JsonConfig jsonconfig=new JsonConfig();
		if(str!=null)
		{
			jsonconfig.setExcludes(str);
		}
		
		//设置时间自动转换
		jsonconfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
		  private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		    return  value == null ?"" : sd.format(value);
		 }
		  
		  public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		    return null;
		  }
		});
		
		return jsonconfig;
	}
	
	
	
	
//	public static String getOrderSn()
//	{
//		
//		/*return getCurrentTimeYMDHSMS()+VerifyCode.generateTextCode(
//				VerifyCode.TYPE_NUM_ONLY, VerifyCode.VERIFYCODE_NUM,
//		"0oOilJI1");*/
//		//从新的规则表中获取值
//		return SequenceUtils.getOrderSn();
//	}
//	
	/**
	 * 比较两个{@link Date}按{@link CompareType}相差的数字.
	 * 
	 * @param  date1 需要比较的时间
	 * @param  date2 被比较的时间
	 * @param  compareType 比较的类型{@link CompareType}
	 * @return 返回按{@link CompareType}相差的数字
	 */
	public static int compareDate(Date date1, Date date2, CompareType compareType) {
		/*int n = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		while (!c1.after(c2)) {// 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); 
			//System.out.println(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (compareType == CompareType.MONTH) {
				c1.add(Calendar.MONTH, 1);	// 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1);// 比较天数，日期+1
			}
		}
		n = n - 1;
		if (compareType == CompareType.YEAR) {
			n = (int) n / 365;
		}
		return n;*/
		int num = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		switch (compareType) {
			case YEAR:
				int year1 = c1.get(Calendar.YEAR);
				int year2 = c2.get(Calendar.YEAR);
				num = year2 - year1;
				break;
			case MONTH:
				int month1 = c1.get(Calendar.MONTH);
				int month2 = c2.get(Calendar.MONTH);
				num = month2 - month1;
				break;
			case DAY:
				int day1 = c1.get(Calendar.DAY_OF_YEAR);
				int day2 = c2.get(Calendar.DAY_OF_YEAR);
				num = day2 - day1;
				break;
		}
		return num;
	}
	
	
	/**
	 * 将一个泛型List<Map<String,String>>
	 * Map里的值转换为String类型
	 * @param oldList
	 * @return
	 */
	public static List<Map<String,String>> ListValueConvertString(List<Map<String,String>> oldList){
		List<Map<String, String>> resultList=new ArrayList<Map<String,String>>();
		for (Map<String, String> valMap:oldList) {
			Map<String, String> resultMap=new HashMap<String, String>();
			for (String key:valMap.keySet()) {
				Object obj=valMap.get(key);
				resultMap.put(key, new String(obj.toString()));
			}
			resultList.add(resultMap);
		}
		oldList.clear();
		
		return resultList;
	}
}
