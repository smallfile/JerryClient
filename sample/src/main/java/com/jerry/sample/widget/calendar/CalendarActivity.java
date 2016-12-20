package com.jerry.sample.widget.calendar;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.jerry.sample.R;
import com.jerry.sample.utils.DialogUtils;
import com.jerry.uilib.widget.calendar.BorderText;
import com.jerry.uilib.widget.calendar.CalendarUtils;

import java.util.Date;
import java.util.List;


public class CalendarActivity extends Activity implements OnGestureListener {

	private ViewFlipper flipper = null;
	private GestureDetector gestureDetector = null;
	private CalendarAdapter calendarAdapter = null;
	private GridView gridView = null;
	private BorderText topText = null;
	
	private int year_now = 0;
	private int month_now = 0;
	private int day_now = 0;
	private static int jumpMonth = 0;      //每次滑动，增加或减去一个月,默认为0（即显示当前月）
	private static int jumpYear = 0;       //滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
	private int year_adapter = 0;
	private int month_adapter = 0;
	private int day_adapter = 0;
	
	public CalendarActivity() {
		String nowDate = CalendarUtils.getInstance().getNowDate();
		String[] array = nowDate.split("-");
		year_now = Integer.parseInt(array[0]);
		month_now = Integer.parseInt(array[1]);
		day_now = Integer.parseInt(array[2]);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		gestureDetector = new GestureDetector(this);

		addTopTextViewConfig();
        addGridView();
        setAdapterDate();
        
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        flipper.removeAllViews();
        flipper.addView(gridView,0);

		loadSchedulingData("1");

	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		long l1=System.currentTimeMillis();
		int gridViewFlag = 0;         //每次添加gridview到viewflipper中时给的标记
		if (e1.getX() - e2.getX() > 120) {
            //像左滑动
			addGridView();   //添加一个gridView
			jumpMonth++;     //下一个月
			
			setAdapterDate();
			loadSchedulingData("1");
	        gridViewFlag ++;
	        flipper.addView(gridView, gridViewFlag);
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.calendar_push_left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.calendar_push_left_out));
			this.flipper.showNext();
			flipper.removeViewAt(0);
			return true;
		} else if (e1.getX() - e2.getX() < -120) {
            //向右滑动
			addGridView();   //添加一个gridView
			jumpMonth--;     //上一个月
			
			setAdapterDate();
			loadSchedulingData("1");
	        gridViewFlag ++;
	        flipper.addView(gridView, gridViewFlag);
	        
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.calendar_push_right_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.calendar_push_right_out));
			this.flipper.showPrevious();
			flipper.removeViewAt(0);
			return true;
		}
		long l2=System.currentTimeMillis();
		Log.i("time",(l1-l2)+"");
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.gestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	
	private void addTopTextViewConfig() {
		topText = (BorderText) findViewById(R.id.toptext);
		topText.setBackgroundResource(R.drawable.calendar_top_day);
		topText.setTextColor(Color.BLACK);
		topText.setTypeface(Typeface.DEFAULT_BOLD);
	}
	
	public void addTextToTopTextView(){
		StringBuffer textDate = new StringBuffer();
		textDate.append(calendarAdapter.getShowYear()).append("年");
		textDate.append(calendarAdapter.getShowMonth()).append("月");
		topText.setText(textDate);
	}
	
	//添加gridview
	private void addGridView() {
		addGridViewConfig();
		gridView.setOnTouchListener(touchListener);
		gridView.setOnItemClickListener(itemClickListener);
	}
	
	OnTouchListener touchListener = new OnTouchListener() {
        //将gridview中的触摸事件回传给gestureDetector
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return CalendarActivity.this.gestureDetector
					.onTouchEvent(event);
		}
	};
			
	OnItemClickListener itemClickListener = new OnItemClickListener() {
		// gridView中的每一个item的点击事件
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			int startPosition = calendarAdapter.getStartPositon();
			int endPosition = calendarAdapter.getEndPosition();
			String year = calendarAdapter.getShowYear();
			String month = calendarAdapter.getShowMonth();
			String day = calendarAdapter.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
			String clickDateStr = year+"-"+month+"-"+day;
			Date clickDate = CalendarUtils.getInstance().str2Date(clickDateStr);
			if (startPosition <= position && position <= endPosition && clickDate.after(new Date())) {
				showSchedulingDialog(clickDateStr);
			}
		}
	};
	
	private void addGridViewConfig() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		//取得屏幕的宽度和高度
		WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int Width = display.getWidth(); 
        int Height = display.getHeight();
        
		gridView = new GridView(this);
		gridView.setNumColumns(7);
		gridView.setColumnWidth(46);
		
		if(Width == 480 && Height == 800){
			gridView.setColumnWidth(69);
		}
		gridView.setLayoutParams(params);
		
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT)); // 去除gridView边框
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
        gridView.setBackgroundResource(R.drawable.calendar_gridview_bk);
	}
	
	private void showSchedulingDialog(final String date){
		
		DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int whichButton) {
				switch(whichButton){
				case 0:
					dialog.dismiss();
					changeSchedulingStatus("1", date, CalendarInfoBean.SCHEDULING_PROGRAM_ORDER);
					break;
				case 1:
					dialog.dismiss();
					changeSchedulingStatus("1", date, CalendarInfoBean.SCHEDULING_PROGRAM_BUSY);
					break;
				case 2:
					dialog.dismiss();
					changeSchedulingStatus("1", date, CalendarInfoBean.SCHEDULING_PROGRAM_IDLE);
					break;
				case 3:
					dialog.dismiss();
					break;
				}
			}
		};
		
		String operators[] = null;
		operators = new String[4];
		operators[0] = "预定";
		operators[1] = "忙";
		operators[2] = "空闲";
		operators[3] = "取消";
		if (operators != null) {		
			new DialogUtils(CalendarActivity.this).showItemsDialog(operators, listener);
		}
	}

	private void setAdapterDate() {
		year_adapter = year_now + jumpYear;
		month_adapter = month_now + jumpMonth;
		if (month_adapter > 0) {
			// 往下一个月滑动
			if (month_adapter % 12 == 0) {
				year_adapter = year_now + month_adapter / 12 - 1;
				month_adapter = 12;
			} else {
				year_adapter = year_now + month_adapter / 12;
				month_adapter = month_adapter % 12;
			}
		} else {
			// 往上一个月滑动
			year_adapter = year_now - 1 + month_adapter / 12;
			month_adapter = month_adapter % 12 + 12;
			if (month_adapter % 12 == 0) {

			}
		}
		day_adapter = day_now;
		
	}

	private void loadSchedulingData(String guideId) {
		updatePage(null);
	}

	private void changeSchedulingStatus(String guideId,String date,String status) {
		updatePage(null);
	}

