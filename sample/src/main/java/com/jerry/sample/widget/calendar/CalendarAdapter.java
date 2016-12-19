package com.jerry.sample.widget.calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jerry.sample.R;
import com.jerry.uilib.widget.calendar.CalendarUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日历gridview中的每一个item显示的textview
 */
public class CalendarAdapter extends BaseAdapter {

	private Context mContext;
	private String[] dayNumber = new String[49]; // 一个gridview中的日期存入此数组中
	private static String week[] = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
	
	private boolean isLeapyear = false; // 是否为闰年
	private int daysOfMonth = 0; // 某月的天数
	private int dayOfWeek = 0; // 具体某一天是星期几
	private int lastDaysOfMonth = 0; // 上一个月的总天数
	
	private String showYear = ""; // 用于在头部显示的年份
	private String showMonth = ""; // 用于在头部显示的月份
	private String animalsYear = "";  // 生肖
	private String leapMonth = ""; // 闰年哪一个月
	private String cyclical = ""; // 天干地支
	
	private int todayIndex = -1; // 用于标记当天
	private List<CalendarInfoBean> schedulingList;
	private Map<Integer,String> schedulingMap = null; // 存储当月所有的日程日期和状态

	public CalendarAdapter() {
		
	}

	public CalendarAdapter(Context context, List<CalendarInfoBean> schedulingList, int year, int month, int day) {
		this.mContext = context;
		this.schedulingList = schedulingList;
		getCalendar(year, month);
	}
	
