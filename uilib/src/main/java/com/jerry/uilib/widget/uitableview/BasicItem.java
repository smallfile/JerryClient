package com.jerry.uilib.widget.uitableview;

public class BasicItem implements IListItem {
	
	private boolean mClickable = true;
	private int mDrawable = -1;
	private String mTitle;
	private String mSubtitle;
	private int mCheckboxDrawable = -1;
	

	public BasicItem() {

	}
	
	public BasicItem(String _title) {
		this.mTitle = _title;
	}
	
	public BasicItem(String _title, String _subtitle) {
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
	}
	
	public BasicItem(String _title, String _subtitle, int _checkboxDrawable) {
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
		this.mCheckboxDrawable = _checkboxDrawable;
	}
	
	public BasicItem(String _title, String _subtitle, boolean _clickable) {
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
		this.mClickable = _clickable;
	}	
	
	public BasicItem(int _drawable, String _title, String _subtitle) {
		this.mDrawable = _drawable;
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
	}
	
	public BasicItem(int _drawable, String _title, String _subtitle, int _checkboxDrawable) {
		this.mDrawable = _drawable;
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
		this.mCheckboxDrawable = _checkboxDrawable;
	}

	public BasicItem(int _drawable, String _title) {
		this.mDrawable = _drawable;
		this.mTitle = _title;
	}
	
	public int getDrawable() {
		return mDrawable;
	}

	public void setDrawable(int drawable) {
		this.mDrawable = drawable;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getSubtitle() {
		return mSubtitle;
	}

	public void setSubtitle(String summary) {
		this.mSubtitle = summary;
	}

	public int getCheckboxDrawable() {
		return mCheckboxDrawable;
	}

	public void setCheckboxDrawable(int checkboxDrawable) {
		this.mCheckboxDrawable = checkboxDrawable;
	}

	@Override
	public boolean isClickable() {
		return mClickable;
	}

	@Override
	public void setClickable(boolean clickable) {
		mClickable = clickable;			
	}
	
}