//	private void loadSchedulingData(String guideId) {
//		SchedulingReqBean reqBean = new SchedulingReqBean();
//		reqBean.setBusinessCode(Code.T_101);
//
//		reqBean.setMethod("getSchedulingList");
//		reqBean.setGuideId(guideId);
//		reqBean.setDate(year_adapter+"-"+month_adapter);
//
//		AsyncTaskUtils.getInstance().requestData(CalendarActivity.this, reqBean, mCallback);
//	}
//
//	private void changeSchedulingStatus(String guideId,String date,String status) {
//		SchedulingReqBean reqBean = new SchedulingReqBean();
//		reqBean.setBusinessCode(Code.T_102);
//
//		reqBean.setMethod("addOrUpdateScheduling");
//		reqBean.setGuideId(guideId);
//		reqBean.setDate(date);
//		reqBean.setStatus(status);
//
//		AsyncTaskUtils.getInstance().requestData(CalendarActivity.this, reqBean, mCallback);
//	}
//
//	private final ICallback mCallback = new ICallback() {
//		@Override
//		public void updateUI(LibResponseBean resultBean, int businessCode) {
//			SchedulingResBean resBean = (SchedulingResBean) resultBean;
//			if (resBean != null) {
//				List<CalendarInfoBean> schedulingList = resBean.getSchedulingList();
//				updatePage(schedulingList);
//			}
//		};
//	};

	private void updatePage(List<CalendarInfoBean> data) {
		calendarAdapter = new CalendarAdapter(this,data, year_adapter,month_adapter,day_adapter);
	    gridView.setAdapter(calendarAdapter);
	    //添加头部的年份
	    addTextToTopTextView();
	}
	
	

}