	@Override
	public int getCount() {
		return dayNumber.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.calendar_item, null);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.tvtext);
		addGridItemStyle(position, textView);
		
		if (position < 7) {
			setWeekStyle(textView);
		}
		if (position < daysOfMonth + dayOfWeek + 7 && position >= dayOfWeek + 7) {
			setThisMonthStyle(textView);
		}
		setSchedulingStyle(position,textView);
		if (todayIndex == position) {
			setTodayStyle(textView);
		}
		return convertView;
	}

	private void addGridItemStyle(int position,TextView textView) {
		String d = dayNumber[position].split("\\.")[0];
		String dv = dayNumber[position].split("\\.")[1];
		
		SpannableString sp;
		if (TextUtils.isEmpty(dv.trim())) {
			sp = new SpannableString(d);
		} else {
			sp = new SpannableString(d + "\n" + dv);
			sp.setSpan(new RelativeSizeSpan(0.75f), d.length() + 1,
					dayNumber[position].length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
				d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(new RelativeSizeSpan(1.2f), 0, d.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// sp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 14, 16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
		textView.setText(sp);
		textView.setTextColor(Color.GRAY);
	}
	
	private void setWeekStyle(TextView textView) {
		// 设置周
		textView.setTextColor(Color.BLACK);
		Drawable drawable = mContext.getResources().getDrawable(
				R.mipmap.calendar_week_top);
		textView.setBackgroundDrawable(drawable);
	}
	
	private void setThisMonthStyle(TextView textView) {
		// 当前月信息显示
		textView.setTextColor(Color.BLACK);// 本月字体设黑
		Drawable drawable = mContext.getResources()
				.getDrawable(R.mipmap.calendar_item);
		textView.setBackgroundDrawable(drawable);
		textView.setBackgroundColor(Color.WHITE);
	}
	
	private void setTodayStyle(TextView textView) {
		// 设置当天的背景
		Drawable drawable = mContext.getResources().getDrawable(
				R.mipmap.calendar_current_day_bg);
		textView.setBackgroundDrawable(drawable);
		textView.setTextColor(Color.WHITE);
	}
	
	private void setSchedulingStyle(int position,TextView textView) {
		if (schedulingMap != null && schedulingMap.size() > 0) {
			for (int i = 0; i < schedulingMap.size(); i++) {
				if (schedulingMap.containsKey(position)) {
					// 设置日程标记背景
					String status = schedulingMap.get(position);
					if (CalendarInfoBean.SCHEDULING_PROGRAM_BUSY.equalsIgnoreCase(status)) {
						textView.setBackgroundResource(R.mipmap.calendar_mark);
					} else if (CalendarInfoBean.SCHEDULING_PROGRAM_ORDER.equalsIgnoreCase(status)) {
						textView.setBackgroundResource(R.mipmap.calendar_week_top);
					}
				}
			}
		}
	}
	
	public void getCalendar(int year, int month) {
		isLeapyear = CalendarUtils.getInstance().isLeapYear(year); // 是否为闰年
		daysOfMonth = CalendarUtils.getInstance().getDaysOfMonth(isLeapyear, month); // 某月的总天数
		dayOfWeek = CalendarUtils.getInstance().getWeekdayOfMonth(year, month); // 某月第一天为星期几
		lastDaysOfMonth = CalendarUtils.getInstance().getDaysOfMonth(isLeapyear, month - 1); // 上一个月的总天数
		getweek(year, month);
	}

	// 将一个月中的每一天的值添加入数组dayNumber中
	private void getweek(int year, int month) {
		int j = 1;
		String lunarDay = ""; // 农历--天

		// 得到当前月的所有日程日期(这些日期需要标记)
		if (schedulingList != null && schedulingList.size() > 0) {
			schedulingMap = new HashMap<Integer, String>();
		}
		
		// 表格中显示的所有数据
		for (int i = 0; i < dayNumber.length; i++) {
			// 周一
			if (i < 7) {
				dayNumber[i] = week[i] + "." + " ";
			} else if (i < dayOfWeek + 7) { // 前一个月
				int temp = lastDaysOfMonth - dayOfWeek + 1 - 7;
				lunarDay = CalendarUtils.getInstance().getLunarDate(year, month - 1, temp + i, false);
				dayNumber[i] = (temp + i) + "." + lunarDay;
			} else if (i < daysOfMonth + dayOfWeek + 7) { // 本月
				int day = i - dayOfWeek + 1 - 7; // 得到的日期
				lunarDay = CalendarUtils.getInstance().getLunarDate(year, month, i - dayOfWeek + 1 - 7, false);
				dayNumber[i] = i - dayOfWeek + 1 - 7 + "." + lunarDay;
				
				// 对于当前月才去标记当前日期
				setTodayPosition(i, year, month, day);
				// 标记日程日期
				setSchedulingMap(i, year, month, day);

				setShowYear(String.valueOf(year));
				setShowMonth(String.valueOf(month));
				setAnimalsYear(CalendarUtils.getInstance().animalsYear(year));
				setLeapMonth(CalendarUtils.getInstance().leapMonth == 0 ? "" : String
						.valueOf(CalendarUtils.getInstance().leapMonth));
				setCyclical(CalendarUtils.getInstance().cyclical(year));
			} else { // 下一个月
				lunarDay = CalendarUtils.getInstance().getLunarDate(year, month + 1, j, false);
				dayNumber[i] = j + "." + lunarDay;
				j++;
			}
		}
	}

	public void matchScheduleDate(int year, int month, int day) {

	}

	private void setTodayPosition(int position, int year, int month, int day) {
		String nowDate = CalendarUtils.getInstance().getNowDate();
		String[] nowDateArray = nowDate.split("-");
		if (Integer.parseInt(nowDateArray[0]) == year 
				&& Integer.parseInt(nowDateArray[1]) == month 
				&& Integer.parseInt(nowDateArray[2]) == day) {
			//当前日期
			todayIndex = position;
		}
	}
	
	private void setSchedulingMap(int position, int year, int month, int day) {
		if (schedulingList != null && schedulingList.size() > 0) {
			for (int m = 0; m < schedulingList.size(); m++) {
				CalendarInfoBean schedulingInfoBean = schedulingList.get(m);
				String[] schedulingDateArray = schedulingInfoBean.getDate().split("-");
				int matchYear = Integer.parseInt(schedulingDateArray[0]);
				int matchMonth = Integer.parseInt(schedulingDateArray[1]);
				int matchDay = Integer.parseInt(schedulingDateArray[2]);
				String schedulingStatus = schedulingInfoBean.getStatus();
				if (matchYear == year && matchMonth == month && matchDay == day) {
					schedulingMap.put(position, schedulingStatus);
				}
			}
		}
	}
	
	/**
	 * 点击每一个item时返回item中的日期
	 * 
	 * @param position
	 * @return
	 */
	public String getDateByClickItem(int position) {
		return dayNumber[position];
	}

	/**
	 * 在点击gridView时，得到这个月中第一天的位置
	 * 
	 * @return
	 */
	public int getStartPositon() {
		return dayOfWeek + 7;
	}

	/**
	 * 在点击gridView时，得到这个月中最后一天的位置
	 * 
	 * @return
	 */
	public int getEndPosition() {
		return (dayOfWeek + daysOfMonth + 7) - 1;
	}

	public String getShowYear() {
		return showYear;
	}

	public void setShowYear(String showYear) {
		this.showYear = showYear;
	}

	public String getShowMonth() {
		return showMonth;
	}

	public void setShowMonth(String showMonth) {
		this.showMonth = showMonth;
	}

	public String getAnimalsYear() {
		return animalsYear;
	}

	public void setAnimalsYear(String animalsYear) {
		this.animalsYear = animalsYear;
	}

	public String getLeapMonth() {
		return leapMonth;
	}

	public void setLeapMonth(String leapMonth) {
		this.leapMonth = leapMonth;
	}

	public String getCyclical() {
		return cyclical;
	}

	public void setCyclical(String cyclical) {
		this.cyclical = cyclical;
	}
}
