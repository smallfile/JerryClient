package com.jerry.uilib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jerry.uilib.R;

/**
 * 
 * @author jerry
 *
 */
public class NavigationBar extends RelativeLayout {

	private TextView navStatusBar;
	private Button navLeftBtn;
	private Button navLeftBack;
	private Button navRightBtn1;
	private Button navRightBtn2;
	private Button navRightBtn3;
	private TextView navTitle;
	
	public NavigationBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public NavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs); 
	}

	public NavigationBar(Context context) {
		super(context);
		init(context,null);
	}

	private void init(Context context, AttributeSet attrs) {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		inflater.inflate(R.layout.navigation_bar, this);
		navStatusBar = (TextView) findViewById(R.id.nav_status_bar);
		navLeftBtn = (Button) findViewById(R.id.nav_left_btn);
		navLeftBack = (Button) findViewById(R.id.nav_left_back);
		navRightBtn1 = (Button) findViewById(R.id.nav_right_btn1);
		navRightBtn2 = (Button) findViewById(R.id.nav_right_btn2);
		navRightBtn3 = (Button) findViewById(R.id.nav_right_btn3);
		navTitle = (TextView) findViewById(R.id.nav_title);
		
		TypedArray attrsType = context.obtainStyledAttributes(attrs, R.styleable.navigation_bar);
		setStatusBarAttribute(attrsType);
		setLeftBtnAttribute(attrsType);
		setLeftBackAttribute(attrsType);
		setRightBtn1Attribute(attrsType);
		setRightBtn2Attribute(attrsType);
		setRightBtn3Attribute(attrsType);
		setTitleAttribute(attrsType);
		attrsType.recycle(); 
		    
	}

	private void setStatusBarAttribute(TypedArray attrsType){
		if (attrsType.hasValue(R.styleable.navigation_bar_statusBarVisibility)) {
			int visibleType = attrsType.getInt(R.styleable.navigation_bar_statusBarVisibility, 0);
			switch(visibleType) {
				case 0:
					navStatusBar.setVisibility(View.GONE);
					break;
				case 1:
					navStatusBar.setVisibility(View.VISIBLE);
					break;
				case 2:
					navStatusBar.setVisibility(View.INVISIBLE);
					break;
			}
		}
	}

	private void setLeftBtnAttribute(TypedArray attrsType) {
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBtnVisibility)) {
			int visibleType = attrsType.getInt(R.styleable.navigation_bar_leftBtnVisibility, 0);
			switch(visibleType) {
			case 0:
				navLeftBtn.setVisibility(View.GONE);
				break;
			case 1:
				navLeftBtn.setVisibility(View.VISIBLE);
				break;
			case 2:
				navLeftBtn.setVisibility(View.INVISIBLE);
				break;
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBtnBackground)) {
			Drawable background = attrsType.getDrawable(R.styleable.navigation_bar_leftBtnBackground);
			if (null != background) {
				navLeftBtn.setBackgroundDrawable(background);
			}
		}  
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBtnName)) {
			String titleName = attrsType.getString(R.styleable.navigation_bar_leftBtnName);
			if (!TextUtils.isEmpty(titleName)) {
				navLeftBtn.setText(titleName);
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBtnColor)) {
			int titleColor = attrsType.getColor(R.styleable.navigation_bar_leftBtnColor, getResources().getColor(android.R.color.white));
			navLeftBtn.setTextColor(titleColor);
		} 
	}

	private void setLeftBackAttribute(TypedArray attrsType) {
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBackVisibility)) {
			int visibleType = attrsType.getInt(R.styleable.navigation_bar_leftBackVisibility, 0);
			switch(visibleType) {
				case 0:
					navLeftBack.setVisibility(View.GONE);
					break;
				case 1:
					navLeftBack.setVisibility(View.VISIBLE);
					break;
				case 2:
					navLeftBack.setVisibility(View.INVISIBLE);
					break;
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBackBackground)) {
			Drawable background = attrsType.getDrawable(R.styleable.navigation_bar_leftBackBackground);
			if (null != background) {
				navLeftBack.setBackgroundDrawable(background);
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBackName)) {
			String titleName = attrsType.getString(R.styleable.navigation_bar_leftBackName);
			if (!TextUtils.isEmpty(titleName)) {
				navLeftBack.setText(titleName);
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_leftBackColor)) {
			int titleColor = attrsType.getColor(R.styleable.navigation_bar_leftBackColor, getResources().getColor(android.R.color.white));
			navLeftBack.setTextColor(titleColor);
		}
	}

	private void setRightBtn1Attribute(TypedArray attrsType) {
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn1Visibility)) {
			int visibleType = attrsType.getInt(R.styleable.navigation_bar_rightBtn1Visibility, 0);
			switch(visibleType) {
			case 0:
				navRightBtn1.setVisibility(View.GONE);
				break;
			case 1:
				navRightBtn1.setVisibility(View.VISIBLE);
				break;
			case 2:
				navRightBtn1.setVisibility(View.INVISIBLE);
				break;
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn1Background)) {
			Drawable background = attrsType.getDrawable(R.styleable.navigation_bar_rightBtn1Background);
			if (null != background) {
				navRightBtn1.setBackgroundDrawable(background);
			}
		} 
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn1Name)) {
			String titleName = attrsType.getString(R.styleable.navigation_bar_rightBtn1Name);
			if (!TextUtils.isEmpty(titleName)) {
				navRightBtn1.setText(titleName);
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn1Color)) {
			int titleColor = attrsType.getColor(R.styleable.navigation_bar_rightBtn1Color, getResources().getColor(android.R.color.white));
			navRightBtn1.setTextColor(titleColor);
		} 
	}
	
	private void setRightBtn2Attribute(TypedArray attrsType) {
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn2Visibility)) {
			int visibleType = attrsType.getInt(R.styleable.navigation_bar_rightBtn2Visibility, 0);
			switch(visibleType) {
			case 0:
				navRightBtn2.setVisibility(View.GONE);
				break;
			case 1:
				navRightBtn2.setVisibility(View.VISIBLE);
				break;
			case 2:
				navRightBtn2.setVisibility(View.INVISIBLE);
				break;
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn2Background)) {
			Drawable background = attrsType.getDrawable(R.styleable.navigation_bar_rightBtn2Background);
			if (null != background) {
				navRightBtn2.setBackgroundDrawable(background);
			}
		} 
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn2Name)) {
			String titleName = attrsType.getString(R.styleable.navigation_bar_rightBtn2Name);
			if (!TextUtils.isEmpty(titleName)) {
				navRightBtn2.setText(titleName);
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn2Color)) {
			int titleColor = attrsType.getColor(R.styleable.navigation_bar_rightBtn2Color, getResources().getColor(android.R.color.white));
			navRightBtn2.setTextColor(titleColor);
		} 
	}
	
	private void setRightBtn3Attribute(TypedArray attrsType) {
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn3Visibility)) {
			int visibleType = attrsType.getInt(R.styleable.navigation_bar_rightBtn3Visibility, 0);
			switch(visibleType) {
			case 0:
				navRightBtn3.setVisibility(View.GONE);
				break;
			case 1:
				navRightBtn3.setVisibility(View.VISIBLE);
				break;
			case 2:
				navRightBtn3.setVisibility(View.INVISIBLE);
				break;
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn3Background)) {
			Drawable background = attrsType.getDrawable(R.styleable.navigation_bar_rightBtn3Background);
			if (null != background) {
				navRightBtn3.setBackgroundDrawable(background);
			}
		}  
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn3Name)) {
			String titleName = attrsType.getString(R.styleable.navigation_bar_rightBtn3Name);
			if (!TextUtils.isEmpty(titleName)) {
				navRightBtn3.setText(titleName);
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_rightBtn3Color)) {
			int titleColor = attrsType.getColor(R.styleable.navigation_bar_rightBtn3Color, getResources().getColor(android.R.color.white));
			navRightBtn3.setTextColor(titleColor);
		}  
	}
	
	private void setTitleAttribute(TypedArray attrsType) {
		if (attrsType.hasValue(R.styleable.navigation_bar_titleName)) {
			String titleName = attrsType.getString(R.styleable.navigation_bar_titleName);
			if (!TextUtils.isEmpty(titleName)) {
				navTitle.setText(titleName);
			}
		}
		if (attrsType.hasValue(R.styleable.navigation_bar_titleColor)) {
			int titleColor = attrsType.getColor(R.styleable.navigation_bar_titleColor, getResources().getColor(android.R.color.white));
			navTitle.setTextColor(titleColor);
		}  
		if (attrsType.hasValue(R.styleable.navigation_bar_titleSize)) {
			String titleSize = attrsType.getString(R.styleable.navigation_bar_titleSize);
			navTitle.setTextSize(Float.parseFloat(titleSize));
		} 
	}

	// *************************　设置监听　******************************
	public void setLeftBtnListener(OnClickListener listener) {
		navLeftBtn.setOnClickListener(listener);
	}

	public void setLeftBackListener(OnClickListener listener){
		navLeftBack.setOnClickListener(listener);
	}
	public void setRightBtn1Listener(OnClickListener listener) {
		navRightBtn1.setOnClickListener(listener);
	}
	
	public void setRightBtn2Listener(OnClickListener listener) {
		navRightBtn2.setOnClickListener(listener);
	}
	
	public void setRightBtn3Listener(OnClickListener listener) {
		navRightBtn3.setOnClickListener(listener);
	}

	public void setTitleListener(OnClickListener listener) {
		navTitle.setOnClickListener(listener);
	}
	//**********************************************************************

	//****************************设置文字***********************************
	public void setLeftBtnName(String name) {
		navLeftBtn.setText(name);
	}

	public void setRightBtn3Name(String name) {
		navRightBtn3.setText(name);
	}

	public void setTitleText(String text) {
		navTitle.setText(text);
	}
	//***********************************************************************

	//****************************设置是否显示******************************
	public void setStatusBarVisibility(Boolean bool) {
		int flag = bool? View.VISIBLE : View.GONE;
		navStatusBar.setVisibility(flag);
	}

	public void setLeftBtnVisibility(Boolean bool) {
		int flag = bool? View.VISIBLE : View.GONE;
		navLeftBtn.setVisibility(flag);
	}

	public void setLeftBackVisibility(Boolean bool) {
		int flag = bool? View.VISIBLE : View.GONE;
		navLeftBack.setVisibility(flag);
	}

	public void setRightBtn1Visibility(Boolean bool) {
		int flag = bool? View.VISIBLE : View.GONE;
		navRightBtn1.setVisibility(flag);
	}

	public void setRightBtn2Visibility(Boolean bool) {
		int flag = bool? View.VISIBLE : View.GONE;
		navRightBtn2.setVisibility(flag);
	}

	public void setRightBtn3Visibility(Boolean bool) {
		int flag = bool? View.VISIBLE : View.GONE;
		navRightBtn3.setVisibility(flag);
	}
	//*****************************************************************

	//****************************设置按钮背景图片******************************
	public void setLeftBtnBackground(int resId) {
		navLeftBtn.setBackgroundResource(resId);
	}

	public void setLeftBackBackground(int resId) {
		navLeftBack.setBackgroundResource(resId);
	}

	public void setRightBtn1Background(int resId) {
		navRightBtn1.setBackgroundResource(resId);
	}

	public void setRightBtn2Background(int resId) {
		navRightBtn2.setBackgroundResource(resId);
	}

	public void setRightBtn3Background(int resId) {
		navRightBtn3.setBackgroundResource(resId);
	}
	//*****************************************************************

	public void setTitleSize(float size) {
		navTitle.setTextSize(size);
	}
	

}