package com.jerry.uilib.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jerry.uilib.R;

public class PostCode extends RelativeLayout {

	private Context mContext;
	private EditText mEditText1, mEditText2, mEditText3, mEditText4, mEditText5, mEditText6;
	
	public PostCode(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public PostCode(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs); 
	}

	public PostCode(Context context) {
		super(context);
		init(context,null);
	}

	private void init(Context context, AttributeSet attrs) {
		mContext = context;

		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		inflater.inflate(R.layout.post_code, this);
		mEditText1 = (EditText) findViewById(R.id.text1);
		mEditText2 = (EditText) findViewById(R.id.text2);
		mEditText3 = (EditText) findViewById(R.id.text3);
		mEditText4 = (EditText) findViewById(R.id.text4);
		mEditText5 = (EditText) findViewById(R.id.text5);
		mEditText6 = (EditText) findViewById(R.id.text6);

		mEditText1.addTextChangedListener(textWatcher);
		mEditText2.addTextChangedListener(textWatcher);
		mEditText3.addTextChangedListener(textWatcher);
		mEditText4.addTextChangedListener(textWatcher);
		mEditText5.addTextChangedListener(textWatcher);
		mEditText6.addTextChangedListener(textWatcher);
		
		mEditText1.setOnKeyListener(keyListener);
		mEditText2.setOnKeyListener(keyListener);
		mEditText3.setOnKeyListener(keyListener);
		mEditText4.setOnKeyListener(keyListener);
		mEditText5.setOnKeyListener(keyListener);
		mEditText6.setOnKeyListener(keyListener);

	}

	public String getResult() {
        String result = mEditText1.getText().toString() + 
						mEditText2.getText().toString() + 
						mEditText3.getText().toString() + 
						mEditText4.getText().toString() + 
						mEditText5.getText().toString() + 
						mEditText6.getText().toString();		return result;
	}
	
	TextWatcher textWatcher = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable value) {
			int focusPosition = getBestFocusPosition(false);
			setBestFocusPosition(focusPosition);
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			
		}
	};
	
	OnKeyListener keyListener = new OnKeyListener() {
		@Override
		public boolean onKey(View arg0, int arg1, KeyEvent keyEvent) {
			if (KeyEvent.KEYCODE_DEL == keyEvent.getKeyCode()) {
				int focusPosition = getBestFocusPosition(true);
				setBestFocusPosition(focusPosition);
			}
			
			return false;
		}
		
	};
	
	
	private int getBestFocusPosition(boolean isDel) {
		if (isDel) {
			if (mEditText6.getText().length() > 0)
				return 6;
			else if (mEditText5.getText().length() > 0)
				return 5;
			else if (mEditText4.getText().length() > 0)
				return 4;
			else if (mEditText3.getText().length() > 0)
				return 3;
			else if (mEditText2.getText().length() > 0)
				return 2;
			else if (mEditText1.getText().length() > 0)
				return 1;
			else
				return 1;
		} else {
			if (mEditText1.getText().length() == 0)
				return 1;
			else if (mEditText2.getText().length() == 0)
				return 2;
			else if (mEditText3.getText().length() == 0)
				return 3;
			else if (mEditText4.getText().length() == 0)
				return 4;
			else if (mEditText5.getText().length() == 0)
				return 5;
			else if (mEditText6.getText().length() == 0)
				return 6;
			else
				return 6;
		}
		
	}
	
	private void setBestFocusPosition(int focusPosition) {
		switch (focusPosition) {
		case 1:
			mEditText1.requestFocus();
			break;
		case 2:
			mEditText2.requestFocus();
			break;
		case 3:
			mEditText3.requestFocus();
			break;
		case 4:
			mEditText4.requestFocus();
			break;
		case 5:
			mEditText5.requestFocus();
			break;
		case 6:
			mEditText6.requestFocus();
			break;
			
		}
	}
	
}