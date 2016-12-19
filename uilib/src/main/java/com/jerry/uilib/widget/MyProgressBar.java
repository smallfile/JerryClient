package com.jerry.uilib.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class MyProgressBar extends ImageView {

	private AnimationDrawable anim = null;

	public MyProgressBar(Context context) {
		super(context);
		anim = (AnimationDrawable) this.getDrawable();
	}

	public MyProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		anim = (AnimationDrawable) this.getDrawable();
	}

	public MyProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		anim = (AnimationDrawable) this.getDrawable();
	}

	public void show() {
		this.setVisibility(View.VISIBLE);

	}

	public void dismiss() {
		this.setVisibility(View.GONE);
	}

	public void start() {
		if (anim != null) {
			if (!anim.isRunning()) {
				anim.start();
			}
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		if (anim != null) {
			if (anim.isRunning()) {
				anim.stop();
			} else {
				anim.start();
			}
		}
	}

}
